# Data Analyzer

### How to run:

**To Run this application you must have java 17 or higher installed in you machine.
Before you clone the repository you must create in your home a directory named data
and inside that directory two others, in and out, once you did that you must set two 
environment variables:**
- DATA_IN=$HOME/data/in
- DATA_OUT=$HOME/data/out

**After that, you must clone this repository https://github.com/ViniLemess/DataAnalyzer.git
Once you Have the repository cloned you must go to the root of the project and run the
following commands in your cmd:**
- ./gradlew clean build
- java -jar build/libs/data-analyzer-1.0.all.jar

**And the application will be running!**