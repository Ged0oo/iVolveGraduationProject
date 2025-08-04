def call() {
    stage('Build Image ') {
        echo ' Building Docker image...'
        sh """docker build -t mnagy156/flask-app:$BUILD_NUMBER ."""
		echo ' Docker image built successfully.'
		currentBuild.displayName = "#${BUILD_NUMBER} - myimg"
		currentBuild.description = "Built Docker image for myimg"
    }
}
return this
