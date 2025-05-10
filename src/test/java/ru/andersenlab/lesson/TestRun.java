package ru.andersenlab.lesson;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;
import static ru.andersenlab.lesson.utilits.DateUtils.getDateInTwoWeeks;
import static ru.andersenlab.lesson.utilits.DateUtils.getNextMonday;
import static ru.andersenlab.lesson.utilits.Driver.quitDriver;


public class TestRun {
    private Config config;
    private TestDatas testDatas;
    private static SignInPage signInPage = new SignInPage();


    @BeforeMethod
    public void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        config = mapper.readValue(new File("src/test/resources/config.json"), Config.class);
        testDatas = mapper.readValue(new File("src/test/resources/testDatas.json"), TestDatas.class);
        getDriver().manage().window().maximize();
        getDriver().get(config.url);
        getDriver().manage().timeouts().pageLoadTimeout(1000, TimeUnit.MILLISECONDS);
        getDriver().manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
    }

    @Epic("AndersenLab")
    @Story("Course selection functionality")
    @Description("Verifies the correct operation of the course selection page using dropdowns and calendars")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TMS-001")
    @Test
    public void testSelectPage() {
        signInPage.setEmailField(config.email);
        signInPage.setPasswordField(config.password);
        signInPage.clickSubmitBtn();

        ProfilePage profilePage = new ProfilePage();
        profilePage.clickAqaPracticeBtn();
        profilePage.clickSelectBtn();

        SelectPage selectPage = new SelectPage();
        selectPage.setCountryDropdown(testDatas.country);
        selectPage.setLanguageDropdown(testDatas.language);
        selectPage.setTypeDropdown(testDatas.type);
        selectPage.setStartCalendar(getNextMonday());
        selectPage.setEndCalendar(getDateInTwoWeeks());
        selectPage.setSelectCourses(testDatas.course1, testDatas.course2);
        selectPage.clickSearchBtn();

        SearchResult searchResult = new SearchResult();
        Assert.assertTrue(searchResult.isFormOpen());
        Assert.assertEquals(searchResult.getResultText(), testDatas.searchResult);
    }
    @Epic("AndersenLab")
    @Story("Drag-and-Drop functionality")
    @Description("Checks drag-and-drop functionality both manual and automatic")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-002")
    @Test
    public void testDragAndDropPage() {
        signInPage.setEmailField(config.email);
        signInPage.setPasswordField(config.password);
        signInPage.clickSubmitBtn();

        ProfilePage profilePage = new ProfilePage();
        profilePage.clickAqaPracticeBtn();
        profilePage.clickDragDropBtn();

        DragAndDropPage dragAndDropPage = new DragAndDropPage();
        dragAndDropPage.dragAndDropManual1();
        dragAndDropPage.dragAndDropManual2();
        dragAndDropPage.dragAndDropAuto1();
        dragAndDropPage.dragAndDropAuto2();
        Assert.assertEquals(dragAndDropPage.getResultLabel(), testDatas.dragAndDropResult);
    }

    @Epic("AQA Training")
    @Story("Alerts and Iframes interaction")
    @Description("Validates handling of alert pop-ups within an iframe: confirmation, discount request, course cancellation")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TMS-003")
    @Test
    public void testAlertsAndIframesPage() {
        SoftAssert softAssert = new SoftAssert();
        signInPage.setEmailField(config.email);
        signInPage.setPasswordField(config.password);
        signInPage.clickSubmitBtn();

        ProfilePage profilePage = new ProfilePage();
        profilePage.clickAqaPracticeBtn();
        profilePage.clickAlertsAndIframesBtn();

        AlertsAndIframesPage alertsAndIframesPage = new AlertsAndIframesPage();
        alertsAndIframesPage.switchToIframe();
        alertsAndIframesPage.clickConfirmBtn();

        Alert alert = getDriver().switchTo().alert();
        softAssert.assertEquals(alert.getText(), testDatas.alertConfirm);
        alert.accept();

        softAssert.assertEquals(alertsAndIframesPage.getResult(), testDatas.resultConfirm);

        alertsAndIframesPage.clickGetDiscountBtn();

        softAssert.assertEquals(alert.getText(), testDatas.alertGetDiscount);
        alert.accept();
        softAssert.assertEquals(alertsAndIframesPage.getResult(), testDatas.resultGetDiscount);

        alertsAndIframesPage.clickCancelCourseBtn();

        softAssert.assertEquals(alert.getText(), testDatas.alertCancelCourse);
        alert.sendKeys(testDatas.textToAlertCancelCourse);
        alert.accept();
        softAssert.assertEquals(alertsAndIframesPage.getResult(), testDatas.resultCancelCourse + testDatas.textToAlertCancelCourse);
        alertsAndIframesPage.switchOutIframe();

        softAssert.assertAll();
    }

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

    @AfterMethod
    public void tearDown() {
        getDriver().quit();
    }

}
