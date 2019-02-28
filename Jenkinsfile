def testStages = (1..6).collect{ "${it}" }.collectEntries { [(it): generateStage(it)] }
def generateStage(index) {
    return {
        echo "This is test job number: ${index}."
        sh "mvn test -Dtest=Demo${index}Tests"
        stash name: "testResults${index}", includes: "target/surefire-reports/*Demo${index}Tests*"
    }
}

pipeline {
    agent any

    options {
      buildDiscarder(logRotator(numToKeepStr: '2'))
    }

    stages {
        stage('Build') {
            when {
                beforeInput true

                branch 'master'
            }

            steps {
                sh 'mvn clean package -DskipTests'
                echo 'new'
            }
        }

        stage('failsafe tests') {
            when {
                beforeInput true

                branch 'master'
            }

            steps {
                script {
                    waitUntil {
                        try {
                            sh 'mvn test -Dtest="Demo1Tests"'
                            true
                        } catch (error) {
                            input 'retry the job?'
                            false
                        }
                    }
                }
            }
        }

        stage('parallel') {
          steps {
            script {
              parallel testStages
            }
          }
        }

        stage('publish') {
            when {
                beforeInput true

                branch 'master'
            }

          input {
            message "Publish the app?"
              ok "Make it so!"
              submitterParameter "USER"
              parameters {
                choice(choices: ['public', 'beta'], name: 'PUBLISH_PROFILE',
                    description: "What profile should we use for publication?")
              }
          }

          steps {
            echo "accepted by ${USER}"
          }
        }
    }
}
