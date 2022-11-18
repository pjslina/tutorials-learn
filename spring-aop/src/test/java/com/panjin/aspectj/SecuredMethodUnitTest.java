package com.panjin.aspectj;

import org.junit.jupiter.api.Test;

public class SecuredMethodUnitTest {
    @Test
    public void testMethod() throws Exception {
        SecuredMethod service = new SecuredMethod();
        service.unlockedMethod();
        service.lockedMethod();
    }
}