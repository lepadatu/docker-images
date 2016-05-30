stage 'Prerequisites'
node {
	checkout scm
	dir('./OracleJDK/java-8') {
	    sh "cp /opt/jre/server-jre-8u92-linux-x64.tar.gz ."
        sh "./build.sh"
    }
    dir('./OracleWebLogic/dockerfiles') {
        sh "cp /opt/weblogic/fmw_12.2.1.0.0_wls_quick_Disk1_1of1.zip ./12.2.1/"
        sh "./buildDockerImage.sh -v 12.2.1 -d"
    }
    dir('./OracleWebLogic/samples/1221-domain') {
        sh "./build.sh luxoftadmin1"
    }
}

stage 'Dev'
node {
    dir('./OracleWebLogic/samples/1221-demo') {

    }

}
