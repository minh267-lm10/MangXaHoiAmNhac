package com.devteria.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteria.identity.entity.PaymentDataPoint;

public interface PaymentDataPointRepository extends JpaRepository<PaymentDataPoint, String> {}
