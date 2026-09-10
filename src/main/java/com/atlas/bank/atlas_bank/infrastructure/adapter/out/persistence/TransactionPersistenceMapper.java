package com.atlas.bank.atlas_bank.infrastructure.adapter.out.persistence;

import com.atlas.bank.atlas_bank.domain.model.transaction.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionPersistenceMapper {

    public Transaction toDomain(TransactionJpaEntity entity) {
        if (entity == null) return null;
        return Transaction.builder()
                .id(entity.getId())
                .type(entity.getType())
                .sourceAccountId(entity.getSourceAccountId())
                .targetAccountId(entity.getTargetAccountId())
                .amount(entity.getAmount())
                .fee(entity.getFee())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public TransactionJpaEntity toJpaEntity(Transaction transaction) {
        if (transaction == null) return null;
        return TransactionJpaEntity.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .sourceAccountId(transaction.getSourceAccountId())
                .targetAccountId(transaction.getTargetAccountId())
                .amount(transaction.getAmount())
                .fee(transaction.getFee())
                .status(transaction.getStatus())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}