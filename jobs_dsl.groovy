//********** FOLDERS **********\\
folder('Whanos base images') {
    displayName('Whanos base images')
    description('Base images fo whanos.')
}

folder('Projects') {
    displayName('Projects')
    description('Projects using whanos.')
}

folder('Tools') {
    displayName('Tools')
    description('Tools for whanos.')
}

//************ WHANOS BASE IMAGES BUILD JOB ***************\\
freeStyleJob('Whanos base images/whanos-javascript') {
    description('Build javascript whanos-base docker image')
    steps {
        dockerBuildAndPublish {
            repositoryName('whanos-javascript')
            dockerHostURI('tcp://docker:2376')
            skipPush(true)
            forcePull(true)
            dockerfileDirectory('/images/javascript/Dockerfile.base')
        }
    }
}   

freeStyleJob('Whanos base images/whanos-python') {
    description('Build python whanos-base docker image')
    steps {
        dockerBuildAndPublish {
            repositoryName('whanos-python')
            dockerHostURI('tcp://docker:2376')
            skipPush(true)
            forcePull(true)
            dockerfileDirectory('/images/python/Dockerfile.base')
        }
    }
}   

freeStyleJob('Whanos base images/whanos-c') {
    description('Build javascript whanos-c docker image')
    steps {
        dockerBuildAndPublish {
            repositoryName('whanos-c')
            dockerHostURI('tcp://docker:2376')
            skipPush(true)
            forcePull(true)
            dockerfileDirectory('/images/c/Dockerfile.base')
        }
    }
}   

freeStyleJob('Whanos base images/whanos-java') {
    description('Build javascript whanos-java docker image')
    steps {
        dockerBuildAndPublish {
            repositoryName('whanos-java')
            dockerHostURI('tcp://docker:2376')
            skipPush(true)
            forcePull(true)
            dockerfileDirectory('/images/java/Dockerfile.base')
        }
    }
}   

// ******** BUILD ALL BASE IMAGES JOB *************\\

freeStyleJob('Build all base images') {
    description('Build all whanos base images')
    publishers {
        downstream('Whanos base images/whanos-c', 'SUCCESS')
        downstream('Whanos base images/whanos-java', 'SUCCESS')
        downstream('Whanos base images/whanos-javascript', 'SUCCESS')
        downstream('Whanos base images/whanos-python', 'SUCCESS')
    }
}

//************** LINK PROJECT ****************\\
// freeStyleJob('link-project') {
//     parameters {
//         stringParam('GIT_REPOSITORY_URL', null, 'Git URL of the repository to containerize')
//     }
//     steps {
//         dsl {
//             text('''job("build_repository") {
//                 scm {
//                     github("$GIT_REPOSITORY_URL", 'master')
//                 }
//                 steps {
//                     shell('cd app && /containerize_app.sh')
//                 }
//             }''')
//         }
//     }
// }

freeStyleJob('link-project') {
    steps {
        shell('cd /var/jenkins_home/workspace/Tools/clone-repository/repository/app && /containerize_app.sh')
    }
}

freeStyleJob('Tools/clone-repository') {
    parameters {
        stringParam('GIT_REPOSITORY_URL', null, 'Git URL of the repository to clone')
    }
    wrappers {
        preBuildCleanup()
        release {
            steps {
                shell('git clone $GIT_REPOSITORY_URL repository')
            }
        }
    }
}
