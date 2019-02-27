### Options
```
pipeline {
    options {
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '2'))
        preserveStashes(buildCount: 5)
        retry(3)
        checkoutToSubdirectory('foo')
        timeout(time: 1, unit: 'HOURS')
    }
}
```


### Parameters
```
pipeline {
    parameters {
        string(name: 'DEPLOY_ENV', defaultValue: 'staging', description: '')
        choice(name: 'CHOICES', choices: ['one', 'two', 'three'], description: '')
    }
}
```


### Triggers
```
pipeline {
    triggers {
        cron('H */4 * * 1-5')
        pollSCM('H */4 * * 1-5')
    }
}
```
