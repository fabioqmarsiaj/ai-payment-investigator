package br.com.fabioqmarsiaj.aipaymentinvestigator.domain;

import java.util.List;

public record TransactionInvestigation(
        String transactionId,
        String diagnosis,
        String explanation,
        String responseCode,
        List<String> possibleCauses
) {}
