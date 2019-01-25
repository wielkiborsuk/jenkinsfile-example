### Jenkins pipeline plugin
* orkiestracja mniejszych jobów
* pipeline script zamiast klikania?
* wstrzymywanie buildów (interakcja z człowiekiem)
* `shared libraries` - własne `pluginy` jenkinsa


### Co dostajemy?
![pipeline_view](images/pipeline_view.png)
<!-- .element: class="stretch" -->


### Jak to osiągnąć?
```
node {
    stage('Checkout') {
        git url: 'https://github.com/example-project.git', branch: 'develop', poll: true
    }

    stage('Build') {
        mvn 'clean install -DskipTests'
    }

    stage('Unit Test') {
        mvn 'test'
    }
}

def mvn(def args) {
    def mvnHome = tool 'M3'
    def javaHome = tool 'JDK8'

    withEnv(["JAVA_HOME=${javaHome}", "PATH+MAVEN=${mvnHome}/bin:${env.JAVA_HOME}/bin"]) {
        sh "${mvnHome}/bin/mvn ${args} --batch-mode -U"
    }
}
```
