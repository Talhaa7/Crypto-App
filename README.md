# Loodos Android App

## Table of Contents

- [Architecture](#markdown-header-architecture)
- [Base Classes](#markdown-header-base-classes)
- [Log Types](#markdown-header_accessing_logs)


## Architecture

### Clean Architecture
Project structure created following [Clean Architecture Principles](https://gist.github.com/ygrenzinger/14812a56b9221c9feca0b3621518635b)

The architecture of the application consists of 3 layers

- Domain layer : This layer contains only pure (java/kotlin) business logic does not contain any third party library.UseCase's responsible handling business logic and they written in the manner of single responsibility principle

- Data layer : This layer is used as data access layer. All data connected operations will be controlled in this layer.  [Repository Pattern](https://developer.android.com/jetpack/guide#recommended-app-arch)is used as structure

- Presenter layer : This layer is UI layer all UI connected operations will be handled in this layer


Resources :

 - Inspired Clean Architecture [Repo](https://github.com/android10/Android-CleanArchitecture) and [Medium Article Explaining Clean Architecture](https://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/) provided from  [Fernando Cejas](https://github.com/android10)
 

## Base Classes

### BaseActivity BaseFragment and BaseViewModel
 BaseActivity and BaseFragment created to reduce boilerplate code in Activity and Fragment also automate dependency control as much as possible.

In this project all activities and fragments should have ViewModel and ViewBinding for extend BaseActivity and BaseFragment.
This way we can automate dependency injection of layout and view model inside base class.
The UI features we use throughout the application are defined in these classes.Like Showing loading progress bar or showing error to user
Loading progress bar and showing error states controlled inside BaseViewModel via _onFailureState and _loadingState.

BaseViewModel allows to use different coroutine scopes using onBackgroundThread and onUIThread methods
and loading state can be changed by using showLoadingDialog and hideLoadingDialog methods inside view model.

### BaseListAdapter
 BaseListAdapter also created following same principle. Adapter item layout also must use ViewBinding to extend BaseListAdapter.
 BaseListAdapter secondary constructor accept AdapterItemSelectListener interface to pass itemClick action to outside of Adapter.


## Accessing Logs
Logging is important part of the code base for testing functionality and accessing information for desired part of the app.
This part is explains how to access different kind of information from logcat.





