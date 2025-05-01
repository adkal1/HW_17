package ru.andersenlab.lesson;

import org.openqa.selenium.By;

import static ru.andersenlab.lesson.utilits.WaitUtils.*;


public class SignInPage {
    private static final By emailField = By.xpath("//input[@name='email']");
    private static final By passwordField = By.xpath("//input[@name='password']");
    private static final By submitBtn = By.xpath("//button[@type='submit']");

    public void setEmailField(String email) {
        waitForVisibility(emailField).sendKeys(email);
    }

    public void setPasswordField(String password) {
        waitForVisibility(passwordField).sendKeys(password);
    }

    public void clickSubmitBtn() {
        waitForClickable(submitBtn).click();
    }

}
