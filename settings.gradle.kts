pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "Conversor_Divisa"
include(":app")
include(":core")
include(":feature")
include(":core:data")
include(":core:domain")
include(":core:design")
include(":feature:login")
include(":feature:home")
include(":core:preferences")
include(":core:network")
include(":core:sqlite")
include(":core:common")
include(":core:model")
include(":feature:register")
