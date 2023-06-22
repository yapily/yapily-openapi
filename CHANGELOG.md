# Changelog for Yapily's API Definition

All notable changes to the API definition will be recorded here.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html)

## [2.22.1] - 2023-06-15
### Fixed
- Fixed POST/consent-auth-code example

## [2.22.0] - 2023-05-26

### Added
- Added endpoints to use Yapily Hosted Pages

## [2.21.6] - 2023-05-25
### Removed
- Removed outdated references to CBI Globe 

## [2.21.5] - 2023-05-23
### Fixed
- Fixed Get Payment Details example 

## [2.21.4] - 2023-05-02
### Changed
- Modified descriptions for Authorisation endpoints

## [2.21.3] - 2023-03-13
### Added
- Update `titles` for all the fields associated with `PaymentRequest`


## [2.21.2] - 2023-03-10
### Added
- Updated `paymentScheme` description with new payment scheme of type `TRANSFER`

## [2.21.1] - 2023-03-07
### Changed
- Included missing `count` field in the `meta` property of the `ApiListResponseOfRealTimeTransaction` object

## [2.21.0] - 2023-03-01
### Added
- Added additional fields to `selectedScaMethod` to show `information` and `data` useful to PSU when authenticating embedded consents

## [2.20.0] - 2023-02-27
### Added
- Added get all refunds endpoint

## [2.19.1] - 2023-02-21
### Added
- Included `x-yapily-features` for endpoints
- Included optional headers for `/pre-auth-requests` and `/payment-pre-auth-requests` endpoint
- Included raw response for VRP endpoints

### Changed
- Modified response for `/pre-auth-requests` and `/payment-pre-auth-requests` endpoint
- Modified endpoint path from `/users/{userUuid}/profile` to `/users/{userUuid}/profile/transaction-groups`
- Modified description, examples, typos for various endpoints and attributes

## [2.19.0] - 2023-02-07
### Added
- Included `complianceData` object in the VRP consents endpoints

## [2.18.0] - 2023-02-06
### Added
- Create refund and get refund by id

## [2.17.0] - 2022-12-15
### Added
- Get client by id and delete beneficiary

### Changed
- Virtual account error response examples

## [2.16.0] - 2022-11-30
### Changed
- Payer name isn't required but the field was marked as mandatory in its description. This change updates the field description.

## [2.15.0] - 2022-10-17
### Added
- ApplicationUser domain alreay has a column createdAt which is not used in OAS models. This change updates the models of
  applicationuser to use createdAt in request and response

## [2.14.0] - 2022-11-03
### Added
- Add Realtime transactions endpoint

## [2.13.1] - 2022-10-03
### Changed
- Fix regex pattern causing sdk issue

## [2.13.0] - 2022-10-03
### Added
- Updated schema and examples for consent endpoints

## [2.12.0] - 2022-09-29
### Added
- Add originalPaymentId to VirtualAccountPayment body

## [2.11.0] - 2022-09-20
### Added
- Add Create Virtual Account Client and Get Virtual Account Clients endpoints

## [2.10.0] - 2022-09-14
### Added
- Add consent extend endpoint

## [2.9.0] - 2022-09-07
### Changed
- Update schema for Financial Profile balance prediction endpoint

## [2.8.0] - 2022-09-06
### Changed
- Update schema for Financial Profile endpoints

## [2.7.1] - 2022-09-02
### Changed
- Updated VRP GET payment details payment ID path parameter to non-UUID format

## [2.7.0] - 2022-08-12
### Added
- Use new error block in variable recurring payments and virtual accounts endpoints

## [2.6.0] - 2022-08-04
### Added
- Add TransactionMutability to Transaction response

## [2.5.0] - 2022-08-02
### Added
- Add variable recurring payments endpoints for beta

## [2.4.0] - 2022-07-11
### Added
- Remove VirtualAccountIdentification and VirtualAccountIdentificationType.
- Reuse AccountIdentification and AccountIdentification for virtual accounts services.

## [2.3.0] - 2022-07-11
### Added
- Add virtual accounts endpoints for private beta

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