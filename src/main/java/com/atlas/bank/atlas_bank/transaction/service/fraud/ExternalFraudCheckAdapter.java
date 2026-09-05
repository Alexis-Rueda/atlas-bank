package com.atlas.bank.atlas_bank.transaction.service.fraud;

import com.atlas.bank.atlas_bank.application.port.out.FraudCheckPort;
import com.atlas.bank.atlas_bank.domain.model.shared.FraudCheckResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class ExternalFraudCheckAdapter implements FraudChecker, FraudCheckPort {

    @Override
    public FraudCheckResult check(Long accountId, BigDecimal amount) {

        ExternalFraudResponse response = callExternalApi(accountId, amount);

        log.info("Respuesta del servicio de fraude — riesgo: {}, score: {}, recomendación: {}",
                response.getRiskLevel(), response.getScore(), response.getRecommendation());

        if("BLOCK".equals(response.getRecommendation())){
            return FraudCheckResult.blocked(
                    "Operación bloqueada por riesgo " + response.getRiskLevel()
                            + " (score: " + response.getScore() + ")"
            );
        }

        return FraudCheckResult.allowed();
    }


    private ExternalFraudResponse callExternalApi(Long accountId, BigDecimal amount){
        if(amount.compareTo(new BigDecimal("1000000")) > 0){
            return new ExternalFraudResponse("HIGH", 0.95, "BLOCK");
        }

        return new ExternalFraudResponse("LOW", 0.1, "ALLOW");
    }
}