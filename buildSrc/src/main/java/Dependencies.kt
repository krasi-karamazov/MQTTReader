object Versions {

    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28

    val kotlin = "1.3.20"
    val constraintLayout = "1.1.3"
    val junit = "4.12"
    val runner = "1.0.2"
    val espresso = "3.0.2"

    val appCompat = "1.0.2"
    val design = "1.0.0"
    val cardView = "1.0.0"
    val recyclerView = "1.0.0"

    val rxJava = "2.2.6"
    val rxAndroid = "2.1.0"
    val rxKotlin = "2.3.0"
    val lifecycle = "2.0.0"

    val timber = "4.7.1"

    val moshi = "1.8.0"
    val rxMQTT = "1.2.0.RELEASE"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Modules {
    val app = ":app"
    val domain = ":domain"
    val model = ":model"
    val presentation = ":presentation"
}

object Libraries {
    val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val jUnitLibrary = "junit:junit:${Versions.junit}"
    val testRunnerLibrary = "com.android.support.test:runner:${Versions.runner}"
    val espressoLibrary = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    val rxMQTT = "net.eusashead.mqtt:rxmqtt:${Versions.rxMQTT}"
}

object SupportLibraries {
    val appCompatLibrary = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardView}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    val constraintLayoutLibrary = "com.android.support.constraint:constraint-layout:${Versions.constraintLayout}"
}