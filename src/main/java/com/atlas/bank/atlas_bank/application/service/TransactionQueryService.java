package com.atlas.bank.atlas_bank.application.service;

import com.atlas.bank.atlas_bank.application.port.out.TransactionRepositoryPort;
import com.atlas.bank.atlas_bank.domain.model.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionQueryService implements ITransactionQueryService{
    private final TransactionRepositoryPort transactionRepository;
    @Override
    public List<Transaction> getByAccountId(Long accountId){
        return transactionRepository.findBySourceAccountIdOrTargetAccountId(accountId, accountId);
    }
}
