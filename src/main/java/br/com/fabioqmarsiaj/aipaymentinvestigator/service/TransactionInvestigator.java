package br.com.fabioqmarsiaj.aipaymentinvestigator.service;

import br.com.fabioqmarsiaj.aipaymentinvestigator.domain.Transaction;
import br.com.fabioqmarsiaj.aipaymentinvestigator.domain.TransactionInvestigation;
import br.com.fabioqmarsiaj.aipaymentinvestigator.repository.TransactionRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class TransactionInvestigator {
    private final ChatClient chatClient;
    private final TransactionRepository repository;

    public TransactionInvestigator(ChatClient.Builder chatClient, TransactionRepository repository) {
        this.chatClient = chatClient.build();
        this.repository = repository;
    }

    public TransactionInvestigation investigate(String transactionId) {
        Transaction transaction = repository
                .findById(transactionId)
                .orElseThrow();

        return chatClient
                .prompt()
                .system(
                        """
                                You are an AI assistant specialized in payment systems.
                                
                                Analyze payment transactions and explain possible reasons
                                for failures based only on the information provided.
                                
                                Do not invent transaction data.
                                If the available information is insufficient, say so.
                                """)
                .user("""
                        Investigate the following payment transaction:
                        
                        Transaction ID: %s
                        Amount: %s
                        Status: %s
                        Response Code: %s
                        Authorizer: %s
                        Message Type: %s
                        """.formatted(
                        transaction.id(),
                        transaction.amount(),
                        transaction.status(),
                        transaction.responseCode(),
                        transaction.authorizer(),
                        transaction.messageType()
                ))
                .call()
                .entity(
                        TransactionInvestigation.class,
                        ChatClient.EntityParamSpec::validateSchema
                );
    }
}
