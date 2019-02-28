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
            failFast true
            parallel {
                stage('branch A') {
                  steps { echo 'hello a' }
                }
                stage('branch B') {
                  steps { echo 'hello b' }
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

