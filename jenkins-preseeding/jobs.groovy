String[] services = ['frontend','user','payment','order','notification','inventory'];
String[] env   = ['Development','Test','Production'];

for(envname in env) {
    for(pipeline in services) {
        String pipeline_capitalized = pipeline.substring(0, 1).toUpperCase() + pipeline.substring(1);

	// Lower-casing environment names, replacing spaces and dots.
	String envname_lowercased = envname.substring(0,envname.length()).toLowerCase().replace(" ", "-").replace(".", "-");
	print "Configuring " + envname + " " + pipeline_capitalized + "\n"

        pipelineJob(envname + ' Configure ' + pipeline_capitalized) {
            description("Building " + pipeline_capitalized + ' for ' + envname)
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
    }
}
