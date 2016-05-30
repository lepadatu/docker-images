stage 'Dev'
node {
	checkout scm
	dir('./OracleJDK/java-8') {
	    sh "cp /opt/jre/server-jre-8u92-linux-x64.tar.gz ."
        sh "docker build -t oracle/jdk:8 ."
    }
    dir('./OracleWebLogic/dockerfiles') {
        sh "cp /opt/weblogic/fmw_12.2.1.0.0_wls_quick_Disk1_1of1.zip ./12.2.1/"
        sh "docker build -v 12.2.1 -d"
    }
}
