import love.forte.gradle.common.core.project.setup

plugins {
    kotlin("multiplatform")
//    `miyoushe-multiplatform-maven-publish`
    kotlin("plugin.serialization")
//    `miyoushe-dokka-partial-configure`
}

setup(P)
if (isSnapshot()) {
    version = P.snapshotVersion.toString()
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
}

kotlin {
    explicitApi()
    applyDefaultHierarchyTemplate()

    sourceSets.configureEach {
        languageSettings {
//            optIn("love.forte.simbot.qguild.InternalApi")
            optIn("kotlin.js.ExperimentalJsExport")
        }
    }

    jvm {
        withJava()
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
                javaParameters = true
                freeCompilerArgs = freeCompilerArgs + listOf("-Xjvm-default=all")
            }
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

    js(IR) {
        useEsModules()
        nodejs()
        generateTypeScriptDefinitions()
        binaries.library()
        compilations.all {
            // Enables ES6 classes generation
            kotlinOptions {
                useEsClasses = true
            }
        }
    }


//    val mainPresets = mutableSetOf<org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet>()
//    val testPresets = mutableSetOf<org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet>()

    // see https://kotlinlang.org/docs/native-target-support.html
    // Tier 1
    macosX64()
    macosArm64()
    iosSimulatorArm64()
    iosX64()

    // Tier 2
    linuxX64()
    linuxArm64()
    watchosSimulatorArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()
    tvosSimulatorArm64()
    tvosX64()
    tvosArm64()
    iosArm64()

    // Tier 3
//    androidNativeArm32()
//    androidNativeArm64()
//    androidNativeX86()
//    androidNativeX64()
    mingwX64()
//    watchosDeviceArm64()

    sourceSets {
        commonMain {
            dependencies {
                compileOnly(simbotAnnotations)
                api(simbotRequestorCore)
                api(libs.kotlinx.coroutines.core)
                api(libs.ktor.client.core)
                api(libs.ktor.client.contentNegotiation)
                api(libs.ktor.serialization.kotlinx.json)
                api(libs.kotlinx.serialization.json)
                api(libs.kotlinx.serialization.protobuf)
                api(simbotLogger)
            }
        }

        commonTest {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.ktor.client.ws)
            }
        }

        jvmMain {
            dependencies {
                compileOnly(simbotApi) // use @Api4J annotation
                compileOnly(simbotAnnotations) // use @Api4J annotation
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.ktor.client.cio)
                implementation(simbotApi) // use @Api4J annotation
                implementation(libs.log4j.api)
                implementation(libs.log4j.core)
                implementation(libs.log4j.slf4j2Impl)
            }
        }

        jsMain {
            dependencies {
                api(simbotAnnotations)
                api(libs.ktor.client.js)
            }
        }
        jsTest {
            dependencies {
                api(libs.ktor.client.js)
            }
        }

        mingwTest {
            dependencies {
                implementation(libs.ktor.client.winhttp)
            }
        }

        linuxTest {
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }

        appleTest {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }

}

// suppress all?
//tasks.withType<org.jetbrains.dokka.gradle.DokkaTaskPartial>().configureEach {
//    dokkaSourceSets.configureEach {
//        suppress.set(true)
//        perPackageOption {
//            suppress.set(true)
//        }
//    }
//}

