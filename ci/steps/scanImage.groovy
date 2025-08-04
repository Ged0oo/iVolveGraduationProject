def call() {
    stage('Scan Docker Image') {
        echo 'Starting Docker image scan using Trivy...'
        withCredentials([usernamePassword(
            credentialsId: 'dockerhub-creds', 
            usernameVariable: 'DOCKER_USER', 
            passwordVariable: 'DOCKER_PASS'
        )]) {
            script {
                try {
                    sh """
                        echo "Current directory: \$(pwd)"
                        echo "Logging into Docker Hub as \$DOCKER_USER"
                        echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin

                        echo "Running Trivy scan on mnagy156/flask-app:\$BUILD_NUMBER..."
                        trivy image --exit-code 0 --severity HIGH,CRITICAL mnagy156/flask-app:\$BUILD_NUMBER
                    """
                } catch (Exception e) {
                    echo "Trivy scan failed: ${e.message}"
                    currentBuild.result = 'UNSTABLE'
                }
            }
        }
    }
}
return this
