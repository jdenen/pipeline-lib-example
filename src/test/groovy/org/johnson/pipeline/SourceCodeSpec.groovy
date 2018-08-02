import spock.lang.*
import org.johnson.pipeline.SourceCode

class SourceCodeSpec extends Specification {
    interface PipelineScript {
        def scm = "scm"
        def env = [:]
        def checkout(scm)
    }

    def script = Mock(PipelineScript)

    def 'checks out SCM object and sets COMMIT_SHA property'() {
        given: 'a pipeline executing against a code change'
        def subject = new SourceCode(script)

        when: 'source code is checked out'
        subject.checkout()

        then: 'the pipeline script checks out its SCM object'
        1 * script.checkout("scm") >> [GIT_COMMIT: "abcdef0"]
        script.env.COMMIT_SHA == "abcdef0"
    }
}
