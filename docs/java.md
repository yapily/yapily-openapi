
## OpenAPI support for Java
  
### Installation

#### Using the Maven Central

You can find the released artifacts on [maven central](https://mvnrepository.com/artifact/org.openapitools/openapi-generator):

**Core:**

```xml
<dependency>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator</artifactId>
    <version>${openapi-generator-version}</version>
</dependency>
```


#### Further Documentation

Please refer to the official openapi-generator docs for more information about the possible arguments and a detailed usage manual of the command line interface.

#### Generating OpenAPI specs to classes

Now that the openapi generator is installed, we can use the spec file to generate the classes required.
Using the generator, you can create either a server side artifact or a client to use in your projects.

### Generating the client using the Jar

A list of generators is available at [generators](https://openapi-generator.tech/docs/generators/)

> openapi-generator-cli generate -i openapi.json -g java -o codegen_client/
>
> or by using the jar
>
> java -jar openapi-generator-cli.jar generate -i openapi.json -g java -o codegen_client/

| Command/Argument    | Description                                                                                                                                                                   |
| ------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `openapi-generator` | Start OpenAPI Generator. **Note: Command here may vary depending on your OpenAPI Generator installation. See the OpenAPI Generator Installation Guide for more information.** |
| `-i openapi.json`   | Use `openapi.json` as the input.                                                                                                                                              |
| `-g java`         | Prepare client-side artifact (stub) using java.                                                                                                                                |
| `-o codegen_client` | Save the generated code in `codegen_client/`.                                                                                                                                 |

### Using the maven-plugin

OpenAPI has created a Maven [plugin](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-maven-plugin/README.md) to support the OpenAPI generator project. This can be found on the official documentation of the project.

#### Usage
To configure the plugin in your project, add the plugin and the execution to your `build`->`plugins` section (default phase is generate-sources phase)
```
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
    <!-- RELEASE_VERSION -->
    <version>5.4.0</version>
    <!-- /RELEASE_VERSION -->
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <!-- project.basedir is the home of the pom and you can supply the path to the spec. -->
                <inputSpec>${project.basedir}/src/main/resources/api.yaml</inputSpec>
                <generatorName>java</generatorName>
                <configOptions>
                   <sourceFolder>src/gen/java/main</sourceFolder>
                </configOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```

Running `mvn clean compile` will trigger the build and use the plugin to create the classes described in the OAS file.