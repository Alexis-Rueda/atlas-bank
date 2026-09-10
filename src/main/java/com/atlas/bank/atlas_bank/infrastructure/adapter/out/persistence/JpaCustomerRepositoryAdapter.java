package com.atlas.bank.atlas_bank.infrastructure.adapter.out.persistence;

import com.atlas.bank.atlas_bank.application.port.out.CustomerRepositoryPort;
import com.atlas.bank.atlas_bank.domain.model.customer.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaCustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final SpringDataCustomerRepository jpaRepository;
    private final CustomerPersistenceMapper mapper;

    @Override
    public Optional<Customer> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        customer.initDefaults();
        CustomerJpaEntity entity = mapper.toJpaEntity(customer);
        CustomerJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}