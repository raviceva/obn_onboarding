package com.ceva.wallet.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceva.wallet.customer.entity.OtpDataTabl;

public interface OtpDataTablRepository extends JpaRepository<OtpDataTabl, Integer> {
  OtpDataTabl findByUserId(String paramString);
  
  OtpDataTabl findFirstByUserIdOrderByMakerDttmDesc(String paramString);
  
  OtpDataTabl findByUserIdAndOtp(String paramString1, String paramString2);
  
  OtpDataTabl findByUserIdAndOtpAndOtpStatusOrderByMakerDttmDesc(String paramString1, String paramString2, String paramString3);
  
  OtpDataTabl findTopByUserIdOrderByMakerDttmDesc(String paramString);
  
  long countByUserIdAndOtpAndOtpStatus(String paramString1, String paramString2, String paramString3);
  
  List<OtpDataTabl> findByMobileNoAndOtpStatus(String userId, String otpStatus);
  
  
  
}