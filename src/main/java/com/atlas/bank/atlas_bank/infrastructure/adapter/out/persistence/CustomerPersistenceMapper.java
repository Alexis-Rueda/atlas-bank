package com.atlas.bank.atlas_bank.infrastructure.adapter.out.persistence;

import com.atlas.bank.atlas_bank.domain.model.customer.Customer;
import com.atlas.bank.atlas_bank.domain.model.shared.Email;
import org.springframework.stereotype.Component;

@Component
public class CustomerPersistenceMapper {

    public Customer toDomain(CustomerJpaEntity entity) {
        if (entity == null) return null;
        return Customer.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail() != null
                        ? Email.of(entity.getEmail().getValue())
                        : null)
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public CustomerJpaEntity toJpaEntity(Customer customer) {
        if (customer == null) return null;
        CustomerJpaEntity entity = new CustomerJpaEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail() != null
                ? com.atlas.bank.atlas_bank.infrastructure.adapter.out.persistence.Email.of(customer.getEmail().getValue())
                : null);
        entity.setStatus(customer.getStatus());
        entity.setCreatedAt(customer.getCreatedAt());
        return entity;
    }
}