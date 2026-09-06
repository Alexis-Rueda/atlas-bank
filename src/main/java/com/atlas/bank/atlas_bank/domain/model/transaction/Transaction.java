package com.atlas.bank.atlas_bank.domain.model.transaction;

import com.atlas.bank.atlas_bank.domain.event.TransactionExecutedEvent;
import com.atlas.bank.atlas_bank.domain.model.transaction.state.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transaction {

    @EqualsAndHashCode.Include
    private Long id;
    private TransactionType type;
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;
    private BigDecimal fee;
    private TransactionStatus status;
    private LocalDateTime createdAt;
    private TransactionState state;

    @Builder.Default
    private final List<Object> domainEvents = new ArrayList<>();

    public TransactionState getState() {
        if (state == null) {
            state = switch (status) {
                case PENDING -> new PendingState();
                case VALIDATED -> new ValidatedState();
                case EXECUTED -> new ExecutedState();
                case REJECTED -> new RejectedState();
                case REVERSED -> new ReversedState();
            };
        }
        return state;
    }

    public void advanceTo(TransactionState newState) {
        state = newState;
        status = newState.status();
    }

    public void markAsExecuted() {
        domainEvents.add(new TransactionExecutedEvent(
                id, type.name(), sourceAccountId,
                targetAccountId, amount, fee
        ));
    }

    public void executeTransfer() {
        advanceTo(getState().validate());
        advanceTo(getState().execute());
        markAsExecuted();
    }

    public List<Object> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }

    public void initDefaults() {
        if (status == null) status = TransactionStatus.EXECUTED;
        if (createdAt == null) createdAt = LocalDateTime.now();
    }
}