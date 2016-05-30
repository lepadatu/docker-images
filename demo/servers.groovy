def deploy(id) {
    unstash 'war'
    def staging = id.equals("staging")
    def production = id.equals("production")
    if (!staging && !production) {
        sh "cp x.war OracleWebLogic/samples/1221-demo-qa/"
        sh "docker build -t 1221-demo-qa --build-arg ADMIN_PASSWORD=luxoftadmin1 OracleWebLogic/samples/1221-demo-qa/"
    }
    if (staging || production) {
        sh "cp x.war OracleWebLogic/samples/1221-demo-${id}/${id}.war"
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
