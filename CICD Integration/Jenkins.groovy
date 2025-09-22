pipeline {
    agent any

    stages {
        stage('Deploy with Ansible') {
            steps {
                ansiblePlaybook(
                    playbook: 'deploy.yml',
                    inventory: 'inventory.ini',
                    extras: '-vvv' // optional: verbose output
                )
            }
        }
    }
}
