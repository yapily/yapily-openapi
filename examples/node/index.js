
var YapilyApi = require('./dist');
var superagent = require("superagent");

var apiClient = YapilyApi.ApiClient.instance;
// Configure HTTP basic authorization: basicAuth
var basicAuth = apiClient.authentications['basicAuth'];
basicAuth.username = process.env.APP_UUID
basicAuth.password = process.env.APP_SECRET

var userApi = new YapilyApi.UsersApi();
var authApi = new YapilyApi.AuthorisationsApi();
var paymentApi = new YapilyApi.PaymentsApi();
var finDataApi = new YapilyApi.FinancialDataApi();

const INSTITUTION = "mock-sandbox";
// UTILS
function uuidv4() {
    return ([1e7] + -1e3 + -4e3 + -8e3 + -1e11).replace(/[018]/g, c =>
        (c ^ crypto.getRandomValues(new Uint8Array(1))[0] & 15 >> c / 4).toString(16)
    );
}


// wrapper functions

function createNewUser() {
    var newUser = new YapilyApi.NewApplicationUser(uuidv4, "Js user test");
    return userApi.addUser(newUser);
}

function deleteUser(userUuid) {
    return userApi.deleteUser(userUuid)
}

/**
 * This function is for example purposes only.
 * The expected flow involves the user retrieving his consentToken using the URL returned as part of the Authorisation process.
 * We can help you on https://docs.yapily.com/support/".
 */
async function authenticateAndFetchConsents(authUrl) {
    const parts = authUrl.split('?')[1];
    var params = parts.split("&");
    params = params.filter(function (value) { return value.includes("state"); });
    params = params[0].split("=");
    var body = {
        "code": "FAKE_CODE",
        "id_token": "FAKE_ID_TOKE",
        "state": params[1]
    };
    var data = JSON.stringify(body);
    var consentToken;
    return new Promise(function (resolve, reject) {
        superagent
            .post(apiClient.basePath + '/exchange-code/')
            .send(data)
            .trustLocalhost(true)
            .set('Content-Type', 'application/json')
            .end(function (err, res) {
                if (err) {
                    console.log("Error trying to make an exchange code call: ", err)
                    reject(err);
                } else {
                    console.log("Exchange code call response = ", res.status)
                    consentToken = (res.body['consentToken']);
                    resolve(consentToken);
                }
            });
    });
}

function createPaymentRequest() {
    var amount = new YapilyApi.Amount("0.1", "GBP");
    var accountId = new YapilyApi.AccountIdentification("ACCOUNT_NUMBER", "23819264");
    var accountId2 = new YapilyApi.AccountIdentification("SORT_CODE", "204514");
    const ids = [accountId, accountId2];
    var payee = new YapilyApi.Payee("Js user", ids)
    var paymentType = YapilyApi.PaymentType.constructFromObject("DOMESTIC_PAYMENT")
    var paymentRequest = new YapilyApi.PaymentRequest("text", paymentType, payee, amount)
    console.log(paymentRequest)
    return paymentRequest
}

async function pisFlow() {
    var userCreated
    await createNewUser(userApi).then((user) => {
        userCreated = user
    });
    console.log(userCreated)
    var paymentRequest = createPaymentRequest();
    var paymentAuthRequest = new YapilyApi.PaymentAuthorisationRequest()
    paymentAuthRequest['paymentRequest'] = paymentRequest
    paymentAuthRequest['institutionId'] = INSTITUTION
    paymentAuthRequest['userUuid'] = userCreated.uuid
    var authResponse;
    await authApi.createPaymentAuthorisation(paymentAuthRequest)
        .then((data) => { console.log(data); authResponse = data })
        .catch((error) => console.log(error));
    console.log(authResponse);
    var consentToken;
    await authenticateAndFetchConsents(authResponse.data.authorisationUrl).then(
        (resp) => {
            console.log(resp)
            consentToken = resp;
        });
    if (consentToken != undefined) {
        var payment;
        await paymentApi.createPayment(consentToken, paymentRequest)
            .then((resp) => { console.log(resp); payment = resp.data });
        await paymentApi.getPayment(payment.uuid, consentToken)
            .then((resp) => console.log(resp));
    }
}


async function aisFlow() {
    var userCreated;
    await createNewUser(userApi).then((user) => {
        userCreated = user
    });
    console.log(userCreated)
    var accAuthRequest = new YapilyApi.AccountAuthorisationRequest()
    accAuthRequest['institutionId'] = INSTITUTION
    accAuthRequest['userUuid'] = userCreated.uuid
    var authResponse;
    await authApi.initiateAccountRequest(accAuthRequest)
        .then((data) => { console.log(data); authResponse = data })
        .catch((error) => console.log(error));
    console.log(authResponse);
    var consentToken;
    await authenticateAndFetchConsents(authResponse.data.authorisationUrl).then(
        (resp) => {
            consentToken = resp;
        });
    if (consentToken != undefined) {
        var accounts;
        await finDataApi.getAccounts(consentToken)
            .then((resp) => { console.log(resp); accounts = resp.data });
        await accounts.forEach((account) => 
            {
                finDataApi.getTransactions(account.id, consentToken)
                .then((resp) => { console.log(resp.data) });
            }
        );
    }
    deleteUser(userCreated.uuid);
}

let flow = process.argv[2];
if(flow==="PIS"){
    pisFlow();
} else {
    aisFlow();
}