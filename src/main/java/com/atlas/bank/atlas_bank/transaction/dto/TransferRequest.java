package com.atlas.bank.atlas_bank.transaction.dto;

import com.atlas.bank.atlas_bank.transaction.validation.DifferentAccounts;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
@DifferentAccounts
public class TransferRequest {
    @NotNull(message = "La cuenta de origen es obligatoria")
    private Long fromAccountId;

    @NotNull(message = "La cuenta de destino es obligatoria")
    private Long toAccountId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    private BigDecimal amount;
}