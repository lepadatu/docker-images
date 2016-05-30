def deploy(id) {
    "cd".execute(null, new File("OracleWebLogic/samples/1221-demo"))
    unstash 'war'
//    sh "cp x.war /tmp/webapps/${id}.war"
}

def undeploy(id) {
    sh "rm x.war"
}

def runWithServer(body) {
    def id = UUID.randomUUID().toString()
    deploy id
    try {
        body.call id
    } finally {
        undeploy id
    }
}

this
