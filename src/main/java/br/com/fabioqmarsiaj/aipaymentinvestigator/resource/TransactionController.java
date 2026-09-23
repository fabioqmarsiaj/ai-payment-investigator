package br.com.fabioqmarsiaj.aipaymentinvestigator.resource;

import br.com.fabioqmarsiaj.aipaymentinvestigator.domain.TransactionInvestigation;
import br.com.fabioqmarsiaj.aipaymentinvestigator.service.TransactionInvestigator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionInvestigator investigator;

    public TransactionController(TransactionInvestigator investigator) {
        this.investigator = investigator;
    }

    @GetMapping("/{id}/investigate")
    public TransactionInvestigation investigate(@PathVariable String id) {
        return investigator.investigate(id);
    }
}
