def call() {
    stage('Push Manifests ') {
        withCredentials([usernamePassword(
            credentialsId: 'github-creds', 
            usernameVariable: 'GIT_USER', 
            passwordVariable: 'GIT_TOKEN'
        )]) {
            sh '''
				echo "Pushing manifests to GitHub..."
            '''
        }
    }
}
return this


