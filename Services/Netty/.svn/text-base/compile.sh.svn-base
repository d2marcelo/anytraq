#!/bin/sh

if [ -z "$1" ] ; then
	echo
        echo "Sorry, you must pass the name of the file to compile as the first parameter of this script."
        echo
        exit
fi


DEV_LOCATION="."
LIB_LOCATION="$DEV_LOCATION/../../3rdParty/netty-3.2.4.Final/jar"
CLASSPATH="$DEV_LOCATION/target/classes/com/brtracker/services/netty"

for i in `ls ${LIB_LOCATION}/*.jar`
do
  CLASSPATH=${CLASSPATH}:${i}
done



echo
echo "About to compile $1"

javac  -Xlint:unchecked -Xlint:deprecation -classpath $CLASSPATH $DEV_LOCATION/src/main/java/com/brtracker/services/netty/$1.java -d $DEV_LOCATION/target/classes/com/brtracker/services/netty

echo
echo "Compiling complete"
echo
