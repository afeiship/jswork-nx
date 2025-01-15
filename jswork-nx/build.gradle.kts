plugins {
    id("java-library")
    id("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = "com.github.afeiship.jsw_nx"
            artifactId = "jsw_nx"
            version = "0.0.4"

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