def testStages = (1..6).collect{ "${it}" }.collectEntries { [(it): generateStage(it)] }
testStages.failFast = true
def generateStage(index) {
    return {
        waitUntil {
            try {
              echo "This is test job number: ${index}."
              sh "mvn test -Dtest=Demo${index}Tests"
              stash name: "testResults${index}", includes: "target/surefire-reports/*Demo${index}Tests*"
              true
            } catch(error) {
              input "Retry the job?"
              false
            }
        }
    }
}

pipeline {
    agent any

    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '7'))
        preserveStashes(buildCount: 5)
    }

    stages {
        stage('build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('test') {
            steps {
                script {
                    waitUntil {
                        try {
                            sh 'mvn test'
                            true
                        } catch (error) {
                            input 'retry the job?'
                            false
                        }
                    }
                }
            }
        }

        stage('testParallel') {
            steps {
                script {
                    parallel testStages
                }
            }
        }

        stage('generate reports') {
            steps {
              script {
                (1..6).collect { "${it}" }.each {
                  unstash name: "testResults${it}"
                }
              }
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            }
        }

        stage('deploy') {
            when {
                branch 'master'
                beforeInput true
            }
            input {
                message "Proceed with the deployment?"
                submitterParameter "USER"
                parameters {
                    choice(choices: ['softlive', 'live'], name: 'DEPLOY_ENV', description: "Which env do you want to deploy to?")
                }
            }

            steps {
                echo "Deployment to instance: ${DEPLOY_ENV}, approved by ${USER}"
                echo "!!!"
                echo "!!!"
                echo "call it a success!!!"
            }
        }
    }
}
