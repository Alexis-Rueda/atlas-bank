package com.atlas.bank.atlas_bank.transaction.dto;

import com.atlas.bank.atlas_bank.domain.model.transaction.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponse toResponse(Transaction transaction);
}