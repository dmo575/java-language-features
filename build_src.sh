#!/usr/bin/bash

# q. why bash?
# a. because :B
# a. You can also just run the javac and java lines

clear
echo "Starting build process..."
# build the src example, redirect std and error output to log file
javac -d build/ src/Main.java &> build_info.log

# if the build failed, tell the user where to go find the log data
if [ $? -ne 0 ]; then
    echo "Build failed, check log at build_info.log"
    exit 1
fi

clear
echo "Build done. executing..."
# execute the example, redirect std and error output to log file
java -classpath build src.Main 2> execution_info.log

# if the execution failed, tell the user where to go find the log data
if [ $? -ne 0 ]; then
    echo "Build failed, check log at execution_info.log"
    exit 1
fi