package com.atlas.bank.atlas_bank.infrastructure.adapter.out.persistence;

import com.atlas.bank.atlas_bank.domain.model.account.Account;
import com.atlas.bank.atlas_bank.domain.model.shared.Email;
import org.springframework.stereotype.Component;

@Component
public class AccountPersistenceMapper {

    public Account toDomain(AccountJpaEntity entity) {
        if (entity == null) return null;
        return Account.builder()
                .id(entity.getId())
                .accountNumber(entity.getAccountNumber())
                .ownerName(entity.getOwnerName())
                .email(entity.getEmail() != null
                        ? Email.of(entity.getEmail().getValue())
                        : null)
                .type(entity.getType())
                .balance(entity.getBalance() != null
                        ? com.atlas.bank.atlas_bank.domain.model.shared.Money.of(
                        entity.getBalance().getAmount(),
                        com.atlas.bank.atlas_bank.domain.model.shared.Currency.valueOf(
                                entity.getBalance().getCurrency().name()))
                        : null)
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .customerId(entity.getCustomerId())
                .build();
    }

    public AccountJpaEntity toJpaEntity(Account account) {
        if (account == null) return null;
        AccountJpaEntity entity = new AccountJpaEntity();
        entity.setId(account.getId());
        entity.setAccountNumber(account.getAccountNumber());
        entity.setOwnerName(account.getOwnerName());
        entity.setEmail(account.getEmail() != null
                ? com.atlas.bank.atlas_bank.infrastructure.adapter.out.persistence.Email.of(account.getEmail().getValue())
                : null);
        entity.setType(account.getType());
        entity.setBalance(account.getBalance() != null
                ? Money.of(
                account.getBalance().getAmount(),
                com.atlas.bank.atlas_bank.domain.model.shared.Currency.valueOf(
                        account.getBalance().getCurrency().name()))
                : null);
        entity.setStatus(account.getStatus());
        entity.setCreatedAt(account.getCreatedAt());
        entity.setCustomerId(account.getCustomerId());
        return entity;
    }
}