package com.atlas.bank.atlas_bank.transaction.service.transfer;

import com.atlas.bank.atlas_bank.account.exception.AccountNotFoundException;
import com.atlas.bank.atlas_bank.account.model.Account;
import com.atlas.bank.atlas_bank.transaction.exception.AccountNotActiveException;
import com.atlas.bank.atlas_bank.transaction.exception.InsufficientFundsException;
import com.atlas.bank.atlas_bank.transaction.model.Transaction;
import com.atlas.bank.atlas_bank.account.repository.AccountRepository;
import com.atlas.bank.atlas_bank.transaction.repository.TransactionRepository;
import com.atlas.bank.atlas_bank.transaction.service.factory.TransactionFactory;
import com.atlas.bank.atlas_bank.transaction.service.fee.FeeCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService extends TransactionProcessor<TransferContext> implements ITransferService {

    private final AccountRepository accountRepository;
    private final List<FeeCalculator> feeCalculators;

    public TransferService(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository, List<FeeCalculator> feeCalculators) {
        super(transactionRepository);
        this.accountRepository = accountRepository;
        this.feeCalculators = feeCalculators;
    }

    @Override
    @Transactional
    public Transaction execute(Long fromId, Long toId, BigDecimal amount){
        //buscar cuentas
        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new AccountNotFoundException(fromId));
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new AccountNotFoundException(toId));

        return process(new TransferContext(from, to, amount));
    }

    @Override
    protected void validate(TransferContext ctx) {
        if (!"ACTIVE".equals(ctx.from().getStatus())) {
            throw new AccountNotActiveException(ctx.from().getId(), ctx.from().getStatus());
        }
        if (!"ACTIVE".equals(ctx.to().getStatus())) {
            throw new AccountNotActiveException(ctx.to().getId(), ctx.to().getStatus());
        }
        if (ctx.from().getBalance().compareTo(ctx.amount()) < 0) {
            throw new InsufficientFundsException(ctx.from().getId(), ctx.from().getBalance(), ctx.amount());
        }
    }

    @Override
    protected BigDecimal calculateFee(TransferContext context) {
        return feeCalculators.stream()
                .filter(fc -> fc.supports(context.from().getType()))
                .findFirst()
                .orElseThrow( () ->  new RuntimeException("No hay calculador para el tipo " +
                        context.from().getType()))
                .calculate(context.amount());
    }

    @Override
    protected void execute(TransferContext ctx, BigDecimal fee) {
        ctx.from().setBalance(ctx.from().getBalance().subtract(ctx.amount()).subtract(fee));
        ctx.to().setBalance(ctx.to().getBalance().add(ctx.amount()));
        accountRepository.save(ctx.from());
        accountRepository.save(ctx.to());
    }

    @Override
    protected Transaction save(TransferContext ctx, BigDecimal fee) {
        Transaction transaction = TransactionFactory.createTransfer(ctx, fee);
        return transactionRepository.save(transaction);
    }
}