package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.baseclasses.BaseForm;
import ru.andersenlab.lesson.elements.Button;
import ru.andersenlab.lesson.elements.TextBox;


public class SignInPage extends BaseForm {
    private final TextBox passwordField = new TextBox(By.xpath("//input[@name='password']"));
    private final Button submitBtn = new Button(By.xpath("//button[@type='submit']"));
    private final TextBox emailField = new TextBox(By.xpath("//input[@name='email']"));

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

}
