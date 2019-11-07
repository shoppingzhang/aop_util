# aopUtil

Project的build.gradle

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
	
	        implementation 'com.github.shoppingzhang:aop_util:v1.0.0-alpha'
		
	}

  
  
