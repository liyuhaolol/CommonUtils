import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

plugins {
    id("com.android.library")
    `maven-publish`
    signing
}


android {
    namespace = "spa.lyh.cn.lib_utils"
    compileSdk = 34


    defaultConfig {
        minSdk = 19

    }

    buildTypes {
        release {
            isMinifyEnabled  = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
        }
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }

}

dependencies {
    implementation ("androidx.appcompat:appcompat:1.2.0")
    implementation ("com.google.android.material:material:${findProperty("lib.design")}")
}

var signingKeyId = ""//签名的密钥后8位
var signingPassword = ""//签名设置的密码
var secretKeyRingFile = ""//生成的secring.gpg文件目录
var ossrhUsername = ""//sonatype用户名
var ossrhPassword = "" //sonatype密码

val localProperties = project.rootProject.file("local.properties")

if (localProperties.exists()) {
    println("Found secret props file, loading props")
    val properties = Properties()

    InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
        properties.load(reader)
    }
    signingKeyId = properties.getProperty("signingKeyId")
    signingPassword = properties.getProperty("signingPassword")
    secretKeyRingFile = properties.getProperty("secretKeyRingFile")
    ossrhUsername = properties.getProperty("ossrhUsername")
    ossrhPassword = properties.getProperty("ossrhPassword")

} else {
    println("No props file, loading env vars")
}


afterEvaluate {
    publishing{
        publications{
            register<MavenPublication>("release"){
                groupId = "io.github.liyuhaolol"
                artifactId = "CommonUtils"
                version = findProperty("version.versionName") as String
                from(components["release"])

                pom{
                    name.value("CommonUtils")
                    description.value("Powerful Utils")
                    url.value("https://github.com/liyuhaolol/CommonUtils")

                    developers {
                        developer {
                            id.value("liyuhao")
                            name.value("liyuhao")
                            email.value("liyuhaoid@sina.com")
                        }
                    }

                    scm {
                        connection.value("scm:git@github.com/liyuhaolol/CommonUtils.git")
                        developerConnection.value("scm:git@github.com/liyuhaolol/CommonUtils.git")
                        url.value("https://github.com/liyuhaolol/CommonUtils")
                    }
                }
            }
        }

/*        repositories{
            maven{
                name = "CommonUtils"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = ossrhUsername
                    password = ossrhPassword
                }
            }
        }*/
    }
}

gradle.taskGraph.whenReady {
    if (allTasks.any { it is Sign }) {
        allprojects {
            extra["signing.keyId"] = signingKeyId
            extra["signing.secretKeyRingFile"] = secretKeyRingFile
            extra["signing.password"] = signingPassword
        }
    }
}

signing {
    sign(publishing.publications)
}

/*tasks.register<Zip>("generateRepo") {
    val publishTask = tasks.named(
        "publishReleasePublicationToCommonUtilsRepository",
        PublishToMavenRepository::class.java)
    from(publishTask.map { it.repository.url })
    into("mylibrary")
    archiveFileName.set("mylibrary.zip")
}*/

/*tasks.register<Zip>("generateRepo") {
    val publishTask = tasks.named(
        "publishToMavenLocal",
        PublishToMavenLocal::class.java)
    from(publishTask.map { it.temporaryDir.absolutePath })
    into("mylibrary")
    archiveFileName.set("mylibrary.zip")
}*/



