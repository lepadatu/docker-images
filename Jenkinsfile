stage 'Dev'
node {
	checkout scm
	dir('./OracleJDK/java-8') {
	    sh "cp /opt/jre/server-jre-8u92-linux-x64.tar.gz ."
        sh "docker build -t oracle/jdk:8 ."
    }
}
