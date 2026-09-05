package com.atlas.bank.atlas_bank.domain.model.transaction;

import com.atlas.bank.atlas_bank.domain.model.transaction.state.*;
import com.atlas.bank.atlas_bank.domain.event.TransactionExecutedEvent;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Transaction extends AbstractAggregateRoot<Transaction> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType type;

    @Column(name = "source_account_id")
    private Long sourceAccountId;

    @Column(name = "target_account_id")
    private Long targetAccountId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal fee;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Transient
    private TransactionState state;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) this.status = TransactionStatus.EXECUTED;
    }

    public TransactionState getState() {
        if(state==null){
            state = switch (status){
                case PENDING -> new PendingState();
                case VALIDATED -> new ValidatedState();
                case EXECUTED -> new ExecutedState();
                case REJECTED -> new RejectedState();
                case REVERSED -> new ReversedState();
            };
        }
        return state;
    }

    public void advanceTo(TransactionState newState){
        state = newState;
        status = newState.status();
    }

    public void markAsExecuted(){
        registerEvent(new TransactionExecutedEvent(
                id,
                type.name(),
                sourceAccountId,
                targetAccountId,
                amount,
                fee
        ));
    }

    public void executeTransfer(){
        advanceTo(getState().validate());
        advanceTo(getState().execute());
        markAsExecuted();
    }
}