package io.kristiansen.gradle.plantuml.traits

import org.apache.commons.io.FilenameUtils
import java.nio.file.Path

trait PathHelper {

    Path assetsPath = project.projectDir.toPath().resolve('assets/')

    Path getDestinationPath(File sourceFile, String extension) {

        String baseName = FilenameUtils.getBaseName(sourceFile.name)
        String destName = "${baseName}"

        assetsPath.resolve(destName + extension)
    }
}