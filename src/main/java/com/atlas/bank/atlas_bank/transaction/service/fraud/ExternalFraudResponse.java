package com.atlas.bank.atlas_bank.transaction.service.fraud;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExternalFraudResponse {
    private String riskLevel;      // LOW, MEDIUM, HIGH
    private double score;           // 0.0 a 1.0
    private String recommendation;  // ALLOW, REVIEW, BLOCK
}