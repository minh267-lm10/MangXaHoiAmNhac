package com.devteria.identity.repository;

import com.devteria.identity.entity.PaymentDataPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDataPointRepository extends JpaRepository<PaymentDataPoint, String> {
}
