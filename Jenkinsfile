def remote = [:]
remote.host = "192.168.160.87"
remote.name = "runtime"

pipeline {
    agent any
    tools {
        maven 'maven36'
        jdk 'jdk11'
    }

    stages {
        stage ('Start') {
            steps {
                dir('temperature'){
                    sh '''
                        echo "PATH = ${PATH}"
                        echo "M2_HOME = ${M2_HOME}"
                    '''
                }
            }
        }
        stage('Build') {
            steps {
                dir('temperature'){
                    sh 'mvn -Dmaven.test.failure.ignore=true install' 
                }
            }
            post {
                success {
                    dir('temperature'){
                        junit 'target/surefire-reports/**/*.xml' 
                    }
                }
            }
        }
        stage ('Deploy') {
            steps{
                dir('temperature'){
                    sh 'mvn deploy -f pom.xml -s settings.xml' 
                }
            }
        }
        stage('Publish'){
            steps{
                script{
                    docker.withRegistry("http://192.168.160.48:5000") {
                        def temperatureApp = docker.build("esp51/temperature", "./temperature")
                        temperatureApp.push()
                    }
                }
            }
        }
        stage('Deployment') {
            steps { 
                withCredentials([usernamePassword(credentialsId: 'esp51_ssh_credentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {

                    script {
                        remote.user = USERNAME
                        remote.password = PASSWORD
                        remote.allowAnyHosts = true
                    }

                    sshCommand remote: remote, command: "ls"
                    sshCommand remote: remote, command: "docker stop esp51-temperature"
                    sshCommand remote: remote, command: "docker rm esp51-temperature"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp51/temperature"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp51/temperature"
                    sshCommand remote: remote, command: "docker create -p 8000:8080 --name esp51-temperature 192.168.160.48:5000/esp51/temperature"
                    sshCommand remote: remote, command: "docker start esp51-temperature"
                }
            }
        }
    }
}