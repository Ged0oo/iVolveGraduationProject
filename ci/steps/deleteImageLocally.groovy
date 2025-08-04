def call() {
    stage('Delete Image Locally ') {
        echo ' Deleting local Docker image...'
        sh """ docker rmi mnagy156/flask-app:$BUILD_NUMBER|| true"""
		echo ' Local Docker image deleted successfully.'
		currentBuild.displayName = "#${BUILD_NUMBER} - myimg"
		currentBuild.description = "Deleted local Docker image for myimg"
    }
}
return this
