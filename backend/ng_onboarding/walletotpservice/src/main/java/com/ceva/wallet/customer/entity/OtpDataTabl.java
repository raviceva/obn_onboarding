package com.ceva.wallet.customer.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "OTP_DATA_TABL")
public class OtpDataTabl {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Column(name="USER_ID")
	String userId;
	
	@Column(name="MOBILE_NO")
	String mobileNo;
	
	@Column(name="OTP")
	String otp;
	
	@Column(name="OTP_STATUS")
	String otpStatus;
	
	@Column(name="OTP_TEMPLATE")
	String otpTemplate;
	

	@Column(name="CHANNEL")
	String channel;
	
	@Column(name="EMAIL_ID")
	String emailId;
	
	@Column(name="AMOUNT")
	Long amount;
	
	
	@Column(name="MAKER_ID")
	String makerId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MAKER_DTTM")
	Date makerDttm;
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getOtpStatus() {
		return otpStatus;
	}

	public void setOtpStatus(String otpStatus) {
		this.otpStatus = otpStatus;
	}

	public String getOtpTemplate() {
		return otpTemplate;
	}

	public void setOtpTemplate(String otpTemplate) {
		this.otpTemplate = otpTemplate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getMakerId() {
		return makerId;
	}

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public Date getMakerDttm() {
		return makerDttm;
	}

	public void setMakerDttm(Date makerDttm) {
		this.makerDttm = makerDttm;
	}

	
	@Override
	public String toString() {
		return "OtpDataTabl [id=" + id + ", userId=" + userId + ", mobileNo=" + mobileNo + ", otp=" + otp
				+ ", otpStatus=" + otpStatus + ", otpTemplate=" + otpTemplate + ", channel=" + channel + ", emailId="
				+ emailId + ", amount=" + amount + ", makerId=" + makerId + ", makerDttm=" + makerDttm  + "]";
	}
	

	
	
}

