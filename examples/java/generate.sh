#!/bin/bash

# Switch to the example folder for this build
cd examples/java

echo "Copying OAS file"
cp ../../openapi.json .

echo "Running maven install"
mvn clean install