package com.atlas.bank.atlas_bank.account.dto;

import com.atlas.bank.atlas_bank.account.model.Account;
import com.atlas.bank.atlas_bank.shared.model.Currency;
import com.atlas.bank.atlas_bank.shared.model.Email;
import com.atlas.bank.atlas_bank.shared.model.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "balance", source = "balance", qualifiedByName = "toMoney")
    @Mapping(target = "email", source = "email", qualifiedByName = "toEmail")
    Account toEntity(CreateAccountRequest request);

    @Mapping(target = "balance", source = "balance", qualifiedByName = "toAmount")
    @Mapping(target = "email", source = "email", qualifiedByName = "fromEmail")
    AccountResponse toResponse(Account account);

    @Named("toMoney")
    default Money toMoney(BigDecimal amount){
        if(amount==null) return null;
        return Money.of(amount, Currency.ARS);
    }

    @Named("toAmount")
    default BigDecimal toAmount(Money money){
        if (money==null) return null;
        return money.getAmount();
    }

    @Named("toEmail")
    default Email toEmail(String email) {
        if (email == null) return null;
        return Email.of(email);
    }

    @Named("fromEmail")
    default String fromEmail(Email email) {
        if (email == null) return null;
        return email.getValue();
    }
}