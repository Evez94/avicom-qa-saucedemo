pipeline {
    agent {
        docker {
            image 'markhobson/maven-chrome:jdk-19'
            args '-v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out source code from GitHub...'
                checkout scm
            }
        }

        stage('Compile Project') {
            steps {
                echo 'Compiling project and validating dependencies...'
                sh 'mvn test-compile'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                echo 'Executing TestNG Test Suite in Headless Docker Container...'
                sh 'mvn clean test -Dheadless=true'
            }
        }
    }

    post {
        always {
            echo 'Archiving test reports...'
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: true
        }
        success {
            echo 'SUCCESS: All tests passed successfully in CI/CD pipeline!'
        }
        failure {
            echo 'FAILURE: Tests failed. Please check the surefire reports.'
        }
    }
}