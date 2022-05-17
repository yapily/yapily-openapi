
import re
import time
import json
import os
import sys
import uuid
import requests
import openapi_client as openapi_client
import base64

from openapi_client.rest import ApiException
from openapi_client.models import *
from pprint import pprint

# The client must configure the authentication and authorization parameters
# in accordance with the API server security policy.
# Examples for each auth method are provided below, use the example that
# satisfies your auth use case.

# Configure HTTP basic authorization: basicAuth
configuration = openapi_client.Configuration(
    username= os.getenv('APP_UUID', ''),
    password= os.getenv('APP_SECRET', '')
)

INSTITUTION = "mock-sandbox"

def create_new_user(user_api_instance):
    new_user = NewApplicationUser(application_user_id=str(
        uuid.uuid4()), reference_id='Test user python')
    user_api_response = user_api_instance.add_user(new_user)
    return user_api_response

def delete_user(user_api_instance, user_id):
    return user_api_instance.delete_user(user_id)

"""
* This function is for example purposes only.
* The expected flow involves the user retrieving his consentToken using the URL returned as part of the Authorisation process.
* We can help you on https://docs.yapily.com/support/".
"""
def authenticate_and_fetch_consent(authUrl):
    q_params = authUrl.split("?")
    components = q_params[1].split("&")
    components = [(x.split("=")[0], x.split("=")[1]) for x in components]
    state = [x for x in components if x[0] == "state"][0]
    print(state)
    url = f"{configuration.host}/exchange-code/"
    payload = {"code":"FAKE_CODE","id_token":"FAKE_ID_TOKE","state":f"{state[1]}"}
    headers = {
        'Content-type': 'application/json'
    }
    print(payload)
    response = requests.request(
        "POST", url, headers=headers, data=json.dumps(payload))
    response_body = None
    print(f'Got a response {response.status_code} on exchange-code')
    try:
        response_body = response.json()['consentToken']
    except KeyError:
        print(f"Error fetching consent: {state}")
    return response_body

def fetch_accounts_and_transactions(accounts_api_instance, consent_token):
    accounts_response = accounts_api_instance.get_accounts(consent_token)
    accounts = accounts_response.data
    for account in accounts:
        account_transactions_response = accounts_api_instance.get_transactions(
            account.id, consent_token)
        account_transactions = account_transactions_response.data
        for account_transaction in account_transactions:
            pprint(account_transaction)


def create_payment_req():
    amount = Amount(amount=0.1, currency="GBP")
    account_identification = AccountIdentification(
            type=AccountIdentificationType.ACCOUNT_NUMBER, identification="23819264")
    account_identification2 = AccountIdentification(
            type=AccountIdentificationType.SORT_CODE, identification="219264")
    payee = Payee(name="Python user", account_identifications=[
                      account_identification, account_identification2])
    payment_req = PaymentRequest(payment_idempotency_id="text",
                                     amount=amount,
                                     reference="Test",
                                     type=PaymentType.DOMESTIC_PAYMENT,
                                     payee=payee)
                                 
    return payment_req

def ais_flow():
    # AIS flow
    try:
        user_api_response = create_new_user(user_api_instance)
        account_req = AccountAuthorisationRequest(
            application_user_id=user_api_response.application_user_id, institution_id=INSTITUTION)
        account_account_request = auth_api_instance.initiate_account_request(
            account_req)
        data = account_account_request.data
        authUrl = data.authorisation_url
        # authorise the consent received
        consent_token = authenticate_and_fetch_consent(authUrl)
        if consent_token is not None:
            fetch_accounts_and_transactions(accounts_api_instance, consent_token)
            delete_user(user_api_instance, user_api_response.uuid)
    except ApiException as e:
        print("Exception thrown: %s\n" % e)

def pis_flow():
     # PIS flow
    try:
        user_api_response = create_new_user(user_api_instance)
        payment_req = create_payment_req()
        payment_auth_req = PaymentAuthorisationRequest(user_uuid = str(user_api_response.uuid), 
            institution_id=INSTITUTION,
            payment_request=payment_req)
        print(payment_auth_req)
        api_response = auth_api_instance.create_payment_authorisation(
            payment_auth_req)
        data = api_response.data
        authUrl = data.authorisation_url
        consent_token = authenticate_and_fetch_consent(authUrl)
        if consent_token is not None:
            payment_api_response = payment_api_instance.create_payment(
                consent_token, payment_req)
            payment_response = payment_api_response.data
            payment_get_api_response = payment_api_instance.get_payments(
                payment_response.id, consent_token)
            for payment in payment_get_api_response.data.payments:
                print(payment)
            delete_user(user_api_instance, user_api_response.uuid)
    except ApiException as e:
        print("Exception thrown: %s\n" % e)

def init_client(flow: str):
    with openapi_client.ApiClient(configuration) as api_client:
        global auth_api_instance
        global user_api_instance
        global consents_api_instance
        global accounts_api_instance
        global payment_api_instance
        print(f'Preparing API instances for {flow}')
        auth_api_instance = openapi_client.AuthorisationsApi(api_client)
        user_api_instance = openapi_client.UsersApi(api_client)
        consents_api_instance = openapi_client.ConsentsApi(api_client)
        accounts_api_instance = openapi_client.FinancialDataApi(api_client)
        payment_api_instance = openapi_client.PaymentsApi(api_client)
        
        if(flow=="AIS"):
            ais_flow()
        else:
            pis_flow()

if __name__ == "__main__":
    print(f'Number of arguments: {len(sys.argv)} arguments.')
    flow = sys.argv[1]
    print(f'Argument used: {flow}')
    init_client(flow)