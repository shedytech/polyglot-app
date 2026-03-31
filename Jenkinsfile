pipeline {
    agent any

    environment {
        DOCKERHUB_REPO = "shedyvince29"
        VERSION = "v1"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/shedytech/polyglot-app.git'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh '''
                    echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                    '''
                }
            }
        }

        stage('Build Images') {
            steps {
                sh '''
                echo "Building Java Image..."
                docker build -t $DOCKERHUB_REPO/java-service:$VERSION ./java-service

                echo "Building Node Image..."
                docker build -t $DOCKERHUB_REPO/node-service:$VERSION ./node-service

                echo "Building Python Image..."
                docker build -t $DOCKERHUB_REPO/python-service:$VERSION ./python-service
                '''
            }
        }

        stage('Push Images') {
            steps {
                sh '''
                echo "Pushing Java Image..."
                docker push $DOCKERHUB_REPO/java-service:$VERSION

                echo "Pushing Node Image..."
                docker push $DOCKERHUB_REPO/node-service:$VERSION

                echo "Pushing Python Image..."
                docker push $DOCKERHUB_REPO/python-service:$VERSION
                '''
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withCredentials([file(credentialsId: 'kubeconfig', variable: 'KUBECONFIG')]) {
                    sh '''
                    echo "Deploying to Kubernetes..."
                    kubectl apply -f k8s/
                    '''
                }
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully!'
        }
        failure {
            echo '❌ Pipeline failed!'
        }
    }
}

