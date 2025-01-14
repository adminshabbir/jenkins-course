job('NodeJS Docker example') {
    scm {
        git('git://github.com/adminshabbir/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('adminshabbir')
            node / gitConfigEmail('ahamadshabbir2@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('adminshabbir/docker-nodejs-demo1')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('adminshabbir')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
