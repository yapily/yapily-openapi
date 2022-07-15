# Examples

## Structure
This folder contains examples on how to use the OpenAPI generator to create a code client used to interact with the Yapily API quickly.

Before generating the clients, you will need to register your application in our [Dashboard](https://dashboard.yapily.com/).

Once your application is generated and you've retrieved the `application_key` and `application_secret`, they can either be updated in the example files, or set as environment variables:

```
export APP_UUID=<your-application-uuid> 
export APP_SECRET=<your-application-secret> 
```

Each language has a `generate` script that showcases the flow used to generate the packages and run the entry scripts.

## Usage

All example scripts share one common step, fetching the `openapi.json` file from the spec file.

However, each language handles the generation and testing differently:

* Java 
    
    In `java`, we have a Maven project that will install all dependencies and compile the OAS file in models and api definitions.
    Those are then stored in `target/generated-sources` and can be used in services which act like wrappers around the APIClient calls.
    Each service comes with it's own test and `examples/java/src/test/java/com/yapily/openapi/service/SuiteTest.java` tests the AIS and PIS flows by calling all endpoints.


* Python 
    
    In `python`, `generate.sh` will install a virtual env, then all dependencies and compile the OAS file in models and api definitions.
    Those are then stored in `codegen_client` and we need to relocate out test script `main.py` into the package, so it can use make the APIClient calls.
    The generate script calls the `main.py [ARGS...]` file twice, passing the flow to be followed as an param:
    ``` 
    python3 main.py AIS
    
    python3 main.py PIS
    ```

* Node 
    
    In `node`, the package.json file contains two scripts which are called from `generate.sh` First one generates the openapi client using the oas file provided. 
    Afterwards, it packages the codeclient as a folder `dist`. This is the package of the APIClient and is used in `script.js`.
    Same as the Python example, the script is called twice, once for each flow:
    ```
    "scripts": {
    "build-oas": "openapi-generator-cli generate -i openapi.json -g javascript -o generated-sources/openapi --additional-properties=usePromises=true",
    -> "start-ais": "node index.js AIS",
    -> "start-pis": "node index.js PIS"
    },
    ```