package com.tiemfah.atm.controller;

import com.tiemfah.atm.model.Transaction;
import com.tiemfah.atm.service.BankAccountService;
import com.tiemfah.atm.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction")
public class TransactionController {
    private TransactionService transactionService;
    private BankAccountService accountService;

    public TransactionController(TransactionService transactionService, BankAccountService accountService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @PostMapping
    public String makeTransaction(@ModelAttribute Transaction transaction, Model model) {
        transactionService.makeTransaction(transaction);
        model.addAttribute("allBankAccount", accountService.getBankAccounts());
        return "redirect:bankaccount";
    }
}
