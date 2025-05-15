package ru.andersenlab.lesson.selenium_step_definition;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import ru.andersenlab.lesson.*;

import java.io.File;
import java.io.IOException;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;
import static ru.andersenlab.lesson.utilits.Driver.quitDriver;

public class StepDefinitionSeleniumClass extends BaseTest {
    private static WebDriver driver;
    Config config;
    TestDatas testDatas;
    SignInPage signInPage;
    RegisterPage registerPage;

    @Given("Set up driver")
    public void set_up_driver() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        config = mapper.readValue(new File("src/test/resources/config.json"), Config.class);
        testDatas = mapper.readValue(new File("src/test/resources/testDatas.json"), TestDatas.class);
        driver = getDriver();
        driver.manage().window().maximize();
    }

    @When("opening Registration Page")
    public void opening_registration_page() {
        driver.get(config.url);
        signInPage = new SignInPage();
        signInPage.clickRegisterBtn();
        registerPage = new RegisterPage();
    }

    @And("set First Name")
    public void set_first_name() {
        registerPage.setFirstNameField(testDatas.firstName);
    }

    @And("set Last Name")
    public void set_last_name() {
        registerPage.setLastNameBoxField(testDatas.lastName);
    }

    @And("set Date of Birth")
    public void set_date_of_birth() {
        registerPage.setDateOfBirthField(testDatas.dateOfBirth);
    }

    @And("set email {string}")
    public void set_email(String email) {
        registerPage.setEmailField(email);
    }

    @When("set password")
    public void set_password() {
        registerPage.setPasswordField(testDatas.password);
    }

    @And("set confirmation password")
    public void set_confirmation_password() {
        registerPage.setPasswordConfirmationField(testDatas.password);
    }

    @And("click Submit button")
    public void click_submit_button() {
        registerPage.clickSubmitBtn();
    }

    @Then("check success registration")
    public void check_success_registration() {
        Assert.assertTrue(signInPage.isFormOpen());
    }

    @Then("check failure registration")
    public void check_failure_registration() {
        Assert.assertTrue(registerPage.isFormOpen());
    }

    @Then("check error email field")
    public void check_error_email_field() {
        Assert.assertEquals(registerPage.getErrorEmailField(), "Required");
    }

    @Then("quit driver")
    public void quit_driver() {
        quitDriver();
    }

}
