### Jenkinsfile
* zamiast kodować w jenkinsie, można to umieścić w repo
* plik Jenkinsfile (zwykle w root projektu)
* w buildzie wystarczy zdefiniować, gdzie jest repo


### Jak się go używa?
![build_with_jenkinsfile](images/build_with_jenkinsfile.png)
<!-- .element: class="stretch" -->


### Składnia deklaratywna
```
pipeline {
    agent any

    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
        stage('Build') {
            steps {
                mvn 'clean install -DskipTests'
                archiveArtifacts '**/target/*.*ar'
            }
        }

        stage('Unit Test') {
            steps {
                mvn 'test'
            }
        }

        stage('Integration Test') {
            steps {
                mvn 'verify -DskipUnitTests -Parq-wildfly-swarm '
            }
        }

        stage('Deploy') {
            when { expression { return currentBuild.currentResult == 'SUCCESS' } }
            steps {
                mvn 'deploy'
            }
        }
    }
    post {
        always {
            // Archive Unit and integration test results, if any
            junit allowEmptyResults: true,
                    testResults: '**/target/surefire-reports/TEST-*.xml'
            mailIfStatusChanged env.EMAIL_RECIPIENTS
        }
    }
}

def mailIfStatusChanged(String recipients) {
    if (currentBuild.currentResult == 'SUCCESS') {
        currentBuild.result = 'SUCCESS'
    }
    step([$class: 'Mailer', recipients: recipients])
}
```
