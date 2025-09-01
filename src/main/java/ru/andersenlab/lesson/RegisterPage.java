package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.baseclasses.BaseForm;
import ru.andersenlab.lesson.elements.Button;
import ru.andersenlab.lesson.elements.Label;
import ru.andersenlab.lesson.elements.TextBox;

public class RegisterPage extends BaseForm {
    private final TextBox firstNameField = new TextBox(By.xpath("//input[@name='firstName']"));
    private final TextBox lastNameField = new TextBox(By.xpath("//input[@name='lastName']"));
    private final TextBox dateOfBirthField = new TextBox(By.xpath("//input[@name='dateOfBirth']"));
    private final TextBox emailField = new TextBox(By.xpath("//input[@name='email']"));
    private final TextBox passwordField = new TextBox(By.xpath("//input[@name='password']"));
    private final TextBox passwordConfirmationField = new TextBox(By.xpath("//input[@name='passwordConfirmation']"));
    private final Button submitBtn = new Button(By.xpath("//button[@type='submit']"));


    public RegisterPage() {
        super(By.xpath("//h1[contains(text(), 'Registration')]"));
    }

    public void setFirstNameField(String firstName) {
        firstNameField.setText(firstName);
    }

    public void setLastNameBoxField(String lastName) {
        lastNameField.setText(lastName);
    }

    public void setDateOfBirthField(String dateOfBirth) {
        dateOfBirthField.setText(dateOfBirth);
    }

    public void setEmailField(String email) {
        emailField.setText(email);
    }

    public void setPasswordField(String password) {
        passwordField.setText(password);
    }

    public void setPasswordConfirmationField(String password) {
        passwordConfirmationField.setText(password);
    }

    public void clickSubmitBtn() {
        submitBtn.moveToElementClick();
    }

    public String getErrorEmailField() {
        final Label errorEmailField = new Label(By.xpath("//input[@name='email']/../../div/span"));
        return errorEmailField.getText();
    }
}
