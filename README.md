# aopUtil

Project的build.gradle

    buildscript {
        ...

        dependencies {
            ...
            classpath 'com.hujiang.aspectjx:gradle-android-plugin-aspectjx:2.0.4'
        }
    }

	allprojects {
	
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
app的build.gradle

    apply plugin: 'android-aspectjx'

    ...

	dependencies {
	
	        implementation 'com.github.shoppingzhang:aop_util:v1.0.2'
		
	}

  
  
