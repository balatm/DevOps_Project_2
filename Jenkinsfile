pipeline {
    agent any
    environment {
        mavenHome = tool 'Maven 3'
        jdkHome = tool 'JDK 17'
    }
    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/balatm/DevOps_Project_2.git', branch: 'master'
            }
        }
        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh "${mavenHome}/bin/mvn clean package -DskipTests"
                    } else {
                        bat "\"${mavenHome}\\bin\\mvn.cmd\" clean package -DskipTests"
                    }
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh "${mavenHome}/bin/mvn test"
                    } else {
                        bat "\"${mavenHome}\\bin\\mvn.cmd\" test"
                    }
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("balatm/springboot-app:${env.BUILD_NUMBER}")
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'balatm_docker') {
                        docker.image("balatm/springboot-app:${env.BUILD_NUMBER}").push()
                    }
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Use the kubeconfig stored as a file credential with id 'kubeconfig'
                    withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                        if (isUnix()) {
                            sh 'kubectl apply -f kubernetes/deployment.yaml --record'
                        } else {
                            bat 'kubectl apply -f kubernetes\\deployment.yaml --record'
                        }
                    }
                }
            }
        }
    }
}
