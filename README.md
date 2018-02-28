# GEB framework example

## How to run testing locally

1. Run the testing using command:
```
./gradlew test
``` 
2. Depends on the goals, we can specify the browser name in the command, to run the testing on specific browser:
``` 
./gradlew chromeTest 
```
or
```
./gradlew firefoxTest 

```

## How to run testing remotely
1. First - run the Selenoid locally (or remotely) [folowing this guide]("https://github.com/savvagen/Selenoid-example")
2. Run the testing using command after running of the Selenoid:
``` 
./gradlew remoteTest

```
