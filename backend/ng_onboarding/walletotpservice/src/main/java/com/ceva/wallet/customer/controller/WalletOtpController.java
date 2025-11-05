package com.ceva.wallet.customer.controller;

import com.ceva.wallet.dto.GenericResponseDTO;
import com.ceva.wallet.i18n.Translator;
import com.ceva.wallet.dto.GenericRequestDTO;
import com.ceva.wallet.util.ErrorCodes;
import com.ceva.wallet.validation.JsonSchemaValidatorService;
import com.ceva.wallet.validation.ValidateFormFields;

import jakarta.servlet.http.HttpServletRequest;

import com.ceva.wallet.customer.service.WalletAuthorizationService;
import com.ceva.wallet.customer.service.WalletOtpService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping({ "/v1/wallet/" })
public class WalletOtpController {
	
	Logger logger = LoggerFactory.getLogger(WalletOtpController.class);

	@Autowired
	private WalletOtpService walletOtpService;

	@Autowired
	WalletAuthorizationService walletAuthorizationService;
	
	private final JsonSchemaValidatorService jsonSchemaValidatorService;
	
	public WalletOtpController(JsonSchemaValidatorService jsonSchemaValidatorService) {
        this.jsonSchemaValidatorService = jsonSchemaValidatorService;
    }
	
	
	

	@PostMapping(value = "/sendotp", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseEntity<GenericResponseDTO> sendOtp(@RequestBody GenericRequestDTO genericRequestDTO, HttpServletRequest httpServletRequest){
		
		GenericResponseDTO  genericResponseDTO = new GenericResponseDTO();
		
		try {
			 
		    logger.debug("Request DTO ->", genericRequestDTO.toString());
		    
		       
	        genericResponseDTO.setResponseCode(ErrorCodes.INIT_FAIL.getResponseCode());
	        genericResponseDTO.setResponseDescription(Translator.toLocale(ErrorCodes.INIT_FAIL.getResponseDescription()));
	        
	       
		    // Validate the form
		    genericResponseDTO = new ValidateFormFields(jsonSchemaValidatorService).validateForm(genericRequestDTO);
		    
		    if("000".equals(genericResponseDTO.getResponseCode())) {
		    	//success
		    	JSONObject jrequest = new JSONObject();
		    	
			    jrequest.put("jheader", genericRequestDTO.getJheader());
			    jrequest.put("jbody", genericRequestDTO.getJbody());
			    
			    String headerLang = httpServletRequest.getHeader("Accept-Language");
				jrequest.getJSONObject("jbody").put("headerLang", headerLang==null ? "en-US" : headerLang );
			    
			    genericResponseDTO = walletOtpService.sendOtp(jrequest);
		    	
		    }else {
		    	//fail
		    	 ResponseEntity.badRequest().body(genericResponseDTO);
		    }
		    
		}catch (Exception e) {
			e.printStackTrace();
			 genericResponseDTO.setResponseCode(ErrorCodes.UNABLE_TO_PROCESS.getResponseCode());
		     genericResponseDTO.setResponseDescription(Translator.toLocale(ErrorCodes.UNABLE_TO_PROCESS.getResponseDescription()));
		}
	   
	    return new ResponseEntity<>(genericResponseDTO, HttpStatus.OK);
	}
	
	@PostMapping(value = "/verifyotp", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseEntity<GenericResponseDTO> verifyOtp(@RequestBody GenericRequestDTO genericRequestDTO,HttpServletRequest httpServletRequest){
		
		GenericResponseDTO  genericResponseDTO = new GenericResponseDTO();
		
		try {
			 
		    logger.debug("Request DTO ->", genericRequestDTO.toString());
		    
		       
	        genericResponseDTO.setResponseCode(ErrorCodes.INIT_FAIL.getResponseCode());
	        genericResponseDTO.setResponseDescription(Translator.toLocale(ErrorCodes.INIT_FAIL.getResponseDescription()));
	        
	       
		    // Validate the form
		    genericResponseDTO = new ValidateFormFields(jsonSchemaValidatorService).validateForm(genericRequestDTO);
		    
		    if("000".equals(genericResponseDTO.getResponseCode())) {
		    	//success
		    	JSONObject jrequest = new JSONObject();
			    jrequest.put("jheader", genericRequestDTO.getJheader());
			    jrequest.put("jbody", genericRequestDTO.getJbody());
			    
			    String headerLang = httpServletRequest.getHeader("Accept-Language");
				jrequest.getJSONObject("jbody").put("headerLang", headerLang==null ? "en-US" : headerLang );
				
			    genericResponseDTO = walletOtpService.verifyOtp(jrequest);
		    	
		    }else {
		    	//fail
		    	 ResponseEntity.badRequest().body(genericResponseDTO);
		    }
		    
		}catch (Exception e) {
			e.printStackTrace();
			 genericResponseDTO.setResponseCode(ErrorCodes.UNABLE_TO_PROCESS.getResponseCode());
		     genericResponseDTO.setResponseDescription(Translator.toLocale(ErrorCodes.UNABLE_TO_PROCESS.getResponseDescription()));
		}
	   
	    return new ResponseEntity<>(genericResponseDTO, HttpStatus.OK);
	}
	
	
	public static void main(String args[])  {
		
	
	}
	
}
