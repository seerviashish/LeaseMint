import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "io.seerviashish.android.leasemint"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.seerviashish.android.leasemint"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    flavorDimensions += "environment"

    productFlavors {
        val appProperties = Properties()
        val environment = "environment"
        appProperties.load(File(rootDir, "app.properties").bufferedReader())
        create("dev") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            dimension = environment
            manifestPlaceholders+= "appName" to appProperties.getProperty("dev.appName")
            resValue("string", "app_name", appProperties.getProperty("dev.appName"))
        }
        create("qa") {
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            dimension = environment
            manifestPlaceholders+= "appName" to appProperties.getProperty("qa.appName")
            resValue("string", "app_name", appProperties.getProperty("qa.appName"))
        }
        create("stage") {
            applicationIdSuffix = ".stage"
            versionNameSuffix = "-stage"
            dimension = environment
            manifestPlaceholders+= "appName" to appProperties.getProperty("stage.appName")
            resValue("string", "app_name", appProperties.getProperty("stage.appName"))
        }
        create("uat") {
            applicationIdSuffix = ".uat"
            versionNameSuffix = "-uat"
            dimension = environment
            manifestPlaceholders+= "appName" to appProperties.getProperty("uat.appName")
            resValue("string", "app_name", appProperties.getProperty("uat.appName"))
        }
        create("prod") {
            dimension = environment
            manifestPlaceholders+= "appName" to appProperties.getProperty("prod.appName")
            resValue("string", "app_name", appProperties.getProperty("prod.appName"))
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}