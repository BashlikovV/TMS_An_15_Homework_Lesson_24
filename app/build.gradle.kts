plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "by.bashlikovvv.tms_an_15_homework_lesson_22"
    compileSdk = 34

    defaultConfig {
        applicationId = "by.bashlikovvv.tms_an_15_homework_lesson_22"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

object Navigation {
    private const val nameSpace = "androidx.navigation"
    const val navigationFragmentKTX = "$nameSpace:navigation-fragment-ktx:2.7.4"
    const val navigationUiKTX = "$nameSpace:navigation-ui-ktx:2.7.4"
    const val navigationDynamicFeatures = "$nameSpace:navigation-dynamic-features-fragment:2.7.4"
}

object Room {
    private const val nameSpace = "androidx.room"
    const val roomKTX = "$nameSpace:room-ktx:2.5.2"
    const val roomRuntime = "$nameSpace:room-runtime:2.5.2"
    const val roomCompiler = "$nameSpace:room-compiler:2.5.2"
}

object LifeCycle {
    private const val nameSpace = "androidx.lifecycle"
    const val lifeCycleViewModel = "$nameSpace:lifecycle-viewmodel-ktx:2.6.2"
    const val lifecycleExtencions = "$nameSpace:lifecycle-extensions:2.2.0"
    const val lifecycleRuntime = "$nameSpace:lifecycle-runtime-ktx:2.6.2"

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation(Navigation.navigationFragmentKTX)
    implementation(Navigation.navigationUiKTX)

    //Room
    implementation(Room.roomKTX)
    implementation(Room.roomRuntime)
    ksp(Room.roomCompiler)

    implementation(LifeCycle.lifeCycleViewModel)
    implementation(LifeCycle.lifecycleExtencions)
    implementation(LifeCycle.lifecycleRuntime)
    //Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-compiler:2.49")
}

kapt {
    correctErrorTypes = true
}