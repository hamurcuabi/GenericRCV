# GenericRCV 
					
GenericRCV makes Recyclerviews simple :bicyclist:

## Prerequisites

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

## Dependency

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
	...
	 implementation 'com.github.hamurcuabi:GenericRCV:Tag'
}
```

```
Current Version is 0.1.0
```

## Configuration
```
1-Create your model extending from BaseModel (TestModel)

  public class TestModel extends BaseModel 
```
```
2-Create your item layout for view (I am using databinding and butterknife)

  item_test.xml in layput folder from our sample project you can copy and  past then build it to generate binding classes
  ```
  
  ```
3-Create adapter

public class TestAdapter extends GenericAdapter<TestModel,
        IOnItemClickListener<TestModel>,
        TestViewHolder> implements Filterable ..
     ```
 
4-Createviewholder   

public class TestViewHolder extends BaseViewHolder<TestModel,
        IOnItemClickListener<TestModel>> ...
```
```
5-Activty or fragment setup your adapter should implements IOnItemClickListener, IOnSwipe

testAdapter = new TestAdapter(this, this, emptyView);
        GRVHelper.setupWithSwipe(testAdapter, recyclerView, this);
```
 
 ```
 I am strongly recommended to you looking for sample project. It is basic and understable   
  ```
  
  ![GenericRCV Demo](https://media.giphy.com/media/f5uoUMtt3znuc7PHQ9/giphy.gif)

