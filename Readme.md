# Gradle Webapp Plugin

This plugin use to build and bundle frontend in Java project.

# Example

```
plugins {
    id'io.github.hlangjian.webapp' version 'latest-version'
}

// Default values
webapp {
    appDir = 'src/main/webapp'
    buildCommand = 'npm install && npm run build'
    from = 'src/main/webapp/dist'
    to = 'build/resources/main/META-INF/resources'
    includes = ['assets/*', 'index.html']
}
```
