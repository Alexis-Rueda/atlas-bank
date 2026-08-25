package com.atlas.bank.atlas_bank.transaction.service.fraud;

public record FraudCheckResult(boolean blocked, String reason) {
    public static FraudCheckResult allowed(){
        return new FraudCheckResult(false, null);
    }

    public static FraudCheckResult blocked(String reason){
        return new FraudCheckResult(true, reason);
    }
}