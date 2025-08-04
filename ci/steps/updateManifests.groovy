def call() {
    stage('Update Manifests ') {
        echo 'Updating Kubernetes manifests (YAML files) using sed...'
        sh """
          echo "Updating image tag in manifests..."
        """
    }
}
return this
