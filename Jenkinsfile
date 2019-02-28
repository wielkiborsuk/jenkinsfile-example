pipeline {
    agent any

    options {
      buildDiscarder(logRotator(numToKeepStr: '2'))
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
                echo 'new'
            }
        }
    }
}

