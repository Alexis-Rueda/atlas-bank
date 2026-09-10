package com.atlas.bank.atlas_bank.infrastructure.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Email {

    @Column(nullable = false)
    private String value;

    private Email(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        if (!value.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("El email no tiene un formato válido: " + value);
        }

        this.value = value.toLowerCase().trim();
    }

    public static Email of(String value) {
        return new Email(value);
    }

    @Override
    public String toString() {
        return value;
    }
}