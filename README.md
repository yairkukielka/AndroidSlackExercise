##Android Slack Exercise
This is an exercise app for Slack. You can read the instructions in the INSTRUCTIONS.md file

###Architecture

MVP architecture based on [this](https://github.com/android10/Android-CleanArchitecture), the app contains 3 modules:
presentation, data and domain. It is separated in modules so that the direction of the dependencies is always towards the inner circle (see reference architecture) and no mistakes can be made concerning the 'includes' of the projects. It also makes it easier to be tested.

###Modules

#### 1 Presentation
It includes the views (Fragments, Activities) and the presenters. The presenters are created and destroyed by the views.
It also includes the dependency injection components and modules.
This app uses configuration qualifiers (resource directories) to allow showing alternative resources based on the current device.
#### 2 Domain
It is the business layer. This module is pure java so that it can be easily tested with traditional java testing techniques and libraries.
The business logic is divided mainly in two use cases or interactors:
 * Get the list of users
 * Get the details of a user

#### 3 Data
Provides the data for the application with a repository pattern:
 * The communication towards the internet (net package)
 * The communication with the filesystem (cache package)


###Some libraries used
The libraries used can be found in the buildsystem/dependencies.gradle file.

 * Dagger2: for dependency injection
 * Butterknife: for view injection
 * RxJava and RxAndroid: reactive extensions libraries
 * Gson: Json parsing and POJO serialization
 * Picasso: for downloading images (it has been hidden in the code behind the ImageLoader interface so it can be easily swaped by Glide)

###Executing the code
You can try these Gradle/adb commands:

 * `./gradlew clean build` - Build the entire example and execute unit and integration tests plus lint check.
 * `./gradlew installDebug` - Install the debug apk on the current connected device.
 * `./gradlew runUnitTests` - Execute the data layer tests.

###Future Improvements
 * Add more tests to the three modules.
 * Add a database to swap out the file persistance.
