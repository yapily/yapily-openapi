
## OpenAPI support for Node.js

### Setting up your node.js env

#### Installing NVM

[NVM](https://github.com/creationix/nvm) is a bash script for managing installations of Node.js and npm. NVM doesn't support Windows. For more information about managing your Node.js installation on Windows, see [nvm-windows](https://github.com/coreybutler/nvm-windows)

To install NVM, follow the guide provided [here](https://github.com/creationix/nvm#installation)

#### Installing Node.js and npm

This [section](https://cloud.google.com/nodejs/docs/setup#installing_nodejs_and_npm) of the guide provides a step by step guide on how to install and configure your node installation.

### Installing the OpenAPI Generator

OpenAPI Generator allows generation of API client libraries (SDK generation), server stubs, documentation and configuration automatically given an OpenAPI Spec (both 2.0 and 3.0 are supported). Please see [OpenAPITools/openapi-generator](https://github.com/OpenAPITools/openapi-generator) for more information.

#### Installation

- Locally (recommended)

> npm install @openapitools/openapi-generator-cli
>
> or using yarn
>
> yarn add @openapitools/openapi-generator-cli

- Globally

> npm install -g @openapitools/openapi-generator-cli
>
> or using yarn
>
> yarn global add @openapitools/openapi-generator-cli

#### Verifying

After the installation has finished you can run `npx openapi-generator-cli` or add a script like this:

```json
{
  "name": "my-cool-package",
  "version": "0.0.0",
  "scripts": {
    "my-awesome-script-name": "openapi-generator-cli generate -i docs/openapi.yaml -g typescript-angular -o generated-sources/openapi --additional-properties=ngVersion=6.1.7,npmName=restClient,supportsES6=true,npmVersion=6.9.0,withInterfaces=true",
  }
}
```

#### Further Documentation

Please refer to the official openapi-generator docs for more information about the possible arguments and a detailed usage manual of the command line interface.

### Generating OpenAPI specs to classes

Now that the openapi generator is installed, we can use the spec file to generate the classes required.
Using the generator, you can create either a server side artifact or a client to use in your projects.

#### Generating the client

A list of generators is available at [generators](https://openapi-generator.tech/docs/generators/)

> openapi-generator-cli generate -i openapi.json -g javascript -o codegen_client/

| Command/Argument    | Description                                                                                                                                                                   |
| ------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `openapi-generator` | Start OpenAPI Generator. **Note: Command here may vary depending on your OpenAPI Generator installation. See the OpenAPI Generator Installation Guide for more information.** |
| `-i openapi.json`   | Use `openapi.json` as the input.                                                                                                                                              |
| `-g javascript`         | Prepare client-side artifact (stub) using javascript.                                                                                                                                |
| `-o codegen_client` | Save the generated code in `codegen_client/`.                                                                                                                                 |

#### Using the generated classes

Once the previous step was ran, your folder will now contain a client package. This package is the stub of the APIs described in the specs file and is meant to serve as a boilerplate.

To install all the required libraries, change to the directory `codegen_client` and run the following command:

> npm install

Schemas described in the specs file are stored in the models package.