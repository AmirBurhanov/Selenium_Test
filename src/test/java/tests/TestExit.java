package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestExit extends BaseTest {

    @Test
    public void exit() throws Exception {
        try {
            dashboardPage
                    .clickUserButton()
                    .clickExit();

            Assert.assertTrue(youTrackLogin.isLoginPageDisplayed(),"Заголовок страницы логина пустой");
            captureScreenshot("Test exit", "Пользователь находится на странице авторизации");
        } catch (Exception e) {
            handleTestException("exit", e);
        }
    }
}