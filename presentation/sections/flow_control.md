### Input
```
pipeline {
    stages {
        stage('publish') {
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
                ...
            }
        }
    }
}
```


### When
```
pipeline {
    stages {
        stage {
            when {
                beforeAgent true
                beforeInput true

                branch 'master'
                expression { BRANCH_NAME ==~ /(production|staging)/ }
                buildingTag()

                environment name: 'PUBLISH_PROFILE', value: 'beta'
            }
        }
    }
}
```


### Niestabilne testy? - podejście proste
```
pipeline {
    stages {
        stage('failsafe tests') {
            options {
                retry(5)
            }
            steps {
                sh 'mvn test'
            }
        }
    }
}
```


### Niestabilne testy? - bardziej interaktywnie
```
pipeline {
    stages {
        stage('failsafe tests') {
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
    }
}
```
