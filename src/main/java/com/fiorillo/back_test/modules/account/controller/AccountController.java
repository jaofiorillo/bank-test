package com.fiorillo.back_test.modules.account.controller;

import com.fiorillo.back_test.modules.account.dto.AccountDto;
import com.fiorillo.back_test.modules.account.dto.TransactionalDto;
import com.fiorillo.back_test.modules.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AccountDto accountDto) {
        return accountService.create(accountDto);
    }

    @PostMapping("/transactional")
    public ResponseEntity<?> transactional(@Valid @RequestBody TransactionalDto transactionalDto) {
        return accountService.transactional(transactionalDto);
    }

    @GetMapping("/{numberAccount}")
    public ResponseEntity<?> getAccountBynumberAccount(@PathVariable Integer numberAccount) {
        return accountService.getAccountBynumberAccount(numberAccount);
    }
}
