plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.android.junit5)
}

android {
    namespace = "idv.hsu.data.remote"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    implementation(libs.bundles.network)
    ksp(libs.moshi.codegen)

    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.junit5.api)
    testRuntimeOnly(libs.junit5.engine)
}