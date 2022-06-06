
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

#### Using the precompiled JAR

The latest stable version of the generated jar is available at:
 `https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/5.4.0/openapi-generator-cli-5.4.0.jar`

**Mac/Linux** users:

```sh
wget https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/5.4.0/openapi-generator-cli-5.4.0.jar -O openapi-generator-cli.jar
```

**Windows** users, you will need to install [wget](http://gnuwin32.sourceforge.net/packages/wget.htm) or you can use Invoke-WebRequest in PowerShell (3.0+), e.g.

```sh
Invoke-WebRequest -OutFile openapi-generator-cli.jar https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/5.4.0/openapi-generator-cli-5.4.0.jar
```

After downloading the JAR, run `java -jar openapi-generator-cli.jar help` to show the usage.

For Mac users, please make sure Java 8 is installed (Tips: run `java -version` to check the version), and export `JAVA_HOME` in order to use the supported Java version:

```sh
export JAVA_HOME=`/usr/libexec/java_home -v 1.8`
export PATH=${JAVA_HOME}/bin:$PATH
```

#### Using the CLI script

```
mkdir -p ~/bin/openapitools
curl https://raw.githubusercontent.com/OpenAPITools/openapi-generator/master/bin/utils/openapi-generator-cli.sh > ~/bin/openapitools/openapi-generator-cli
chmod u+x ~/bin/openapitools/openapi-generator-cli
export PATH=$PATH:~/bin/openapitools/
```

Now, `openapi-generator-cli` is "installed". On invocation, it will query the GitHub repository for the most recently released version. If this matches the last downloaded jar,
it will execute as normal. If a newer version is found, the script will download the latest release and execute it.

#### Further Documentation

Please refer to the official openapi-generator docs for more information about the possible arguments and a detailed usage manual of the command line interface.

#### Generating OpenAPI specs to classes

Now that the openapi generator is installed, we can use the spec file to generate the classes required.
Using the generator, you can create either a server side artifact or a client to use in your projects.

#### Generating the client

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

#### Using the generated classes

Once the previous step was ran, your folder will now contain a client package. This package is the stub of the APIs described in the specs file and is meant to serve as a boilerplate.

To install all the required libraries, change to the directory `codegen_client` and run the following command:

> mvn install

Schemas described in the specs file are stored in the models package.