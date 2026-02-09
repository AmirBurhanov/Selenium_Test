package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExit extends BaseTest {

    @Test
    public void exit() {
        try {

            wait.waitSeconds(driver, 1);
            captureScreenshot("Test exit", "click user");

            dashboardPage.clickUserButton();
            captureScreenshot("TestExit", "list appearance ");


            wait.waitSeconds(driver, 1);
            dashboardPage.clickExit();


            Assert.assertTrue(youTrackLogin.isLoginPageDisplayed(),"Заголовок страницы логина пустой");
            captureScreenshot("Test exit", "Пользователь находится на странице авторизации");
            wait.waitSeconds(driver, 1);

        } catch (Exception e) {
            captureScreenshot("TestExit", "ERROR");
            Assert.fail("Ошибка в тесте выхода: " + e.getMessage());
        }
    }
}