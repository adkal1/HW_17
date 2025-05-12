package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.baseclasses.BaseForm;
import ru.andersenlab.lesson.elements.Button;
import ru.andersenlab.lesson.elements.Label;
import ru.andersenlab.lesson.elements.TextBox;


public class SignInPage extends BaseForm {
    private final TextBox passwordField = new TextBox(By.xpath("//input[@name='password']"));
    private final Button submitBtn = new Button(By.xpath("//button[@type='submit']"));
    private final TextBox emailField = new TextBox(By.xpath("//input[@name='email']"));
    private final Label emailErrorLabel = new Label(By.xpath("//input[@name='email']/../../div/span"));
    private final Label passwordErrorLabel = new Label(By.xpath("//input[@name='password']/../../div/span"));
    private final Button registerBtn = new Button(By.xpath("//a[@data-login-link='LoginPageLink']"));

    public SignInPage() {
        super(By.xpath("//h1[contains(text(), 'Sign In')]"));
    }

    public void setEmailField(String email) {
        emailField.clear();
        emailField.setText(email);
    }

    public void setPasswordField(String password) {
        passwordField.clear();
        passwordField.setText(password);
    }

    public void clickSubmitBtn() {
        submitBtn.click();
    }

    public boolean isPasswordFieldPresent() {
        return passwordField.isElementPresent();
    }

    public boolean isEmailFieldPresent() {
        return emailField.isElementPresent();
    }

    public String getEmailLabelError() {
        return emailErrorLabel.getText();
    }

    public String getPasswordLabelError() {
        return passwordErrorLabel.getText();
    }

    public void clickRegisterBtn() {
        registerBtn.click();
    }

}
