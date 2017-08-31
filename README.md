![Demo](https://github.com/dimorinny/show-case-card-view/blob/master/art/demo.gif?raw=true)

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

You can display a ShowCase on your activity or fragment using the below code.

To display a list of (click-through) steps:
```java
new ShowCaseStepDisplayer.Builder(MainActivity.this)
    .addStep(new ShowCaseStepItem(new Center(), "Message at center"))
    .addStep(new ShowCaseStepItem(view, "Message at View"))
    .build().start();
```

Use withScrollView() if some step's target Views could be inside a ScrollView, they will be auto-scrolled to:

```java
new ShowCaseStepDisplayer.Builder(MainActivity.this)
    .withScrollView(scrollView)
    .addStep(new ShowCaseStepItem(view, "Message at View to scroll to"))
    .addStep(new ShowCaseStepItem(new TopLeft(), "Message at TopLeft"))
    .build().start();
```

To display a single item:

```java
new ShowCaseView.Builder(MainActivity.this)
    .withTypedPosition(new TopLeft())
    .withTypedRadius(new Radius(186F))
    .withContent("This is hello world!")
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

For more complicated usage - see [example](https://github.com/dimorinny/show-case-card-view/blob/master/app/src/main/java/ru/dimorinny/showcasesample/MainActivity.java).