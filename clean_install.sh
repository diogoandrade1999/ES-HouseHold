#!/bin/bash

cd ./simulator && mvn clean install -DskipTests > /dev/null 2>&1
cd ../temperature && mvn clean install -DskipTests > /dev/null 2>&1
cd ../luminosity && mvn clean install -DskipTests > /dev/null 2>&1
cd ../humidity && mvn clean install -DskipTests > /dev/null 2>&1
