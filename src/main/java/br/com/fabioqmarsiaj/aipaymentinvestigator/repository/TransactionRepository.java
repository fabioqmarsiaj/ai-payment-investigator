package br.com.fabioqmarsiaj.aipaymentinvestigator.repository;

import br.com.fabioqmarsiaj.aipaymentinvestigator.domain.Transaction;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionRepository {
    private final List<Transaction> transactions = List.of(
            new Transaction(
                    "TX-001",
                    new BigDecimal("150.90"),
                    "DECLINED",
                    "05",
                    "AUTHORIZE-X",
                    "0200"
            ),
            new Transaction(
                    "TX-002",
                    new BigDecimal("49.90"),
                    "APPROVED",
                    "00",
                    "AUTHORIZE-X",
                    "0200"
            ),
            new Transaction(
                    "TX-003",
                    new BigDecimal("890.00"),
                    "DECLINED",
                    "51",
                    "AUTHORIZE-Y",
                    "0200"
            )
    );

    public Optional<Transaction> findById(String id) {
        return transactions.stream()
                .filter(transaction -> transaction.id().equals(id))
                .findFirst();
    }
}
