package com.ceva.wallet.util;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import com.ceva.wallet.customer.entity.Alerts;
import com.ceva.wallet.customer.entity.OtpDataTabl;

public class OtpMessageBuilder {

    public static void buildOtpMessage(Alerts alerts, OtpDataTabl otpDataTabl, String otp, String channel, Locale locale) {
        String transType = otpDataTabl.getOtpTemplate();
        String transTime = otpDataTabl.getMakerDttm().toGMTString();

        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        String expiryMsg = messages.getString("otp.expiry.message");

        String smsMsg;
        switch (transType) {
            case "WALLET_SELF_REG":
                smsMsg = MessageFormat.format(messages.getString("otp.registration"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "TXN_OTP":
                // Ensure amount formatting as string (adapt to your currency formatting if needed)
                String amountStr = formatAmount(otpDataTabl.getAmount());
                smsMsg = MessageFormat.format(messages.getString("otp.txn"),
                        otp, amountStr, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "FORGOT_PASSWORD":
                smsMsg = MessageFormat.format(messages.getString("otp.forgot.password"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "FORGOT_MPIN":
                smsMsg = MessageFormat.format(messages.getString("otp.forgot.mpin"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "FORGOT_TXNPIN":
                smsMsg = MessageFormat.format(messages.getString("otp.forgot.txnpin"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "CHANGE_MPIN":
                smsMsg = MessageFormat.format(messages.getString("otp.change.mpin"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "CHANGE_TXNPIN":
                smsMsg = MessageFormat.format(messages.getString("otp.change.txnpin"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "CHANGE_PASSWORD":
                smsMsg = MessageFormat.format(messages.getString("otp.change.password"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "SET_MPIN":
                smsMsg = MessageFormat.format(messages.getString("otp.set.mpin"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "DEV_REP_OTPGEN":
                smsMsg = MessageFormat.format(messages.getString("otp.device.activation"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "USSDOTPGEN":
                alerts.setFetchStatus("S");
                break;

            case "PINCREATION":
                smsMsg = MessageFormat.format(messages.getString("otp.pin.creation"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            case "SECURITY_QUES_UPDATE_OTP":
                smsMsg = MessageFormat.format(messages.getString("otp.security.questions"),
                        otp, channel, transTime, expiryMsg);
                alerts.setOutMessage(smsMsg);
                break;

            default:
                alerts.setOutMessage(messages.getString("otp.invalid.type"));
                break;
        }
    }

    private static String formatAmount(Object amountObj) {
        if (amountObj == null) {
            return "0";
        }
        if (amountObj instanceof BigDecimal) {
            return ((BigDecimal) amountObj).toPlainString();
        }
        return amountObj.toString();
    }
}
