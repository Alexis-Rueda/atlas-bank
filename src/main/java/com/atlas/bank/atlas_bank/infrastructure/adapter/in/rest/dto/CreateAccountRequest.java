package com.atlas.bank.atlas_bank.infrastructure.adapter.in.rest.dto;

import com.atlas.bank.atlas_bank.domain.model.account.AccountType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CreateAccountRequest {
    @NotBlank(message = "El número de cuenta es obligatorio")
    private String accountNumber;

    @NotBlank(message = "El nombre del titular es obligatorio")
    private String ownerName;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    @NotNull(message = "El tipo de cuenta es obligatorio")
    private AccountType type;

    @PositiveOrZero(message = "El balance no puede ser negativo")
    private BigDecimal balance;
}