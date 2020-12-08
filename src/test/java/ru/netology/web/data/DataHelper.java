package ru.netology.web.data;

import lombok.Value;
import ru.netology.web.page.DashboardPage;

import java.util.Arrays;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class FirstTransferData {
        private String secondCardNumber;
        private String firstTransferValue;
    }

    public static FirstTransferData getFirstTransferData() {
        int maxValue = 10001;
        Random random = new Random();
        int transferValue = random.nextInt(maxValue);
        return new FirstTransferData("5559000000000002", "5000");
    }

    @Value
    public static class SecondTransferData {
        private String firstCardNumber;
        private String secondTransferValue;
    }

    public static SecondTransferData getSecondTransferData() {
        int maxValue = 10001;
        Random random = new Random();
        int transferValue = random.nextInt(maxValue);
        return new SecondTransferData("5559000000000001", "5000");
    }
}

