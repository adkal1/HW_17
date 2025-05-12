package ru.andersenlab.lesson;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;

public class SignInPageTest extends BaseTest {

    @Epic("AQA Training")
    @Story("Sign-in page UI")
    @Description("Verifies title and header on the sign-in page")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("TMS-004")
    @Test
    public void verifySignInPageTitleAndHeader() {
        Assert.assertEquals(getDriver().getTitle(), testDatas.title);
    }

    @Epic("AQA Training")
    @Story("Sign-in form validation")
    @Description("Checks presence of email and password fields on the login page")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-005")
    @Test
    public void checkEmailAndPasswordFieldsInSignInPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(signInPage.isEmailFieldPresent());
        softAssert.assertTrue(signInPage.isPasswordFieldPresent());
        softAssert.assertAll();
    }

    @Epic("AQA Training")
    @Story("Password validation")
    @Description("Checks error when password is shorter than 8 characters")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-006")
    @Test
    public void checkPasswordLessThanEightCharacters() {
        Assert.assertEquals(signInPage.getPasswordLabelError(), testDatas.passwordLessEight);
    }

    @Epic("AQA Training")
    @Story("Email validation")
    @Description("Checks error message for invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-007")
    @Test
    public void checkInvalidEmailFormat() {
        Assert.assertEquals(signInPage.getEmailLabelError(), testDatas.emailInvalid);
    }

    @Epic("AQA Training")
    @Story("User login")
    @Description("Successfully logs in with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TMS-011")
    @Test
    public void loginSuccessfullyWithValidCredentials() {
        signInPage.setEmailField(config.email);
        signInPage.setPasswordField(config.password);
        signInPage.clickSubmitBtn();
        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.isFormOpen());
    }

    @Epic("AQA Training")
    @Story("User login")
    @Description("Fails login with incorrect credentials")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-012")
    @Test
    public void failLoginWithIncorrectCredentials() {
        signInPage.setEmailField(config.email);
        signInPage.setPasswordField(config.password + "1");
        signInPage.clickSubmitBtn();
        ProfilePage profilePage = new ProfilePage();
        Assert.assertFalse(profilePage.isFormOpen());
    }

    @Epic("AQA Training")
    @Story("User login")
    @Description("Validates that login fields cannot be empty")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-013")
    @Test
    public void validateLoginPageInputFields() {
        signInPage.setEmailField(" ");
        signInPage.setPasswordField(" ");
        signInPage.clickSubmitBtn();
        ProfilePage profilePage = new ProfilePage();
        Assert.assertFalse(profilePage.isFormOpen());
    }

}
