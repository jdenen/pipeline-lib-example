library(
    identifier: 'pipeline-lib@master',
    retriever: modernSCM([$class: 'GitSCMSource',
                          remote: 'https://github.com/jdenen/pipeline-lib'])
)

node('master') {
    ansiColor('xterm') {
        stage('Checkout') {
            example.checkout()
        }

        stage('Compile') {
            docker.image('gradle:slim').inside {
                echo "GIT_SHA: ${env.COMMIT_SHA}"
                sh 'gradle build --no-daemon'
            }
        }
    }
}
