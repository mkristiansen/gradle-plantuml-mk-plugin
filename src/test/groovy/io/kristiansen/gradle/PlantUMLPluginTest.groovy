package io.kristiansen.gradle

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.*
import org.junit.rules.TemporaryFolder
import org.gradle.testfixtures.ProjectBuilder

import static org.gradle.testkit.runner.TaskOutcome.*

/**
 * Contains functional tests that use the GradleRunner to run the plugin's task in a controlled environment.
 * Reference:
 * https://docs.gradle.org/3.5/userguide/test_kit.html#sec:functional_testing_with_the_gradle_runner
 * Example 45.3
 */
class PlantUMLPluginTest {
    @Rule
    public final TemporaryFolder testProjectDir = new TemporaryFolder();

    private File build_gradle
    private File sample_puml
    private File sample_svg
    private File sample_png

    @Before
    public void setup() {
        // Prepare build.gradle
        sample_puml = testProjectDir.newFolder('assets')
        sample_puml = testProjectDir.newFile('assets/sample.puml')
        sample_puml << """@startuml

                title narcissism as a stable configuration

                participant enamored as A
                participant enamored as B
                participant narcissist as C

                A -> B: likes
                B --> A: no reciprocation
                B -> C: likes
                C --> B: no reciprocation
                C -> C: likes
                C -> C: reciprocated
                @enduml"""
        sample_svg = testProjectDir.newFile("assets/sample.svg")
        sample_png = testProjectDir.newFile("assets/sample.png")

        build_gradle = testProjectDir.newFile('build.gradle')
        build_gradle << 'plugins { id "io.kristiansen.gradle.PlantUMLPlugin"}\n'
    }

    /**
     * Helper method that runs a Gradle task in the testProjectDir
     * @param arguments the task arguments to execute
     * @param isSuccessExpected boolean representing whether or not the build is supposed to fail
     * @return the task's BuildResult
     */
    private BuildResult gradle(boolean isSuccessExpected, String[] arguments = ['tasks']) {
        arguments += '--stacktrace'
        def runner = GradleRunner.create()
                .withArguments(arguments)
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withDebug(true)
        return isSuccessExpected ? runner.build() : runner.buildAndFail()
    }

    private BuildResult gradle(String[] arguments = ['tasks']) {
        gradle(true, arguments)
    }

    @Test
    public void Tasks() {
        def result = gradle('tasks')
        assert result.task(":tasks").outcome == SUCCESS
    }

    @Test
    public void RenderPlantUML() {
        sample_svg.delete()
        sample_png.delete()

        def result = gradle('renderPlantUml')

        assert result.task(":renderPlantUml").outcome == SUCCESS
        assert sample_png.exists() == true
        assert sample_svg.exists() == true
    }

    @Test
    public void CleanPlantUML() {
        def result1 = gradle('renderPlantUml')
        assert result1.task(":renderPlantUml").outcome == SUCCESS
        def result = gradle('cleanPlantUml')
        assert result.task(":cleanPlantUml").outcome == SUCCESS
        assert sample_png.exists() == false
        assert sample_svg.exists() == false
    }
}
