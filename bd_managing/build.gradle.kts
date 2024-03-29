plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.7.1")

    compileOnly("org.projectlombok:lombok:1.18.30")


    implementation("org.flywaydb:flyway-core:9.22.3")


}

tasks.test {
    useJUnitPlatform()
}