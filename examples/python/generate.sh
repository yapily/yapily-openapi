#!/bin/bash

# Switch to the example folder for this build
cd examples/python

echo "Copying OAS file"
cp ../../openapi.json .

# Follow steps in doc to generate the files
pip3 install openapi-generator-cli
echo "Generating using openapi-generator"
openapi-generator generate -i openapi.json -g python -o codegen_client/ 


echo "Copying script file to package"
cp main.py codegen_client/main.py

cd codegen_client

# used for API calls outside the codegenerted package
pip3 install requests
pip3 install -r requirements.txt

python3 main.py AIS
python3 main.py PIS
