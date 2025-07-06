pipeline {
    agent any

    environment {
        IMAGE_NAME = 'activitytracker:latest'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/DeepanshuOP/IBMProject.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 8080:8080 $IMAGE_NAME'
            }
        }
    }

    post {
        success {
            echo 'Build and deployment successful!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
