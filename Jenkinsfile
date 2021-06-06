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
                dir('luminosity'){
                    sh '''
                        echo "PATH = ${PATH}"
                        echo "M2_HOME = ${M2_HOME}"
                    '''
                }
                dir('humidity'){
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
                dir('luminosity'){
                    sh 'mvn -Dmaven.test.failure.ignore=true install' 
                }
                dir('humidity'){
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
                    dir('luminosity'){
                        junit 'target/surefire-reports/**/*.xml' 
                    }
                    dir('humidity'){
                        junit 'target/surefire-reports/**/*.xml' 
                    }
                }
            }
        }
         stage('Integration tests') {
            steps {
                script {
                    dir('temperature'){
                        def mvnHome = tool 'Maven 3.5.2'
                        if (isUnix()) {
                            sh "'${mvnHome}/bin/mvn'  verify -Dunit-tests.skip=true"
                        } else {
                            bat(/"${mvnHome}\bin\mvn" verify -Dunit-tests.skip=true/)
                        }
                    }

                }
                cucumber buildStatus: null, fileIncludePattern: '**/cucumber.json', jsonReportDirectory: 'target', sortingMethod: 'ALPHABETICAL'
            }
        }
        stage ('Deploy') {
            steps{
                sh 'mvn deploy -f ./simulator/pom.xml -s settings.xml' 
                sh 'mvn deploy -f ./temperature/pom.xml -s settings.xml' 
                sh 'mvn deploy -f ./luminosity/pom.xml -s settings.xml' 
                sh 'mvn deploy -f ./humidity/pom.xml -s settings.xml' 
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

                        def luminosityApp = docker.build("esp51/luminosity", "./luminosity")
                        luminosityApp.push()

                        def humidityApp = docker.build("esp51/humidity", "./humidity")
                        humidityApp.push()
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

                    sshCommand remote: remote, command: "docker stop esp51-simulator || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rm esp51-simulator || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp51/simulator || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp51/simulator"
                    sshCommand remote: remote, command: "docker create -p 51010:51010 --name esp51-simulator 192.168.160.48:5000/esp51/simulator"
                    sshCommand remote: remote, command: "docker start esp51-simulator"

                    sshCommand remote: remote, command: "docker stop esp51-temperature || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rm esp51-temperature || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp51/temperature || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp51/temperature"
                    sshCommand remote: remote, command: "docker create -p 51020:51020 --name esp51-temperature 192.168.160.48:5000/esp51/temperature"
                    sshCommand remote: remote, command: "docker start esp51-temperature"

                    sshCommand remote: remote, command: "docker stop esp51-luminosity || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rm esp51-luminosity || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp51/luminosity || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp51/luminosity"
                    sshCommand remote: remote, command: "docker create -p 51030:51030 --name esp51-luminosity 192.168.160.48:5000/esp51/luminosity"
                    sshCommand remote: remote, command: "docker start esp51-luminosity"

                    sshCommand remote: remote, command: "docker stop esp51-humidity || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rm esp51-humidity || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp51/humidity || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp51/humidity"
                    sshCommand remote: remote, command: "docker create -p 51030:51030 --name esp51-humidity 192.168.160.48:5000/esp51/humidity"
                    sshCommand remote: remote, command: "docker start esp51-humidity"
                }
            }
        }
    }
}