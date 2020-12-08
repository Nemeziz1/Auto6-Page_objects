package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $(byText("Ваши карты"));
    private SelenideElement verifyButtonAccount1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] [class='button__text']");
    private SelenideElement verifyButtonAccount2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] [class='button__text']");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }
    public int getSecondCardBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage2 validToDashBoard2Account1() {
        verifyButtonAccount1.click();
        return new DashboardPage2();
    }

    public DashboardPage2 validToDashBoard2Account2() {
        verifyButtonAccount2.click();
        return new DashboardPage2();
    }
}