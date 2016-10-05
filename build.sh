#!/bin/bash
#####################################################################
# This script packages the compiled project into a jar file.        #
# Then it copies the jar file to the main FTC project if it exists. #
# The version number of the jar file must be the first argument.    #
#####################################################################

if [[ -z "$1" ]] #if no version number was supplied
then
  echo "usage: $0 version.number"
  exit 1
fi

#the path to the jar file to be created/overwritten
file="build/state-machine-framework-$1.jar"

#the build path where all the .class files are
#out="out/production/state-machine-framework/"
#out="build/"
out="bin/"

source_dir="src/"
package="ftc/"

#the source directory
sources="build/state-machine-framework-$1-sources.jar"

#the location of the project where the jar file should be copied to
#dir="../ftc20151105/FtcRobotController/libs/"
#dir="../ftc20151104/libs/"
dir="$HOME/AndroidStudio/ftc7393/libs/"

echo "<=== COMPILED ===>"
echo jar cf "$file" -C "$out" "$package"
jar cf "$file" -C "$out" "$package" #create the jar file
#the -C option tells the utility to cd into $out before creating the jar file

echo "<=== SOURCES ===>"
cd "$source_dir"
zip -r "../$sources" "$package"
cd ..

echo "<=== GIT ADD ===>"
git add "$file" "$sources"

echo "<=== COPY ===>"

if [[ ! -d "$dir" ]] #if the project is not there
then
  echo "error when copying $file to $dir: directory not found"
  exit 2
fi

echo cp "$file" "$dir"
cp "$file" "$dir" #copy the jar file
echo cp "$sources" "$dir"
cp "$sources" "$dir" #copy the sources


