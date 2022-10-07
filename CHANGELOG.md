# Changelog for Yapily's API Definition

All notable changes to the API definition will be recorded here.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html)

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
