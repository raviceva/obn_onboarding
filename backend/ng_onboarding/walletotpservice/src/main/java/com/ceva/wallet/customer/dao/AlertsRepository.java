package com.ceva.wallet.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ceva.wallet.customer.entity.Alerts;

public interface AlertsRepository extends JpaRepository<Alerts,Long>{
	
}