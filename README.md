# DesignToPDF
This is an android library which converts any xml design to PDF in the local storage. It is very customizable.


### Follow these steps to use this library:
#### In build.gradle (Project)

``` gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
``` 

And then in the other gradle file(may be your app gradle or your own module library gradle, but never add in both of them to avoid conflict.)

``` gradle
dependencies {
	        compile 'com.github.kapilmhr:DesignToPDF:1.0'
          }
```


## How to use

####  Simply in one line of code!:
``` java
DesignPdf.with(MainActivity.this).addView(view).create();
```
#### If you want callback 
``` java
   DesignPdf.with(MainActivity.this).addView(view)
                        .create(new Callback() {
                            @Override
                            public void onSuccess(File file) {
                                Toast.makeText(MainActivity.this, "path: "+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                                Log.d("path",file.getAbsolutePath());
                            }

                            @Override
                            public void onError(Exception e) {
                                e.printStackTrace();
                            }
                        });
```

## Customizations

#### Change Margins
``` java
DesignPdf.with(context).setMargins(left,right,top,bottom)
```

#### Change Filepath and filename
``` java
.setFilePath(filePath,"filename")
```

#### Change Multiplier
Initially the screen resolution is taken as the default size so in some case adding multiplier can be quick fix
if the height of layout is long and is inside scroll view. Thus it wont fit in A4 size paper so increasing height multiplier would solve.
By default its 1. set max to 2
```java
.setHeightnWidthMultiplier(heightmultiplier,wifthmultiplier)
```

#### Change height and width
Change height and width of layout to be displayed as per need
must be greater than 100
``` java
.setHeightnWidth(height, width);
```
## Generate PDF from layout that is not visible to user (strictly, visiblity should be set to false)
``` java
DesignPdf.with(MainActivity.this)
                      .addView(R.layout.test_layout)
                      .setViewVisibilty(false)
```


