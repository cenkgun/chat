# Chat for Android

Chat is a sample project that presents a modern, 2021 approach to [Android](https://en.wikipedia.org/wiki/Android_(operating_system)) application development.

## Screenshots 📷
<img src="/art/art-1.png" width="260"> &emsp;<img src="/art/art-4.png" width="260"> &emsp;<img src="/art/art-5.png" width="260">

## Project Tech-stack

* Tech-stack
    * [100% Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    * [Flow](https://developer.android.com/kotlin/flow) - asynchronous stream library
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
        * [Room](https://developer.android.com/jetpack/androidx/releases/room) - store offline cache
    * [Hilt](https://dagger.dev/hilt/) - dependency injection
    * [Glide](https://bumptech.github.io/glide/) - image loading library
* Modern Architecture
    * Clean Architecture
    * Single activity architecture ( with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started))
    * MVVM (presentation layer)
    * Multi-module Architecture
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* Testing
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit 4](https://junit.org/junit4/))
    * [Truth](https://truth.dev/) - assertion framework
* UI
    * [Material design](https://material.io/design)
    * Reactive UI

## Preparation For Run
You first need to generate the `local.properties` (replace YOUR_SDK_DIR with
your actual android SDK directory) file and add `BASE_URL`(replace baseUrl with your base url) line

    sdk.dir=YOUR_SDK_DIR
    BASE_URL="baseUrl"

## Build

In Android Studio, use the "Open an existing Android Studio project", and select the main `Chat` directory.

Alternatively use the `./gradlew build` command to build the project directly.

Build and run.
