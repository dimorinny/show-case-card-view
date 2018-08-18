<div align="center">
	<img src="https://raw.githubusercontent.com/dimorinny/show-case-card-view/master/art/logo.png">
</div>

[![](https://jitpack.io/v/dimorinny/show-case-card-view.svg)](https://jitpack.io/#dimorinny/show-case-card-view)

## Dependency

Firstly, add Jitpack repository in your root build.gradle file (not your module build.gradle file):

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Add dependency to your module's build.gradle file:

```
dependencies {
    compile 'com.github.dimorinny:show-case-card-view:0.0.1'
}
```

## Usage

You can show a ShowCase (one item or a list of steps) on your activity or fragment using the below code.

For a list of steps:
```java
ShowCaseStepController stepController = new ShowCaseStepController(MainActivity.this); // Activity or Fragment
stepController.addItem(new ShowCaseStepItem(new Center(), "This is the center of the screen. Tap anywhere to continue."));
stepController.addItem(new ShowCaseStepItem(viewToShowCase, "This points to a View's location. Tap to end the show case."));
stepController.start();
```

or if you want to include an auto-scroll if the target view is off-screen:

```java
ShowCaseStepController stepController = new ShowCaseStepController(MainActivity.this, scrollView); // Activity or Fragment + ScrollView
stepController.addItem(new ShowCaseStepItem(new Center(), "This is the center of the screen. Tap anywhere to continue."));
stepController.addItem(new ShowCaseStepItem(viewToShowCase, "This View will be scrolled to if needed. Tap to end the show case.", true));
stepController.start();
```

or you can just display a single item with:

```java
new ShowCaseView.Builder(MainActivity.this)
    .withTypedPosition(new TopLeft())
    .withTypedRadius(new Radius(186F))
    .withContent(
            "This is hello world!"
    )
    .build()
    .show(this);
```

**Available positions:**

* `Position(PointF position)`
* `TopLeft()`
* `TopRight()`
* `BottomLeft()`
* `BottomRight()`
* `TopLeftToolbar()`
* `TopRightToolbar()`
* `ViewPosition(View view)`
* `Center()`
* `BottomCenter()`

**Available radiuses:**

* `Radius(float radius)`
* `ViewRadius(View view)`

For more complication usage - see [example](https://github.com/dimorinny/show-case-card-view/blob/master/app/src/main/java/ru/dimorinny/showcasesample/MainActivity.java).

## Demo

![Demo](https://github.com/dimorinny/show-case-card-view/blob/master/art/demo.gif?raw=true)