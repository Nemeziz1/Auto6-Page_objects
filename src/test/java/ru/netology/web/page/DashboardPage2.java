package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage2 {
    private SelenideElement heading = $(byText("Пополнение карты"));
    private SelenideElement codeFieldValue = $("[class='input__box'] [type='text']");
    private SelenideElement codeFieldFrom = $("[class='input__box'] [type='tel']");
    private SelenideElement codeButton = $("[data-test-id='action-transfer'] [class='button__text']");

    public DashboardPage2() {
        heading.shouldBe(visible);
    }

    public DashboardPage validVerify1(DataHelper.FirstTransferData firstTransferData) {
        codeFieldValue.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        codeFieldValue.sendKeys(Keys.BACK_SPACE);
        codeFieldValue.setValue(firstTransferData.getFirstTransferValue());
        codeFieldFrom.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        codeFieldFrom.sendKeys(Keys.DELETE);
        codeFieldFrom.setValue(firstTransferData.getSecondCardNumber());
        codeButton.click();
        return new DashboardPage();
    }

    public DashboardPage validVerify2(DataHelper.SecondTransferData secondTransferData) {
        codeFieldValue.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        codeFieldValue.sendKeys(Keys.BACK_SPACE);
        codeFieldValue.setValue(secondTransferData.getSecondTransferValue());
        codeFieldFrom.sendKeys(Keys.chord(Keys.CONTROL,"a"));
        codeFieldFrom.sendKeys(Keys.DELETE);
        codeFieldFrom.setValue(secondTransferData.getFirstCardNumber());
        codeButton.click();
        return new DashboardPage();
    }
}