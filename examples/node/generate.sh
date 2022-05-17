#!/bin/bash

# Switch to the example folder for this build
cd examples/node

echo "Copying OAS file"
cp ../../openapi.json .

echo "Installing dependencies"
npm install 
echo "Generating using openapi-generator"
npm run build-oas
cd generated-sources/openapi
npm run build
mv dist ../..
cd ../..
echo "Running example script"
npm run start-ais
npm run start-pis