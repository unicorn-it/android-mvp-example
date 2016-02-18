# Example Android-Project implementing the Model View Presenter pattern

## Information

This project contains the folder 'app' which contains the Android-application
that is designed to use the MVP-Pattern in order to be able to separate
the views from the business logic. This makes sense as soon as you want
to use the same business logic for different device-types.
For example if you want to react to a login in a different way that you
would on a wear-gear.

## Getting started

1. clone the repository.
2. make sure you have the Android installed on your machine.
3. Create a virtual device with android itself, an emulated device with Genymotion
or connect your actual android phone to the laptop.
4. in Terminal issue:
```
./gradlew build
```
5. in Terminal issue:
```
adb install app/build/outputs/apk/app-debug.apk
```
6. check your virtual,emulated or real device for the installed application.
7. start it!


### Model

The model is at the moment only represented by one service - the UserService.
This example of an injectable service's purpose is to handle login-business-logic.

### View

The view is represented by our activities and fragments.
If you take a look at the LoginActivityFragment, that gets inflated by the MainActivity,
you can see that it is using RoboGuice to inject the view-objects.
Also it is calling the Presenter to handle the login-button-click.

### Presenter

We have the Presenter being implemented in the class 'MainPresenter'.
It takes requests from the views, delegates them to the services and
calls the views callback-methods.