plugins {
	java
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	checkstyle
	jacoco
}


checkstyle{
	configFile = file("${project.rootDir}/config/checkstyle/google_checks.xml")
	toolVersion = "10.12.4"
	reportsDir = file("${project.buildDir}/checkstyle")
	isShowViolations = true;
	maxWarnings = 10;
	maxErrors = 1;
}
tasks.withType<Checkstyle>().configureEach {
	reports {
		xml.required = false
		html.required = true
	}
}

group = "com.devops"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}

}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.h2database:h2:2.2.220")
}

tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events("PASSED", "SKIPPED", "FAILED", "STANDARD_OUT", "STANDARD_ERROR")
	}
	finalizedBy(tasks.jacocoTestReport)
}



sourceSets {
	create("intTest") {
		compileClasspath += sourceSets.main.get().output
		runtimeClasspath += sourceSets.main.get().output
	}
}

val intTestImplementation by configurations.getting {
	extendsFrom(configurations.implementation.get())
}
val intTestRuntimeOnly by configurations.getting

configurations["intTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
	intTestImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
	intTestImplementation("org.springframework.boot:spring-boot-starter-test")
	intTestRuntimeOnly("org.junit.platform:junit-platform-launcher")
	intTestImplementation("io.rest-assured:rest-assured:5.3.2")
}

val integrationTest = task<Test>("integrationTest") {
	description = "Runs integration tests."
	group = "verification"

	testClassesDirs = sourceSets["intTest"].output.classesDirs
	classpath = sourceSets["intTest"].runtimeClasspath
	shouldRunAfter("test")

	useJUnitPlatform()

	testLogging {
		events("PASSED", "SKIPPED", "FAILED", "STANDARD_OUT", "STANDARD_ERROR")
	}
}

sourceSets {
	create("sysTest") {
		compileClasspath += sourceSets.main.get().output
		runtimeClasspath += sourceSets.main.get().output
	}
}

val sysTestImplementation by configurations.getting {
	extendsFrom(configurations.implementation.get())
}
val sysTestRuntimeOnly by configurations.getting

configurations["sysTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
	sysTestImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
	sysTestImplementation("org.springframework.boot:spring-boot-starter-test")
	sysTestRuntimeOnly("org.junit.platform:junit-platform-launcher")
	sysTestImplementation("io.rest-assured:rest-assured:5.3.2")
}

val systemTest = task<Test>("systemTest") {
	description = "Runs system tests."
	group = "verification"

	testClassesDirs = sourceSets["sysTest"].output.classesDirs
	classpath = sourceSets["sysTest"].runtimeClasspath
	shouldRunAfter("integrationTest")

	useJUnitPlatform()

	testLogging {
		events("PASSED", "SKIPPED", "FAILED", "STANDARD_OUT", "STANDARD_ERROR")
	}
}







