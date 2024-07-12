plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
}

tasks.register<Jar>("generateSourcesJar") {
    from(project.android.sourceSets.getByName("main").java.srcDirs)
    archiveClassifier.set("sources")
}



android {
    namespace = "com.lazyliuzy.lzyutils"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    publishing {
        singleVariant("release")
        singleVariant("debug")
    }
}

afterEvaluate {
    publishing {
        publications {
            // 创建名为 "release" 的Maven发布。
            create<MavenPublication>("release") {
                // 应用于release构建变体的组件。
                from(components["release"])
                // 设置groupId、artifactId和version。
                groupId = "com.github.0728lzy"
                artifactId = "easylzy"
                version = "1.0.0"
            }

            // 创建名为 "debug" 的Maven发布。
            create<MavenPublication>("debug") {
                // 应用于debug构建变体的组件。
                from(components["debug"])
                // 设置groupId、artifactId和version。
                groupId = "com.github.0728lzy"
                artifactId = "easylzy"
                version = "1.0.0"
            }
        }
    }
}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}