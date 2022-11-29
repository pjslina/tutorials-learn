package com.panjin.aspectj;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountUnitTest {
    private Account account;

    @BeforeEach
    public void before() {
        account = new Account();
    }

    @Test
    public void givenBalance20AndMinBalance10_whenWithdraw5_thenSuccess() {
        assertTrue(account.withdraw(5));
    }

    @Test
    public void givenBalance20AndMinBalance10_whenWithdraw100_thenFail() {
        assertFalse(account.withdraw(100));
    }
}
