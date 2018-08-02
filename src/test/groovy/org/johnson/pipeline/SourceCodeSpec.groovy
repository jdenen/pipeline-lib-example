import spock.lang.*
import org.johnson.pipeline.SourceCode

class SourceCodeSpec extends Specification {
    interface PipelineScript {
        def scm = "scm"
        def env = [:]
        def checkout(scm)
        def deleteDir()
    }

    def script = Mock(PipelineScript)

    def 'checks out SCM object into empty directory'() {
        given: 'a pipeline executing against a code change'
        def subject = new SourceCode(script)

        when: 'source code is checked out'
        subject.checkout()

        then: 'the workspace is emptied'
        1 * script.deleteDir()

        then: 'the pipeline checks out its SCM object'
        1 * script.checkout("scm") >> [GIT_COMMIT: "abcdef0"]

        then: "the COMMIT_SHA property is set to source's HEAD"
        script.env.COMMIT_SHA == "abcdef0"
    }
}
