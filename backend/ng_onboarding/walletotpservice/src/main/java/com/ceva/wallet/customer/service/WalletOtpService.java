package com.ceva.wallet.customer.service;

import org.json.JSONObject;

import com.ceva.wallet.dto.GenericResponseDTO;

/**
 * @author CEVA - Ravi Dirisam
 * @category - Wallet OTP Service
 * @version - v1.0
 * @since - 20-10-2025
 *
 */

public interface WalletOtpService {
	
	
	//otp 
	public GenericResponseDTO sendOtp(JSONObject jrequest);
	public GenericResponseDTO verifyOtp(JSONObject jrequest);

}
