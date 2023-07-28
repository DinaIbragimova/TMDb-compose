# Simple project

Starter project with functional modules on the next stack:
+ kotlin, coroutines
+ mvvm, hilt, compose, navigation component
+ retrofit 2, kotlinx-serialization, coil

## Has the following features

#### 1. Single Activity with Navigation Component
There is the single AppActivity, which contains navigation graph with nested feature graph from feature modules.

#### 2. A few more tools
+ AppUpdateProvider
+ LocationProvider
+ NetworkStateProvider
+ PreferencesProvider
+ FirebaseNotificationsService
+ BuildInfo (contains info of current build)
+ Resources containers (ColorValue, ImageValue, TextValue)
+ Effect (like Result in Kotlin for results of operations)
+ CacheProvider (with two implementations)
+ ApiConfig (for build retrofit instances)
+ apiCall(...) and cachedApiCall(...) (for network requests)
+ NotificationHelper
+ Navigation extensions (in nav_ext.kt)
+ and many other extensions for some routine