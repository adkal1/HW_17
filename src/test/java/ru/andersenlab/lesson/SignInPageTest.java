package ru.andersenlab.lesson;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignInPageTest extends BaseTest {

    @Epic("AQA Training")
    @Story("Sign-in page UI")
    @Description("Verifies title and header on the sign-in page")
    @Severity(SeverityLevel.MINOR)
    @TmsLink("TMS-004")
    @Test
    public void verifySignInPageTitleAndHeader() {
        Assert.assertEquals(signInPage.getTitlePage(), testDatas.title);
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
        signInPage.setPasswordField(" ");
        signInPage.setEmailField(config.password);
        Assert.assertTrue(signInPage.isPasswordLabelEightCharError());
    }

    @Epic("AQA Training")
    @Story("Email validation")
    @Description("Checks error message for invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-007")
    @Test
    public void checkInvalidEmailFormat() {
        signInPage.setEmailField(" ");
        signInPage.setPasswordField(config.password);
        Assert.assertTrue(signInPage.isEmailLabelError());
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
        Assert.assertTrue(signInPage.isEmailOrPasswordIsNotValid());
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
        Assert.assertTrue(signInPage.isEmailLabelError());
    }

}
