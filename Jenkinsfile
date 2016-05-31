WLUrl = 'http://localhost:8001'

def servers

stage 'Prerequisites'
node {
	checkout scm
	/*dir('./OracleJDK/java-8') {
	    sh "cp /opt/jre/server-jre-8u92-linux-x64.tar.gz ."
        sh "./build.sh"
    }
    dir('./OracleWebLogic/dockerfiles') {
        sh "cp /opt/weblogic/fmw_12.2.1.0.0_wls_quick_Disk1_1of1.zip ./12.2.1/"
        sh "./buildDockerImage.sh -v 12.2.1 -d"
    }
    dir('./OracleWebLogic/samples/1221-domain') {
        println "Current dir:"
        sh "pwd"
        sh "docker build --build-arg ADMIN_PASSWORD=luxoftadmin1 -t 1221-domain ."
    }*/
}

stage 'Build'
node {
    dir('./demo') {
        mvn 'clean package'
        dir('target') {stash name: 'war', includes: 'x.war'}
    }
}

stage 'QA'
node {
    servers = load 'demo/servers.groovy'
    parallel(longerTests: {
        runTests(servers, 30)
    }, quickerTests: {
        runTests(servers, 20)
    })
}

def mvn(args) {
    sh "${tool 'M3'}/bin/mvn ${args}"
}


def runTests(servers, duration) {
    node {
        checkout scm
        servers.runWithServer {id ->
            mvn "-f sometests test -Durl=${WLUrl}/${id}/ -Dduration=${duration}"
        }
    }
}
