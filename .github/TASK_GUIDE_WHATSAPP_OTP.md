# Task Guide — Implement WhatsApp OTP in `walletotpservice`

---

## 🎯 Objective
Enhance the existing **`walletotpservice`** to send OTPs via **WhatsApp** in addition to SMS, while using the same request and response format already defined in the service.

---

## 📂 Reference Location
backend/ng_onboarding/walletotpservice/
Focus on:
- `WalletOtpController.java`
- `WalletOtpService.java`
- `WalletotpServiceImpl.java`
- `application.properties`

These define OTP generation, validation, and SMS delivery flow.

---

## 🔁 Branch Workflow

Always work on a new feature branch based on `development`.

```bash
git checkout development
git pull origin development
git checkout -b feature/walletotp-whatsapp-otp

Do not commit directly to main or development.

Copilot Prompt Example:

// Copilot instructions: 
// Create a POST endpoint /v1/wallet/sendwhatsappotp
// will accept GenericRequestDTO as @RequestBody 
// that calls sendOtpViaWhatsApp() in OtpService
// Return GenericResponseDTO  matching the existing structure

Expected endpoint: POST /v1/wallet/sendwhatsappotp


// Copilot instructions: Implement sendOtpViaWhatsApp(String phoneNumber, String otpCode)
// similar to sendOtpViaSms()
// Use whatsapp.api.url and whatsapp.api.key from application.properties
// Return OtpResponse using the same format as sendOtpViaSms()

Expected:
	•	A new method sendOtpViaWhatsApp()
	•	Logs for request, response, and exceptions
	•	Reuse of existing DTOs (OtpRequest, OtpResponse)
	•	WhatsApp API integration using configuration values

Request :

{
    "jheader": {
         "ip": "10.0.2.15",
        "version": "1.0.0",
        "channel": "MOBILE",
        "requestType": "states",
        "imeiNo": "5iuv8iuhr1K3WyFrkVbK8ChhRK37uonmr0+q+OSQgL",
        "serialNo": "12345678",
        "deviceType": "sdk_gphone64_arm64",
        "osType": "android",
        "macAddress": "test",
        "userId": "243918203486"
    },
    "jbody": {
       "mobileNumber":"2349030009911"
    }
}


Response :

{
    "requestId": null,
    "responseCode": "00",
    "responseDescription": "Success",
    "errorMessage": null,
    "data": []
}

Step 3: Update Configuration

WhatsApp API properties in application.properties:

whatsapp.api.url=https://api.whatsapp.example.com/send
whatsapp.api.key=<your-api-key>


Step 4: Add Unit Test

Use Copilot to assist in generating test cases in OtpServiceTest.java.

// Copilot: Create JUnit test for sendOtpViaWhatsApp()
// Mock API call and verify OtpResponse for both success and failure
