@Library('my-shared-library') _

pipeline {
     agent { label 'jenkins-agent-1' }

        stages {
        stage('Pipeline Steps') {
            steps {
                buildImage()
                scanImage()
                pushImage()
                deleteImageLocally()
                updateManifests()
                pushManifests()
            }
        }
   }
}