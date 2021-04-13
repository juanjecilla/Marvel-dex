# Marvel-dex

Small app to present the characters in Marvel's Universe.

The app is compatible since API 21, that means over 94.1% devices.

## Architecture
Clean Architecture divided into three layers, each one in a different module.ç

- Presentation (app module)
- Data (Android module)
- Domain (Pure Kotlin module)

## Features
- MVVM in presentation layer.
- Dependency Injection is managed with Koin library.
- Reactive programming using Flow.
- Paginated results.
- Private key protected from decompilations.

 
## Libraries

Libraries used in the whole application are:

- [Jetpack](https://developer.android.com/jetpack)🚀
  - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  - [View Binding](https://developer.android.com/topic/libraries/view-binding)
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md)
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Koin](https://github.com/InsertKoinIO/koin)


## Usage
Register in Marvel's developer platform and replace your keys:
- Your PRIVATE_KEY app/src/main/cpp/native-lib.cpp
- Your PUBLIC_KEY  app/src/main/java/com/scallop/marveldex/di/Network.kt