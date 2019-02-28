### Równoległe etapy
```
pipeline {
    stages {
        stage('parallel') {
            failFast true
            parallel {
                stage('branch A') {
                    agent {
                        label "machine a"
                    }
                    ...
                }
                stage('branch B') {
                    agent {
                        label "machine b"
                    }
                    ...
                }
            }
        }
    }
}
```


### Równoległe kroki
```
pipeline {
    stages {
        stage('scripted parallel') {
            steps {
                script {
                    parallel parallelSteps
                }
            }
        }
    }
}
```

```
def testStages = (1..6).collect{ "${it}" }.collectEntries { [(it): generateStage(it)] }
testStages.failFast = true
def generateStage(index) {
    return {
        echo "This is test job number: ${index}."
        sh "mvn test -Dtest=Demo${index}Tests"
        stash name: "testResults${index}", includes: "target/surefire-reports/*Demo${index}Tests*"
    }
}
```


### Stash i pipeline restart
```
pipeline {
    stages {
        stage('generate reports') {
            steps {
              script {
                (1..6).collect { "${it}" }.each {
                  unstash name: "testResults${it}"
                  sh 'ls target/surefire-reports'
                  sh 'cat target/surefire-reports/*Demo${it}Tests*.xml'
                }
              }
            }
        }
    }
}
```
