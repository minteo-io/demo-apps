// scripts/jobs.groovy

job('example-freestyle-job') {
    description('This is an example Freestyle job created by the Job DSL plugin.')
    scm {
        git('https://github.com/your-repo.git', 'main')
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell('echo "Building the project..."')
    }
    publishers {
        archiveArtifacts('**/target/*.jar')
    }
}

pipelineJob('example-pipeline-job') {
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
