# Changelog for Yapily's API Definition

All notable changes to the API definition will be recorded here.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html)

## [7.0.0] - 2024-10-28
### Changed
breaking change - Removed "status" key from request response and from the schema.

## [6.0.0] - 2024-10-15
### Changed
breaking change - Categorisation type made mandatory for Trigger transaction categorisation POST /accounts/{accountId}/transactions/categorisation

## [5.3.0] - 2024-10-08
### Changed
- Removed payment type "BULK_PAYMENT" from Create Payment, Create Bulk Payment and Get Payment Constraints Rules

## [5.2.0] - 2024-10-07
### Changed
add sub-application support to webhook api

## [5.1.0] - 2024-09-27
### Changed
Removed 'bankStatus' field from response of GET Bulk Payment Status. Modified name of endpoint.
Added 'idempotencyId' field in Submit Bulk Payment request to allow customers to resubmit bulk payments in case of failures on first attempt.

## [5.0.0] - 2024-09-24
### Changed
Changed structure of Get Transactions for 'Consumer' accountType - no longer has 'incoming' and 'outgoing' fields and all is consolidated under 'labels' field, like current 'Business' accountType behaviour

## [4.10.0] - 2024-09-18
### Changed
Removed authToken from HostedPaymentRequestResponse

## [4.9.0] - 2024-09-20
### Changed
- Added Hosted Pages Consent endpoints

## [4.8.0] - 2024-09-11
### Changed
- Added sub-application parameter as header to all existing AIS and PIS endpoints

## [4.7.0] - 2024-08-29
### Removed
- Monitoring object from responses of all endpoints

## [4.6.0] - 2024-08-19
### Changed
- Added a new endpoint for retrieving Bulk Payment File Status

## [4.5.0] - 2024-08-19
### Changed
- Added a new bulk payment endpoint to retrieve the status of each payment submitted for given bulkPaymentId

## [4.4.1] - 2024-07-22
### Changed
- fix webhook and data+ error response schema and examples

## [4.4.0] - 2024-07-22
### Changed
- Remove Virtual Accounts error response schemas.

## [4.3.1] - 2024-07-17
### Changed
- Changed enum to string for field 'categorisationType' in POST /categorisation request body.

## [4.3.0] - 2024-07-11
### Changed
- Added a new field 'categorisationType' in POST /categorisation request body. Accepted values are 'consumer' and 'business'. If not provided defaults to 'consumer'

## [4.2.1] - 2024-07-09
### Changed
- Add missing query parameter `page` to retrieve categorisation endpoint
- Add new error status `406` to webhook registration endpoint when maximum number of registered webhooks has been reached

## [4.2.0] - 2024-07-02
### Changed
- Added a new field 'paymentStatus' to HostedPayment in ApiResponseOfGetHostedPaymentRequest

## [4.1.0] - 2024-06-28
### Changed
- Added `id` and renamed `merchantName` to `name` in the `merchant` object for Get Categorised Transactions response

## [4.0.0] - 2024-06-25
### Changed
- Added Webhooks endpoints to Retrieve webhook event categories, Register new webhook event, Retrieve all registered webhooks, Delete a webhook event, Reset webhook secret

## [3.0.0] - 2024-06-19
### Changed
- Removed Virtual Accounts endpoints.


## [2.32.0] - 2024-05-18
### Changed
- Added optional 'sub-application' header to Create, Get and Delete Event Subscription endpoints to support sub-applications in Notifications Service.


## [2.31.6] - 2024-05-07
### Changed
- Update phase list for hosted endpoint

## [2.31.5] - 2024-05-07
### Changed
- Changed response code for consent-auth-code to 200 to match what is actually returned

## [2.31.4] - 2024-05-02
### Changed
- Rename operationId to a shorter form, compatible with the SwaggerCodeGen
  
## [2.31.3] - 2024-04-22
### Changed
- Added a new enum value 'ACCO' to PaymentIsoStatusCodeEnum

## [2.31.2] - 2024-03-27
### Changed
- Updated applicationUserId to not hardcode an email for Get /users endpoints

## [2.31.1] - 2024-03-26
### Changed
- Updated applicationUserId to not hardcode an email

## [2.31.0] - 2024-03-18
### Changed
- Added new categorisation endpoints

## [2.30.1] - 2024-03-11
### Changed
- Adjust x-beta tags for Virtual Account endpoints and Hosted VRP endpoints

## [2.30.0] - 2024-03-01
### Changed
- Added get all hosted cVRP consents endpoint to Hosted Pages cVRP

## [2.29.0] - 2024-02-01
### Changed
- Added Hosted Pages cVRP endpoints

## [2.28.5] - 2024-01-31
### Changed
- Change to use PaymentContextType instead of PaymentContextTypeV1_2 for all related endpoints

## [2.28.4] - 2024-01-24
### Added
- Added missing fields `paymentLifecycleId` to SubmissionResponse of VRP payment endpoints

## [2.28.3] - 2024-01-23
### Added
- Added new fields `purposeCode`, `payee.accountType` and new enums to field `contextType` to align to latest OBUK spec for TRI pilot. And also update the examples

## [2.28.2] - 2023-12-11
### Added
- Updated `Consent` schema field `isDeletedByInstitution` to indicate the consent was successfully deleted with the institution


## [2.28.1] - 2023-12-08
### Fixed
- Fix Frequency and Alignment enums in VrpConfiguration

## [2.28.1] - 2023-12-06
### Added
- Added `recurringPaymentCategory` for Non Sweeping VRPs. Add support for additional non sweeping VRP changes.

## [2.28.0] - 2023-12-01
### Added
- Added recurringPaymentCategory, maximumCumulativeAmount and maximumCumulativeNumberOfPayments fields for applications VRP configuration (Application Management)

## [2.27.0] - 2023-11-16
### Added
- Added endpoints to manage applications VRP configurations (Application Management)

## [2.26.0] - 2023-11-15
### Added
- Refactor ValidationError enum

## [2.26.0] - 2023-11-14
### Added
- Added endpoints to manage applications and sub-applications (Application Management)

## [2.25.1] - 2023-11-10
### Added
- Updated `Consent` schema with two new fields `softDeleted` and `deletedByInstitution`

## [2.25.0] - 2023-09-27
### Added
- Add `dependentRequired` field to the Schema component for Payment Constraint GET endpoint.

## [2.24.1] - 2023-09-18
### Fixed
- Add x-beta field to the Yapily Constraints endpoints and define enum types explicitly

## [2.24.0] - 2023-09-14
### Added
- Add public endpoints to use Yapily Constraints

## [2.23.0] - 2023-09-11
### Changed
- Included the `hostedPaymentId` and `consentId` fields on the Yapily Hosted Pages GET endpoint

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