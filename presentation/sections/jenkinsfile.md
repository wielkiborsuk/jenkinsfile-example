### Jenkinsfile
* zamiast kodować w jenkinsie, można to umieścić w repo
* plik Jenkinsfile (zwykle w root projektu)
* w buildzie wystarczy zdefiniować, gdzie jest repo


### Jak się go używa?
![build_with_jenkinsfile](images/build_with_jenkinsfile.png)
<!-- .element: class="stretch" -->


### Najprostszy build
```
pipeline {
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
    }
}
```
