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
@Table(name = "alerts")
public class Alerts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="MSG_DATE")
	Date msgDate;
	
	@Column(name="EMAIL_ID")
	String emailId;
	
	@Column(name="MOBILE_NO")
	String mobileNo;

	@Column(name="APPL")
	String appl;
	
	@Column(name="FETCH_STATUS")
	String fetchStatus;
	
	@Column(name="MAILFROM")
	String mailFrom;
	
	@Column(name="MAILTO")
	String mailTo;
	
	@Column(name="MAILCC")
	String mailCc;
	
	@Column(name="MAILBCC")
	String mailBcc;
	
	@Column(name="SUBJECT")
	String subject;
	
	@Column(name="ATTACHMENT_LOC")
	String attachementLoc;

	@Column(name="RETRY_COUNT")
	int retryCount;
	
	@Column(name="TEMPLATE_ID")
	String templateId;
	
	@Column(name="OUT_MESSAGE")
	String outMessage;
	
	@Column(name="IS_PUSH_NOTIFICATION_REQ")
	String isPushNotificationReq;
	
	@Column(name="PUSH_NOTIFICATION_STATUS")
	String pushNotificationStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAppl() {
		return appl;
	}

	public void setAppl(String appl) {
		this.appl = appl;
	}

	public String getFetchStatus() {
		return fetchStatus;
	}

	public void setFetchStatus(String fetchStatus) {
		this.fetchStatus = fetchStatus;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailCc() {
		return mailCc;
	}

	public void setMailCc(String mailCc) {
		this.mailCc = mailCc;
	}

	public String getMailBcc() {
		return mailBcc;
	}

	public void setMailBcc(String mailBcc) {
		this.mailBcc = mailBcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachementLoc() {
		return attachementLoc;
	}

	public void setAttachementLoc(String attachementLoc) {
		this.attachementLoc = attachementLoc;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getOutMessage() {
		return outMessage;
	}

	public void setOutMessage(String outMessage) {
		this.outMessage = outMessage;
	}

	public String getIsPushNotificationReq() {
		return isPushNotificationReq;
	}

	public void setIsPushNotificationReq(String isPushNotificationReq) {
		this.isPushNotificationReq = isPushNotificationReq;
	}

	public String getPushNotificationStatus() {
		return pushNotificationStatus;
	}

	public void setPushNotificationStatus(String pushNotificationStatus) {
		this.pushNotificationStatus = pushNotificationStatus;
	}

	@Override
	public String toString() {
		return "Alerts [id=" + id + ", msgDate=" + msgDate + ", emailId=" + emailId + ", mobileNo=" + mobileNo
				+ ", appl=" + appl + ", fetchStatus=" + fetchStatus + ", mailFrom=" + mailFrom + ", mailTo=" + mailTo
				+ ", mailCc=" + mailCc + ", mailBcc=" + mailBcc + ", subject=" + subject + ", attachementLoc="
				+ attachementLoc + ", retryCount=" + retryCount + ", templateId=" + templateId + ", outMessage="
				+ outMessage + ", isPushNotificationReq=" + isPushNotificationReq + ", pushNotificationStatus="
				+ pushNotificationStatus + "]";
	}
	
	
	
	
}
