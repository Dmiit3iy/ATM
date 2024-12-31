package model;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;

class ATMTest {
    @Mock
    private StubSdk stubSdk;
    @InjectMocks
    private ATM atm;


    @BeforeEach
    public void setUp(TestInfo testInfo) {
        MockitoAnnotations.openMocks(this);
        if (!testInfo.getDisplayName().contains("NoSetup")) {
            configureStubSdk();
        }
    }

    private void configureStubSdk() {
        Mockito.when(stubSdk.countBanknotes(50000)).thenReturn(10);
        Mockito.when(stubSdk.countBanknotes(1000)).thenReturn(4);
        Mockito.when(stubSdk.countBanknotes(500)).thenReturn(1);
        Mockito.when(stubSdk.countBanknotes(100)).thenReturn(6);
        Mockito.when(stubSdk.countBanknotes(50)).thenReturn(10);
    }

    @Test
    void giveMyMoneyBShouldReturnTrue() {
        Assertions.assertTrue(atm.giveMyMoneyB(500));
    }

    @Test
    void giveMyMoneyBShouldReturnFalse() {
        Assertions.assertFalse(atm.giveMyMoneyB(530));
    }

    @Test
    @DisplayName("NoSetup")
    void giveMyMoneyBShouldReturnFalseWhenNoBanknotesAvailable() {
        Mockito.when(stubSdk.countBanknotes(anyInt())).thenReturn(0);
        Assertions.assertFalse(atm.giveMyMoneyB(500));
    }

}