#!/bin/bash
test -z "$1" && exit #exit if no version number was supplied

file="build/state-machine-framework-$1.jar"
jar cvf "$file" -C out/production/state-machine-framework/ ftc/

dir="$HOME/android_studio/ftc20151104/FtcRobotController/libs/"
test -d "$dir" && echo cp "$file" "$dir" && cp "$file" "$dir"



