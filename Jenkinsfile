pipeline {
  agent any
  stages {
    stage(‘Build’) {
      steps {
         sh "/opt/homebrew/Cellar/maven/3.9.3/libexec/bin/mvn clean package"
      }
    }
    stage(‘Test’) {
      steps {
        sh "/opt/homebrew/Cellar/maven/3.9.3/libexec/bin/mvn test"
      }
    }
  }
}
