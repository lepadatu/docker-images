def deploy(id) {
    unstash 'war'
    def staging = id.equals("staging")
    def production = id.equals("production")
    if (!staging && !production) {
        sh "cp x.war OracleWebLogic/samples/1221-appdeploy-qa/container-scripts/qa.war"
        sh "docker build -t 1221-appdeploy-qa OracleWebLogic/samples/1221-appdeploy-qa/"
    }
    if (staging || production) {
        sh "cp x.war OracleWebLogic/samples/1221-demo-${id}/${id}.war"
        sh "docker build -t 1221-appdeploy-${id} OracleWebLogic/samples/1221-appdeploy-${id}/"
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
