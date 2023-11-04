package net.youssfi.accountservice.controllers;

import net.youssfi.accountservice.clients.CustomerRestClient;
import net.youssfi.accountservice.entities.BankAccount;
import net.youssfi.accountservice.entities.Customer;
import net.youssfi.accountservice.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private AccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(AccountRepository accountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity getBankAccountById(@PathVariable String accountId){
        BankAccount bankAccount = accountRepository.findById(accountId).orElse(null);
        if(bankAccount==null) {
            return ResponseEntity.internalServerError().body(Map.of("errorMessage","account not found"));
        }
        Customer customer=customerRestClient.getCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return ResponseEntity.ok(bankAccount);
    }
    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        return accountRepository.findAll();
    }
}
