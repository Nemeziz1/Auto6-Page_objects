package ru.netology.web.test;

import lombok.val;
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
        dashboardPage.validToDashBoard2Account1();
        val dashboardPage2 = new DashboardPage2();
        val firstTransferData = DataHelper.getFirstTransferData();
        dashboardPage2.validVerify1(firstTransferData);
        dashboardPage.validToDashBoard2Account2();
        val secondTransferData = DataHelper.getSecondTransferData();
        dashboardPage2.validVerify2(secondTransferData);
    }
}
