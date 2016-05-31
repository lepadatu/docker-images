def deploy(id) {
    unstash 'war'
    if (id.equals("staging") || id.equals("production") || id.equals("qa")) {
        sh "echo PWD: `pwd`"
        sh "cp x.war OracleWebLogic/samples/1221-appdeploy-${id}/${id}.war"
        sh "docker build -t 1221-appdeploy-${id} OracleWebLogic/samples/1221-appdeploy-${id}/"
    }

}

def undeploy(id) {
    sh "rm x.war"
}

def runWithServer(body) {
    def id = 'qa'.toString()
    deploy id
    try {
        body.call id
    } finally {
        undeploy id
    }
}

this
