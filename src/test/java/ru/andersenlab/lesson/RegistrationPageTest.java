package ru.andersenlab.lesson;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseTest {
    @Epic("AQA Training")
    @Story("User registration")
    @Description("Successfully registers a user with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TMS-008")
    @Test
    public void registerSuccessfullyWithValidData() {
        signInPage.clickRegisterBtn();

        RegisterPage registerPage = new RegisterPage();
        registerPage.setFirstNameField(testDatas.firstName);
        registerPage.setLastNameBoxField(testDatas.lastName);
        registerPage.setDateOfBirthField(testDatas.dateOfBirth);
        registerPage.setEmailField(testDatas.email);
        registerPage.setPasswordField(testDatas.password);
        registerPage.setPasswordConfirmationField(testDatas.password);
        registerPage.clickSubmitBtn();
        Assert.assertTrue(signInPage.isFormOpen());
    }

    @Epic("AQA Training")
    @Story("User registration")
    @Description("Attempts to register with an already registered email")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-009")
    @Test
    public void registerWithAlreadyRegisteredEmail() {
        signInPage.clickRegisterBtn();

        RegisterPage registerPage = new RegisterPage();
        registerPage.setFirstNameField(testDatas.firstName);
        registerPage.setLastNameBoxField(testDatas.lastName);
        registerPage.setDateOfBirthField(testDatas.dateOfBirth);
        registerPage.setEmailField(testDatas.email);
        registerPage.setPasswordField(testDatas.password);
        registerPage.setPasswordConfirmationField(testDatas.password);
        registerPage.clickSubmitBtn();
        Assert.assertFalse(signInPage.isFormOpen());
    }

    @Epic("AQA Training")
    @Story("User registration")
    @Description("Validates required input fields on registration page")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-010")
    @Test
    public void validateRegisterPageInputFields() {
        signInPage.clickRegisterBtn();

        RegisterPage registerPage = new RegisterPage();
        registerPage.setFirstNameField(testDatas.firstName);
        registerPage.setLastNameBoxField(testDatas.lastName);
        registerPage.setDateOfBirthField(testDatas.dateOfBirth);
        registerPage.setPasswordField(testDatas.password);
        registerPage.setPasswordConfirmationField(testDatas.password);
        registerPage.clickSubmitBtn();
        Assert.assertEquals(registerPage.getErrorEmailField(), "Required");
    }
}
