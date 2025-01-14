plugins {
    id("com.android.library")
    id("maven-publish")
}

android {
    namespace = "com.github.afeiship.jsw_nx"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["release"]) // 发布 release 构建的产物
            groupId = "com.github.afeiship"
            artifactId = "jsw-nx"
            version = "0.0.3"
        }
    }
}
