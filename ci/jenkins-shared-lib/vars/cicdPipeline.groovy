def call() {
    pipeline {
        agent any

        environment {
            IMAGE_NAME = "mnagy156/ivolve-app"
            TAG = "v1.0.${BUILD_NUMBER}"
        }

        stages {
            stage('Build Image') {
                steps {
                    script {
                        sh 'docker build -t $IMAGE_NAME:$TAG .'
                    }
                }
            }

            stage('Scan Image') {
                steps {
                    script {
                        echo "Scanning image... (stub)"
                        // Integrate Trivy/Anchore/etc. later
                    }
                }
            }

            stage('Push Image') {
                steps {
                    script {
                        withCredentials([usernamePassword(
                            credentialsId: 'dockerhub-creds',
                            usernameVariable: 'DOCKER_USER',
                            passwordVariable: 'DOCKER_PASS'
                        )]) {
                            sh '''
                            echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                            docker push $IMAGE_NAME:$TAG
                            '''
                        }
                    }
                }
            }

            stage('Delete Image Locally') {
                steps {
                    script {
                        sh 'docker rmi $IMAGE_NAME:$TAG || true'
                    }
                }
            }

            stage('Update Manifests') {
                steps {
                    script {
                        sh '''
                        sed -i "s|image: .*|image: $IMAGE_NAME:$TAG|" k8s/deployment.yaml
                        '''
                    }
                }
            }

            stage('Push Manifests') {
                steps {
                    script {
                        sh '''
                        git config --global user.name "jenkins"
                        git config --global user.email "jenkins@ci.local"
                        git add k8s/deployment.yaml
                        git commit -m "Update deployment image to $TAG"
                        git push origin main
                        '''
                    }
                }
            }
        }
    }
}
