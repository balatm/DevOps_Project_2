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
                    sh "${mavenHome}/bin/mvn clean package -DskipTests"
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh "${mavenHome}/bin/mvn test"
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
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        docker.image("balatm/springboot-app:${env.BUILD_NUMBER}").push()
                    }
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    kubernetesDeploy(configs: 'kubernetes/deployment.yaml', kubeconfigId: 'kubeconfig')
                }
            }
        }
    }
}
