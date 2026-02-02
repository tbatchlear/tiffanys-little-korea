package com.tiffanyslittlekorea.gradle

import org.gradle.api.Project

class Env {
    static Map<String, String> load(Project project) {
        def env = [:] as Map<String, String>
        def envFile = project.rootProject.file(".env")
        if (!envFile.exists()) return env

        envFile.eachLine { line ->
            def trimmed = line.trim()
            if (!trimmed || trimmed.startsWith("#")) return

            def idx = trimmed.indexOf("=")
            if (idx <= 0) return

            def key = trimmed.substring(0, idx).trim()
            def value = trimmed.substring(idx + 1).trim()
            env[key] = value
        }

        return env
    }
}
