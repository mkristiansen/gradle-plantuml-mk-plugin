package io.kristiansen.gradle.plantuml.tasks

import io.kristiansen.gradle.plantuml.traits.PathHelper
import org.gradle.api.tasks.Delete

import java.nio.file.Files
import java.nio.file.Path

class CleanPlantUmlTask extends Delete implements PathHelper {

    CleanPlantUmlTask() {
        for (Path puml : Files.newDirectoryStream(assetsPath, '*.puml')) {
            delete getDestinationPath(puml.toFile(), '.svg').toFile()
            delete getDestinationPath(puml.toFile(), '.png').toFile()
        }
    }
}
