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

