# Yapily-openapi3-specifications

Yapily uses the `OpenAPI 3.0.1` specification to schematize our [docs](https://docs.yapily.com/) and generate our supported client libraries. This provides a consistent developer experience across our external interfaces.


- [Yapily-openapi3-specifications](#yapily-openapi3-specifications)
  - [Installing the Java dependencies](#installing-the-java-dependencies)
  - [Java support](#java-support)
  - [Node support](#node-support)
  - [Python support](#python-support)
  - [Known issues with openapi-generator](#known-issues-with-openapi-generator)

## Installing the Java dependencies

The OpenAPI generator is a maven plugin used to generate server and client libraries from a given OAS file.

First requirement to use the OpenAPI generator is to install Java. 

The following [tutorial](https://cloud.google.com/java/docs/setup#install_a_jdk_java_development_kit) provides an extensive step by step guide on how to setup and configure your development environment.

## Java support

Please refer to this [document](/main/../docs/java.md) for Java.

## Node support

Please refer to this [document](/main/../docs/node.md) for Node.

## Python support

Please refer to this [document](/main/../docs/python.md) for Python.

## Known issues with openapi-generator

The [openapi-generator](https://github.com/OpenAPITools/openapi-generator) generation styles vary depending on the chosen language:

- We regard the extension of enumerations in the Yapily API to be a non-breaking change and we may add new values without advance warning. Please note that some OpenAPI generators automatically employ enum validation by default, however we expect clients to disable strict enum validation for responses in your generated libraries. Otherwise your clients will experience errors when new enum values are added.