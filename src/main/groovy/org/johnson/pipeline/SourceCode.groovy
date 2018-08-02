package org.johnson.pipeline

class SourceCode implements Serializable {
    def script

    SourceCode(script) {
        this.script = script
    }

    def checkout() {
        script.deleteDir()
        script.env.COMMIT_SHA = script.checkout(script.scm).GIT_COMMIT
    }
}
