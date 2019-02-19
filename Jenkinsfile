def testStages = (1..6).collectEntries { [it: generateStage(it)] }
def generateStage(index) {
    return {
        stage("test stage: ${index}") {
                echo "This is test job number: ${index}."
                sh "mvn test -Dtest=Demo${index}Tests"
        }
    }
}

pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('test') {
            steps {
                script {
                    parallel testStages
                }
            }
        }

        stage('generate reports') {
            steps {
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            }
        }
    }
}
