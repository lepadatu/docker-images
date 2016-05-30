def deploy(id) {
    //unstash 'war'
    println "Current dir:"
    sh "pwd"
    def staging = id.equals("staging")
    def production = id.equals("production")
    if (!staging && !production) {
        sh "pwd"
/*        sh "cp x.war OracleWebLogic/samples/1221-demo-qa/${id}.war"
        "cd".execute(null, new File("OracleWebLogic/samples/1221-demo-qa"))
    }
    "cd".execute(null, new File("OracleWebLogic/samples/1221-demo"))

    sh "cp x.war /tmp/webapps/${id}.war" */
    }
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
