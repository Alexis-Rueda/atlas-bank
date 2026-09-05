package com.atlas.bank.atlas_bank.transaction.service.listener;

import com.atlas.bank.atlas_bank.domain.event.TransactionExecutedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {
    @EventListener
    public void onTransactionExecuted(TransactionExecutedEvent event){
        log.info("Enviando comprobante de {} por ${} — Transacción #{}",
                event.type(), event.amount(), event.transactionId());
    }
}