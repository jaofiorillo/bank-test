package com.fiorillo.back_test.modules.account.service;

import com.fiorillo.back_test.auth.services.AuthenticationService;
import com.fiorillo.back_test.modules.account.dto.AccountDto;
import com.fiorillo.back_test.modules.account.dto.AccountResponse;
import com.fiorillo.back_test.modules.account.dto.TransactionalDto;
import com.fiorillo.back_test.modules.account.enums.EPayment;
import com.fiorillo.back_test.modules.account.model.Account;
import com.fiorillo.back_test.modules.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AuthenticationService authenticationService;

    public ResponseEntity<?> create(AccountDto dto) {
        try {
            validateExistingAccount(dto.numberAccount());
            var user = authenticationService.getAuthenticatedUser();
            var account = Account.of(dto, user);

            accountRepository.save(account);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AccountResponse.of(account));
        } catch (Exception ex) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", ex.getMessage()));
        }
    }

    private void validateExistingAccount(Integer numberAccount) {
        var existingAccount = accountRepository.findByNumberAccount(numberAccount);

        if (existingAccount.isPresent()) {
            throw new RuntimeException("Numero de conta já existente");
        }
    }

    public ResponseEntity<?> transactional(TransactionalDto dto) {
        try {
            var existingAccount = getAccountByNumberAccount(dto.numberAccount());
            calculateRate(dto, existingAccount);
            accountRepository.save(existingAccount);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AccountResponse.of(existingAccount));
        } catch (Exception ex) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", ex.getMessage()));
        }
    }

    private void calculateRate(TransactionalDto dto, Account account) {
        var value = dto.value();
        var paymentMethod = dto.paymentMethod();

        var valueWithRate = paymentMethod == EPayment.P
            ? value
            : value.add(value.multiply(BigDecimal.valueOf(paymentMethod.getRate())
            .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)));

        var newBalance = account.getBalance().subtract(valueWithRate);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        account.setBalance(newBalance.setScale(2, RoundingMode.HALF_UP));
    }

    public ResponseEntity<?> getAccountBynumberAccount(Integer numberAccount) {
        try {
            var user = authenticationService.getAuthenticatedUser();
            var account = getAccountByNumberAccount(numberAccount);

            if (!user.getNumberAccounts().contains(account.getNumberAccount())) {
                throw new RuntimeException("Usuário sem acesso a essa conta");
            }

            return ResponseEntity
                .status(HttpStatus.OK)
                .body(AccountResponse.of(account));
        } catch (Exception ex) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", ex.getMessage()));
        }
    }

    private Account getAccountByNumberAccount(Integer numberAccount) {
        return accountRepository.findByNumberAccount(numberAccount)
            .orElseThrow(() -> new EntityNotFoundException("Conta não encontrada"));
    }
}
