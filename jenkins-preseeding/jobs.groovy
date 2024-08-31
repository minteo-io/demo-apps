// scripts/jobs.groovy

job('Prod-freestyle-job') {
    description('This is an example Freestyle job created by the Job DSL plugin.')
    scm {
        git('https://github.com/minteo-io/demo-apps.git', 'main')
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell('echo "Building the project..."')
    }
    publishers {
        archiveArtifacts('**/demo/prod/*.jar')
    }
}

pipelineJob('Prod-pipeline-job') {
    description('This is an example Pipeline job created by the Job DSL plugin.')
    definition {
        cps {
            script("""
                pipeline {
                    agent any
                    stages {
                        stage('Build') {
                            steps {
                                echo 'Building...'
                            }
                        }
                        stage('Test') {
                            steps {
                                echo 'Testing...'
                            }
                        }
                        stage('Deploy') {
                            steps {
                                echo 'Deploying...'
                            }
                        }
                    }
                }
            """.stripIndent())
            sandbox(true)
        }
    }
}
