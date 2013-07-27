#!/bin/sh


DEV_LOCATION="."
LIB_LOCATION="$DEV_LOCATION/../../3rdParty/netty-3.2.4.Final/jar"
CLASSPATH="$DEV_LOCATION/target/classes/com/brtracker/services/netty"

for i in `ls ${LIB_LOCATION}/*.jar`
do
  CLASSPATH=${CLASSPATH}:${i}
done




java -cp $CLASSPATH com.brtracker.services.netty.MessageConsumerServer


