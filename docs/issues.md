## Known issues with openapi-generator

The [openapi-generator](https://github.com/OpenAPITools/openapi-generator) generation styles vary depending on the chosen language:

- We regard the extension of enumerations in the Yapily API to be a non-breaking change and we may add new values without advance warning. Please note that some OpenAPI generators automatically employ enum validation by default, however we expect clients to disable strict enum validation for responses in your generated libraries. Otherwise your clients will experience errors when new enum values are added.