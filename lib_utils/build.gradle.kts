import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties
import net.thebugmc.gradle.sonatypepublisher.PublishingType.AUTOMATIC

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("net.thebugmc.gradle.sonatype-central-portal-publisher") version "1.2.3"
}

android {
    namespace = "spa.lyh.cn.lib_utils"
    compileSdk = 35


    defaultConfig {
        minSdk = 21

    }

    buildTypes {
        release {
            isMinifyEnabled  = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility =  JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
}

dependencies {
    implementation ("androidx.appcompat:appcompat:${Lib.appcompat}")
    api ("androidx.activity:activity-ktx:${Lib.activity}")
    implementation ("com.google.android.material:material:${Lib.design}")
    implementation ("androidx.constraintlayout:constraintlayout:2.2.0")
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


centralPortal {
    username = ossrhUsername
    password = ossrhPassword
    name = "CommonUtils"
    group = "io.github.liyuhaolol"
    version = Version.versionName
    pom {
        //packaging = "aar"
        name = "CommonUtils"
        description = "Powerful Utils"
        url = "https://github.com/liyuhaolol/CommonUtils"
        licenses {
            license {
                name = "The MIT License"
                url = "https://github.com/liyuhaolol/CommonUtils/blob/master/LICENSE"
            }
        }
        developers {
            developer {
                id = "liyuhao"
                name = "liyuhao"
                email = "liyuhaoid@sina.com"
            }
        }
        scm {
            connection = "scm:git@github.com/liyuhaolol/CommonUtils.git"
            developerConnection = "scm:git@github.com/liyuhaolol/CommonUtils.git"
            url = "https://github.com/liyuhaolol/CommonUtils"
        }

    }
    publishingType = AUTOMATIC
    javadocJarTask = tasks.create<Jar>("javadocEmptyJar") {
        archiveClassifier = "javadoc"
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





