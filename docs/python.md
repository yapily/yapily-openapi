
## OpenAPI support for Python
  
### Setting up your python env

#### Installing Python

Python's installation instructions vary by operating system. Follow the guide provided [here](https://cloud.google.com/python/docs/setup) for the operating system you're running in your development environment.

#### Using virtualenv

This [section](https://cloud.google.com/python/docs/setup#installing_and_using_virtualenv) of the guide details further how to use [virtual enviroments](https://docs.python.org/3/library/venv.html) to avoid dependency issues.

### Installing the OpenAPI Generator

OpenAPI Generator allows generation of API client libraries (SDK generation), server stubs, documentation and configuration automatically given an OpenAPI Spec (both 2.0 and 3.0 are supported). Please see [OpenAPITools/openapi-generator](https://github.com/OpenAPITools/openapi-generator) for more information.

This project checks the [maven repository](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-cli) once a day for a new version and will publish this new version automatically as a python package.

You can install the package either in a virtual environment or globally using:

> pip install openapi-generator-cli

#### Further Documentation

Please refer to the official openapi-generator docs for more information about the possible arguments and a detailed usage manual of the command line interface.

### Generating OpenAPI specs to classes

Now that the openapi generator is installed, we can use the spec file to generate the classes required.
Using the generator, you can create either a server side artifact or a client to use in your projects.

#### Generating the client

> #Prepare the client-end code
>
> openapi-generator generate -i openapi.json -g python -o codegen_client/

| Command/Argument    | Description                                                                                                                                                                   |
| ------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `openapi-generator` | Start OpenAPI Generator. **Note: Command here may vary depending on your OpenAPI Generator installation. See the OpenAPI Generator Installation Guide for more information.** |
| `-i openapi.json`   | Use `openapi.json` as the input.                                                                                                                                              |
| `-g python`         | Prepare client-side artifact (stub) in Python.                                                                                                                                |
| `-o codegen_client` | Save the generated code in `codegen_client/`.                                                                                                                                 |

#### Using the generated classes

Once the previous step was ran, your folder will now contain a client package. This package is the stub of the APIs described in the specs file and is meant to serve as a boilerplate.

To install all the required libraries, change to the directory `codegen_client` and run the following command:

> #Install dependencies for the API service
>
> pip install -r requirements.txt

Schemas described in the specs file are stored in the models package.