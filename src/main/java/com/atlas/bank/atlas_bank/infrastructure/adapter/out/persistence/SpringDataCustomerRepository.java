package com.atlas.bank.atlas_bank.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerRepository extends JpaRepository<CustomerJpaEntity, Long> {
}