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

file="build/state-machine-framework-$1.jar" #the path to the jar file to be created/overwritten
out="out/production/state-machine-framework/" #the build path where all the .class files are

echo jar cf "$file" -C "$out" ftc/
jar cf "$file" -C "$out" ftc/ #create the jar file
#the -C option tells the utility to cd into $out before creating the jar file

dir="../ftc20151104/FtcRobotController/libs/" #the location of the project where the jar file should be copied to

if [[ ! -d "$dir" ]] #if the project is not there
then
  echo "error when copying $file to $dir: directory not found"
  exit 2
fi

echo cp "$file" "$dir"
cp "$file" "$dir" #copy the file
