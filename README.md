<div align="center">
	<img src="https://raw.githubusercontent.com/dimorinny/show-case-card-view/master/art/logo.png">
</div>

```
// TODO: badges
```

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
// TODO: import
```

## Usage

You can show this view on your activity or fragment using this code:

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

**Available radiuses:**

* `Radius(float radius)`
* `ViewRadius(View view)`

For more complication usage - see [example](https://github.com/dimorinny/show-case-card-view/blob/master/app/src/main/java/ru/dimorinny/showcasesample/MainActivity.java).

## Demo

![Demo](https://github.com/dimorinny/show-case-card-view/blob/master/art/demo.gif?raw=true)