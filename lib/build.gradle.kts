plugins {
    id("java-library")
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20210307")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.afeiship.jsw_nx"
            artifactId = "jsw_nx"
            version = "0.0.9"

            // 包含主编译输出
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
    }
}