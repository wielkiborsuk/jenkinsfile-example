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
