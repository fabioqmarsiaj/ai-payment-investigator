package br.com.fabioqmarsiaj.aipaymentinvestigator.domain;

import java.math.BigDecimal;

public record Transaction(
        String id,
        BigDecimal amount,
        String status,
        String responseCode,
        String authorizer,
        String messageType
) {}
