package io.kristiansen.gradle.plantuml.tasks

import io.kristiansen.gradle.plantuml.traits.PathHelper

import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import org.gradle.api.DefaultTask

import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.nio.file.Path

class RenderPlantUmlTask extends DefaultTask implements PathHelper {

    RenderPlantUmlTask() {
        Files.newDirectoryStream(assetsPath, '*.puml').each { Path puml ->
            inputs.file puml.toFile()
        }

        Files.newDirectoryStream(assetsPath, '*.puml').each { Path puml ->
            outputs.file getDestinationPath(puml.toFile(), '.svg').toFile()
            outputs.file getDestinationPath(puml.toFile(), '.png').toFile()
        }
    }

    def renderFile(String source, File path, FileFormatOption format) {
        Path projectPath = project.projectDir.toPath()
        SourceStringReader reader = new SourceStringReader(source)

        Path destPath = getDestinationPath(path, format.getFileFormat().getFileSuffix())

        println "Rendering ${projectPath.relativize(destPath)}"
        reader.generateImage(new FileOutputStream(destPath.toFile()), format)
    }

    @TaskAction
    def render() {

        Path projectPath = project.projectDir.toPath()
        for (File puml : inputs.files) {
            String relPumlPath = projectPath.relativize(puml.toPath()).toString()
            String pumlContent = new String(Files.readAllBytes(puml.toPath()), 'UTF-8')

            renderFile(pumlContent, puml, new FileFormatOption(FileFormat.SVG))
            renderFile(pumlContent, puml, new FileFormatOption(FileFormat.PNG))
        }

    }
}
