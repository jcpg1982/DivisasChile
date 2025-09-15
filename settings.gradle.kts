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
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
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
