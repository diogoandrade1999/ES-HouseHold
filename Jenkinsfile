pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                sh 'cd springboot/temperature && mvn install'
            }
        }
    }
}