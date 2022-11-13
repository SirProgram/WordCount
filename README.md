# Word Count

---
## Overview

This project counts the occurrences of words in a given filepath. 

The result is printed to the command line and orders them by highest frequency first.
In the case of tied occurrences, they are then sorted alphabetically. 
Common punctuation is also removed and the words are made lowercase.

As an example:
```
Here comes the first line.
This is the second line.
And there's a third line too!
```
Will return the output:
```
line:3
the:2
a:1
and:1
comes:1
first:1
here:1
is:1
second:1
there's:1
third:1
this:1
too:1
```

## Run
This project was made and compiled using **Java 19** (OpenJDK). There is a built jar in the `release` folder in the repository above.

The jar takes the filepath of the file to wordcount as the first parameter. As an example:
```
java -jar release\WordCount-1.0.jar src\test\resources\shortText.txt
```
## Build


Alternatively, the project can be built from source by running `mvn clean package` which will build to `target/WordCount-1.0-SNAPSHOT-jar-with-dependencies.jar`