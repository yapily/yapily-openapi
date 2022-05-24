# Changelog for Yapily's API Definition

All notable changes to the API definition will be recorded here.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html)


## [2.2.0] - 2022-05-18
### Added
- Publishing new notifications endpoints for private beta
- Miscellaneous updates to improve documentations

## [2.1.0] - 2022-02-11
### Added
- New end point POST /payment-pre-auth-requests was added to support CBI Globe's pre-authorisation process
- The Feature enum was extended to include INITIATE_ONETIME_PRE_AUTHORISATION_PAYMENTS
- The financial profile API's response, was enhanced to include arrays for newly introduced recentlyTerminatedIncomeStreams and recentlyTerminatedExpenditureStreams. See GET /users/{userUuid}/profile
- A default value of OTHER to paymentContextType

### Changed
- All occurrences of account identifiers in existing definitions for Account, Payee, Payer and RefundAccount updated to an array of unique items matching the platform's implementation.

### Removed
- Unnecessary enumeration representing http status codes from the ApiError definition.

## [2.0.0] - 2021-12-01
### Added 
- The first published OpenAPI Specification used to drive Yapily's contract first development approach.