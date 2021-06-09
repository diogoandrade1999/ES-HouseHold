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
        stage('Build And Unit Tests') {
            steps {
                dir('household'){
                    sh 'mvn -Dmaven.test.failure.ignore=true -Dintegration-tests.skip=true clean install' 
                }
                dir('simulator'){
                    sh 'mvn -Dmaven.test.failure.ignore=true -Dintegration-tests.skip=true clean install' 
                }
                dir('temperature'){
                    sh 'mvn -Dmaven.test.failure.ignore=true -Dintegration-tests.skip=true clean install' 
                }
                dir('luminosity'){
                    sh 'mvn -Dmaven.test.failure.ignore=true -Dintegration-tests.skip=true clean install' 
                }
                dir('humidity'){
                    sh 'mvn -Dmaven.test.failure.ignore=true -Dintegration-tests.skip=true clean install' 
                }
            }
            post {
                success {
                    dir('household'){
                        junit 'target/surefire-reports/**/*.xml' 
                    }
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
                        sh 'mvn -Dunit-tests.skip=true verify'
                    }
                }
            }
            post {
                success {
                    dir('temperature'){
                        cucumber buildStatus: null, fileIncludePattern: '**/cucumber.json', jsonReportDirectory: 'target', sortingMethod: 'ALPHABETICAL'
                    }
                }
            }
        }
        stage ('Deploy') {
            steps{
                sh 'mvn deploy -DskipTests -f ./household/pom.xml -s settings.xml' 
                sh 'mvn deploy -DskipTests -f ./simulator/pom.xml -s settings.xml' 
                sh 'mvn deploy -DskipTests -f ./temperature/pom.xml -s settings.xml' 
                sh 'mvn deploy -DskipTests -f ./luminosity/pom.xml -s settings.xml' 
                sh 'mvn deploy -DskipTests -f ./humidity/pom.xml -s settings.xml' 
            }
        }
        stage('Publish'){
            steps{
                script{
                    docker.withRegistry("http://192.168.160.48:5000") {
                        def householdApp = docker.build("esp51/household", "./household")
                        householdApp.push()

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
                    sshCommand remote: remote, command: "docker create -p 51040:51040 --name esp51-humidity 192.168.160.48:5000/esp51/humidity"
                    sshCommand remote: remote, command: "docker start esp51-humidity"

                    sshCommand remote: remote, command: "docker stop esp51-household || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rm esp51-household || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker rmi 192.168.160.48:5000/esp51/household || echo 'Do not have that image'"
                    sshCommand remote: remote, command: "docker pull 192.168.160.48:5000/esp51/household"
                    sshCommand remote: remote, command: "docker create -p 51000:51000 --name esp51-household 192.168.160.48:5000/esp51/household"
                    sshCommand remote: remote, command: "docker start esp51-household"
                }
            }
        }
    }
}