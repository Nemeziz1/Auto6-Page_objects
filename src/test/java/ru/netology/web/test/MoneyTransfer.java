package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPage;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransfer {
    @Test
    @Order(1)
    void shouldTransferMoneyFrom2To1() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 6000;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getBalanceAfterGet(firstCardBalance, value);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterTransfer(secondCardBalance, value);
        val transferInfo = DataHelper.getSecondTransferData();
        val transferPage = dashboardPage.validToTransferAccount1();
        transferPage.validVerify1(transferInfo, value);
        val actualCurrentFirstCard = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(firstCardBalanceAfter, actualCurrentFirstCard);
        val actualCurrentSecondCard = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalanceAfter, actualCurrentSecondCard);
    }

    @Test
    @Order(2)
    void shouldTransferMoneyFrom1To2() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 6000;
        val firstCardBalance = dashboardPage.getFirstCardBalance();
        val secondCardBalance = dashboardPage.getSecondCardBalance();
        val firstCardBalanceAfter = DataHelper.getBalanceAfterTransfer(firstCardBalance, value);
        val secondCardBalanceAfter = DataHelper.getBalanceAfterGet(secondCardBalance, value);
        val transferInfo = DataHelper.getFirstTransferData();
        val transferPage = dashboardPage.validToTransferAccount2();
        transferPage.validVerify1(transferInfo, value);
        val actualCurrentFirstCard = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(firstCardBalanceAfter, actualCurrentFirstCard);
        val actualCurrentSecondCard = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(secondCardBalanceAfter, actualCurrentSecondCard);
    }

    @Test
    @Order(3)
    void shouldNoTransfer() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage =  verificationPage.validVerify(verificationCode);
        val value = 11000;
        val transferInfo = DataHelper.getSecondTransferData();
        val transferPage = dashboardPage.validToTransferAccount1();
        transferPage.validVerify1(transferInfo, value);
        transferPage.noTransfer();
    }
}
