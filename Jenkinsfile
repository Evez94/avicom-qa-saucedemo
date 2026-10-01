pipeline {
    agent any

    tools {
        // Jenkins-də konfiqurasiya olunmuş JDK və Maven adları
        maven 'Maven-3.9'
        jdk 'JDK-19'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out source code from Git repository...'
                checkout scm
            }
        }

        stage('Compile & Validate') {
            steps {
                echo 'Compiling project and validating dependencies...'
                sh 'mvn test-compile'
            }
        }

        stage('Execute Automated Tests') {
            steps {
                echo 'Running Selenium TestNG Test Suite...'
                // Headless arqumenti ilə TestNG testlərini icra edirik
                sh 'mvn clean test -Dheadless=true'
            }
        }
    }

    post {
        always {
            echo 'Archiving Test Reports...'
            // TestNG / Surefire hesabatlarını saxlayır
            junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
            
            // HTML hesabatı (Surefire/Allure) saxlamaq üçün
            archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: true
        }
        success {
            echo 'SUCCESS: All automated tests passed successfully!'
        }
        failure {
            echo 'FAILURE: Test execution failed. Please check reports.'
        }
    }
}