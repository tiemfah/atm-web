package com.tiemfah.atm.controller;

import com.tiemfah.atm.model.Customer;
import com.tiemfah.atm.service.BankAccountService;
import com.tiemfah.atm.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    private CustomerService customerService;
    private BankAccountService bankAccountService;

    public LoginController(CustomerService customerService, BankAccountService bankAccountService) {
        this.customerService = customerService;
        this.bankAccountService = bankAccountService;
    }

    @PostMapping
    public String login(@ModelAttribute Customer customer, Model model) {
        Customer storedCustomer = customerService.checkPin(customer);

        if (storedCustomer != null) {
            model.addAttribute("customertitle",
                    storedCustomer.getName() + " Bank Accounts");
            model.addAttribute("bankaccounts",
                    bankAccountService.getCustomerBankAccount(customer.getId()));
            return "customeraccount";
        } else {
            model.addAttribute("greeting", "Can't find customer");
            return "home";
        }
    }


    @GetMapping
    public String getLoginPage() {
        return "login";   // return login.html
    }
}
