package com.atlas.bank.atlas_bank.transaction.service;

import com.atlas.bank.atlas_bank.account.model.Account;
import com.atlas.bank.atlas_bank.transaction.model.Transaction;
import com.atlas.bank.atlas_bank.account.repository.AccountRepository;
import com.atlas.bank.atlas_bank.transaction.repository.TransactionRepository;
import com.atlas.bank.atlas_bank.transaction.service.fee.FeeCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferService implements ITransferService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final List<FeeCalculator> feeCalculators;

    @Override
    @Transactional
    public Transaction execute(Long fromId, Long toId, BigDecimal amount){
        //buscar cuentas
        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        //validar que la cuenta esté activa
        if (!"ACTIVE".equals(from.getStatus())) {
            throw new RuntimeException("La cuenta origen no está activa");
        }
        if (!"ACTIVE".equals(to.getStatus())) {
            throw new RuntimeException("La cuenta destino no está activa");
        }

        //validar los fondos
        if (from.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Fondos insuficientes");
        }

        //calcular comisiones
        BigDecimal fee = feeCalculators.stream()
                .filter(fc -> fc.supports(from.getType()))
                .findFirst()
                .orElseThrow( () ->  new RuntimeException("No hay calculador para el tipo " + from.getType()))
                .calculate(amount);


        //actualización de saldos
        from.setBalance(from.getBalance().subtract(amount).subtract(fee));
        to.setBalance(to.getBalance().add(amount));
        accountRepository.save(from);
        accountRepository.save(to);

        //crear transacción
        Transaction transaction = new Transaction();
        transaction.setType("TRANSFER");
        transaction.setSourceAccountId(fromId);
        transaction.setTargetAccountId(toId);
        transaction.setAmount(amount);
        transaction.setFee(fee);
        transaction.setStatus("EXECUTED");

        return transactionRepository.save(transaction);

    }
}
