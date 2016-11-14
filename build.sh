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

#blacklist separated by spaces
blacklist="ftc/electronvolts/test"

#the path to the jar file to be created/overwritten
file="build/state-machine-framework-$1.jar"

#the build path where all the .class files are
out="bin/"

source_dir="src/"
package="ftc"

#the source directory
sources="build/state-machine-framework-$1-sources.jar"

#the location of the project where the jar file should be copied to
dir="$HOME/AndroidStudio/ftc7393/libs/"

echo "<=== BLACKLIST ===>"
whitelist=`cd "$out"; find "$package" -type f`
for item in $blacklist
do
  echo "$item"
  whitelist=`echo "$whitelist" | grep -v "$item"`
done

opts=""
for item in $whitelist
do
  opts="$opts -C $out $item"
done

whitelistSrc=`cd "$source_dir"; find "$package" -type f`
for item in $blacklist
do
  whitelistSrc=`echo "$whitelistSrc" | grep -v "$item"`
done

echo "<=== COMPILED ===>"
echo jar cf "$file" $opts
jar cf "$file" $opts #create the jar file
#the -C option tells the utility to cd into $out before creating the jar file

echo "<=== SOURCES ===>"
cd "$source_dir"
echo zip -r "../$sources" "$package/" -i $whitelistSrc
zip -r "../$sources" "$package/" -i $whitelistSrc
cd ..

echo "<=== GIT ADD ===>"
echo git add "$file" "$sources"
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

echo cd "$dir"
cd "$dir"
#add them to the other project's git repository
echo git add `basename "$file"` `basename "$sources"`
git add `basename "$file"` `basename "$sources"`
