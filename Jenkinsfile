pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git url: '172.17.0.1:workspace/work/presentations/jenkinsfile-example', credentialsId: 'd366e042-f9f9-418b-8c52-df18e93d6226'
            }
        }

        stage('build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('generate reports') {
            steps {
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            }
        }
    }
}
