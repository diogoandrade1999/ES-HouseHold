def remote = [:]
remote.host = "192.168.160.87"
remote.name = "playground"

pipeline {
    agent any
    tools {
        maven 'maven36'
        jdk 'jdk11'
    }

    stages {
        stage ('Start') {
            steps {
                dir('simulator'){
                    sh '''
                        echo "PATH = ${PATH}"
                        echo "M2_HOME = ${M2_HOME}"
                    '''
                }
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
                dir('simulator'){
                    sh 'mvn -Dmaven.test.failure.ignore=true install' 
                }
                dir('temperature'){
                    sh 'mvn -Dmaven.test.failure.ignore=true install' 
                }
            }
            post {
                success {
                    dir('simulator'){
                        junit 'target/surefire-reports/**/*.xml' 
                    }
                    dir('temperature'){
                        junit 'target/surefire-reports/**/*.xml' 
                    }
                }
            }
        }
        stage ('Deploy') {
            steps{
                sh 'mvn deploy -f ./simulator/pom.xml -s settings.xml' 
                sh 'mvn deploy -f ./temperature/pom.xml -s settings.xml' 
            }
        }
        stage('Publish'){
            steps{
                script{
                    docker.withRegistry("http://192.168.160.48:5000") {
                        def simulatorApp = docker.build("esp51/simulator", "./simulator")
                        simulatorApp.push()

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

                    sshCommand remote: remote, command: "docker stop esp51-simulator"
                    sshCommand remote: remote, command: "docker rm esp51-simulator"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp51/simulator"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp51/simulator"
                    sshCommand remote: remote, command: "docker create -p 51010:51010 --name esp51-simulator 192.168.160.48:5000/esp51/simulator"
                    sshCommand remote: remote, command: "docker start esp51-simulator"

                    sshCommand remote: remote, command: "docker stop esp51-temperature"
                    sshCommand remote: remote, command: "docker rm esp51-temperature"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp51/temperature"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp51/temperature"
                    sshCommand remote: remote, command: "docker create -p 51020:51020 --name esp51-temperature 192.168.160.48:5000/esp51/temperature"
                    sshCommand remote: remote, command: "docker start esp51-temperature"
                }
            }
        }
    }
}