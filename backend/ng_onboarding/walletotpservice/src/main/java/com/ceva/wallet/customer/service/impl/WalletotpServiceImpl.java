package com.ceva.wallet.customer.service.impl;

import com.ceva.wallet.customer.dao.AlertsRepository;
import com.ceva.wallet.customer.dao.OtpDataTablRepository;
import com.ceva.wallet.dto.GenericResponseDTO;
import com.ceva.wallet.i18n.Translator;
import com.ceva.wallet.util.CommonUtils;
import com.ceva.wallet.util.ErrorCodes;
import com.ceva.wallet.util.OtpMessageBuilder;
import com.ceva.wallet.customer.entity.Alerts;
import com.ceva.wallet.customer.entity.OtpDataTabl;
import com.ceva.wallet.customer.model.WalletAuthorization;
import com.ceva.wallet.customer.service.WalletAuthorizationService;
import com.ceva.wallet.customer.service.WalletOtpService;
import com.ceva.wallet.customer.service.impl.WalletotpServiceImpl;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletotpServiceImpl implements WalletOtpService {
	
  
  @Autowired
  private OtpDataTablRepository otpDataTablRepository;
  
  @Autowired
  private AlertsRepository alertsRepository;
  
  @Autowired
  private WalletAuthorizationService walletAuthorizationService;
 /* @Autowired
  private MobCustBioDataRepository mobCustBioDataRepository;
  
  @Autowired
  private WalletCustomerLoginRepository walletCustomerLoginRepository;
  
  @Autowired
  private MobImeiDataRepository mobImeiDataRepository;
  
  @Autowired
  private UserLoginCredentialsRepository userLoginCredentialsRepository;
  
  @Autowired
  private MobCustomerDocInfoRepository mobCustomerDocInfoRepository;
  
  @Autowired
  private MobCustomerMasterRepository mobCustomerMasterRepository;*/
  
  GenericResponseDTO genericResponseDTO = null;
  
  Logger logger = LoggerFactory.getLogger(WalletotpServiceImpl.class);
  
  
  @Transactional
  public GenericResponseDTO sendOtp(JSONObject jrequest) {

	    genericResponseDTO = new GenericResponseDTO();

	    try {
	        logger.info("Request in Impl->" + jrequest);

	        // default response = internal error
	        genericResponseDTO.setResponseCode(ErrorCodes.INTERNAL_ERROR.getResponseCode());
	        genericResponseDTO.setResponseDescription(Translator.toLocale(ErrorCodes.INTERNAL_ERROR.getResponseDescription()));

	        // extract mobile & channel
	        String mobileNumber = CommonUtils.getMobileNo(
	                jrequest.has("jbody") && jrequest.getJSONObject("jbody").has("mobileNumber")
	                        ? jrequest.getJSONObject("jbody").getString("mobileNumber")
	                        : "");

	        String channel = jrequest.has("jheader") && jrequest.getJSONObject("jheader").has("channel")
	                ? jrequest.getJSONObject("jheader").getString("channel")
	                : ""; 

	        // expire previously unused OTPs
	        //new WalletRegistrationDao().updateOtpExpiredWhichNotUsed(mobileNumber);
	        // generate otp and encrypted value
	        String otp = CommonUtils.createRandomNumber(6);
	        String encryptedOtp = CommonUtils.b64_sha256(otp);

	        
	        // build and persist otpData
	        OtpDataTabl otpDataTabl = new OtpDataTabl();
	        otpDataTabl.setUserId(mobileNumber);
	        otpDataTabl.setMobileNo(mobileNumber);
	        otpDataTabl.setChannel(channel);
	        otpDataTabl.setEmailId(CommonUtils.getMobileNo(
	                jrequest.has("jbody") && jrequest.getJSONObject("jbody").has("emailId")
	                        ? jrequest.getJSONObject("jbody").getString("emailId")
	                        : ""));
	        otpDataTabl.setOtp(encryptedOtp);
	        otpDataTabl.setOtpStatus("ACTIVE");
	        otpDataTabl.setOtpTemplate(jrequest.has("jbody") && jrequest.getJSONObject("jbody").has("otpTemplate")
	                ? jrequest.getJSONObject("jbody").getString("otpTemplate")
	                : "");
	        otpDataTabl.setMakerId(jrequest.has("jbody") && jrequest.getJSONObject("jbody").has("userId")
	                ? jrequest.getJSONObject("jbody").getString("userId")
	                : "");
	        otpDataTabl.setMakerDttm(Date.from(Instant.now()));

	        otpDataTablRepository.save(otpDataTabl);
	        logger.info("Otp Data Table insertion Completed ::");

	        // prepare alerts
	        Alerts alerts = new Alerts();
	        alerts.setMobileNo(mobileNumber);
	        alerts.setAppl("SMS");
	        alerts.setFetchStatus("P");
	        alerts.setTemplateId(jrequest.has("jbody") && jrequest.getJSONObject("jbody").has("otpTemplate")
	                ? jrequest.getJSONObject("jbody").getString("otpTemplate")
	                : "");
	       alerts.setMsgDate(new Date());
	        alerts.setRetryCount(0);

	        // prepare locale (if provided in jheader.locale or prepare back to English)
	        Locale locale = Locale.forLanguageTag(jrequest.getJSONObject("jbody").getString("headerLang"));
	        

	        // Build and set the OTP message (i18n-aware). The builder sets outMessage or fetchStatus as needed.
	        OtpMessageBuilder.buildOtpMessage(alerts, otpDataTabl, otp, channel, locale);

	        // persist alert
	        alertsRepository.save(alerts);
	        logger.info("Alert Data Table insertion Completed ::");

	        // success response
	        genericResponseDTO.setResponseCode(ErrorCodes.SUCCESS.getResponseCode());
	        genericResponseDTO.setResponseDescription(Translator.toLocale(ErrorCodes.SUCCESS.getResponseDescription()));
	        

	        logger.info("Response from Impl->" + genericResponseDTO.toString());

	    } catch (Exception e) {
	        logger.error("Error in sendOtp", e);
	        genericResponseDTO.setResponseCode(ErrorCodes.INTERNAL_ERROR.getResponseCode());
	        genericResponseDTO.setResponseDescription(Translator.toLocale(ErrorCodes.INTERNAL_ERROR.getResponseDescription()));
	    }

	    return genericResponseDTO;
	}

  @Override
  @Transactional
  public GenericResponseDTO verifyOtp(JSONObject jrequest) {
	    
		  genericResponseDTO = new GenericResponseDTO();

	    try {
	      
	    	logger.info("Request in Impl->" + jrequest);
	    	genericResponseDTO.setResponseCode(ErrorCodes.INTERNAL_ERROR.getResponseCode());
		    genericResponseDTO.setResponseDescription(Translator.toLocale(ErrorCodes.INTERNAL_ERROR.getResponseDescription()));
	    	
		    // extract mobile 
	        String mobileNumber = CommonUtils.getMobileNo(
	                jrequest.has("jbody") && jrequest.getJSONObject("jbody").has("mobileNumber")
	                        ? jrequest.getJSONObject("jbody").getString("mobileNumber")
	                        : "");
		    
		    updateOtpExpiredWhichNotUsed(mobileNumber);
		    
		    String encryptedOtp = jrequest.has("jbody") && jrequest.getJSONObject("jbody").has("otp")
	                ? jrequest.getJSONObject("jbody").getString("otp") : "";
		    
		    WalletAuthorization walletAuthorization = new WalletAuthorization(); 
		    walletAuthorization.setServiceType("NONFINANCIAL");
		    walletAuthorization.setOtp(encryptedOtp);
		    walletAuthorization.setUserId(mobileNumber);
		    walletAuthorization.setServiceCode("VALIDATE_OTP");
		    
		    genericResponseDTO=walletAuthorizationService.validate(walletAuthorization);
		 
	      
	      logger.info("Response from Impl->" + genericResponseDTO.toString());
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	      genericResponseDTO.setResponseCode(ErrorCodes.INTERNAL_ERROR.getResponseCode());
	      genericResponseDTO.setResponseDescription(Translator.toLocale(ErrorCodes.INTERNAL_ERROR.getResponseDescription()));
	    } 
	    return genericResponseDTO;
  }
  
  
  
  public void updateOtpExpiredWhichNotUsed(String mobileNumber) {
      long ttlSeconds = 59; 

    
      List<OtpDataTabl> openOtps = otpDataTablRepository.findByMobileNoAndOtpStatus(mobileNumber, "ACTIVE");

      Instant now = Instant.now();
      Instant cutoff = now.minus(ttlSeconds, ChronoUnit.SECONDS);

      for (OtpDataTabl otp : openOtps) {
          try {
              Date initiatedDate = otp.getMakerDttm();
              if (initiatedDate == null) {
                  
                  otp.setOtpStatus("EXPIRED");
              } else {
                  Instant transInstant = initiatedDate.toInstant();
                  if (transInstant.isBefore(cutoff)) {
                      otp.setOtpStatus("EXPIRED");
                  }
              }
          } catch (Exception e) {
              
              otp.setOtpStatus("EXPIRED");
          }
      }

      // Save all updated entities
      if (!openOtps.isEmpty()) {
          otpDataTablRepository.saveAll(openOtps);
      }
  }
  
  
}

