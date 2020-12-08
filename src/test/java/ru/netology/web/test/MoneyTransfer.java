package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.DashboardPage2;
import ru.netology.web.page.LoginPage;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransfer {
    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val dashboardPage = new DashboardPage();
        Integer expectedFirstCard = dashboardPage.getFirstCardBalance();
        Integer expectedSecondCard = dashboardPage.getSecondCardBalance();
        dashboardPage.validToDashBoard2Account1();
        val dashboardPage2 = new DashboardPage2();
        val firstTransferData = DataHelper.getFirstTransferData();
        dashboardPage2.validVerify1(firstTransferData);
        Integer expectedCurrentFirstCard = dashboardPage.getFirstCardBalance();
        Assertions.assertNotEquals(expectedFirstCard, expectedCurrentFirstCard);
        Integer expectedCurrentSecondCard = dashboardPage.getSecondCardBalance();
        Assertions.assertNotEquals(expectedSecondCard, expectedCurrentSecondCard);
        dashboardPage.validToDashBoard2Account2();
        val secondTransferData = DataHelper.getSecondTransferData();
        dashboardPage2.validVerify2(secondTransferData);
        Integer expectedCurrentFirstCard2 = dashboardPage.getFirstCardBalance();
        Assertions.assertEquals(expectedFirstCard, expectedCurrentFirstCard2);
        Integer expectedCurrentSecondCard2 = dashboardPage.getSecondCardBalance();
        Assertions.assertEquals(expectedSecondCard, expectedCurrentSecondCard2);
    }
}
