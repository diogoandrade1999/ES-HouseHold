pipeline {
    agent any
    tools {
        maven 'maven36'
        jdk 'jdk11'
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'cd springboot/temperature && mvn install'
            }
        }
        stage ('Deploy') {
            steps{
                sh 'cd springboot/temperature && mvn deploy -f pom.xml -s settings.xml' 
            }
        }
    }
}