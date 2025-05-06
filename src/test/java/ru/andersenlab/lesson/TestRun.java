package ru.andersenlab.lesson;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Test
    public void verifySignInPageTitleAndHeader() {
        Assert.assertEquals(getDriver().getTitle(), testDatas.title);
    }

    @Test
    public void checkEmailAndPasswordFieldsInSignInPage() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(signInPage.isEmailFieldPresent());
        softAssert.assertTrue(signInPage.isPasswordFieldPresent());
        softAssert.assertAll();
    }

    @Test
    public void checkPasswordLessThanEightCharacters() {
        Assert.assertEquals(signInPage.getPasswordLabelError(), testDatas.passwordLessEight);
    }

    @Test
    public void checkInvalidEmailFormat() {
        Assert.assertEquals(signInPage.getEmailLabelError(), testDatas.emailInvalid);
    }

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

    @Test
    public void loginSuccessfullyWithValidCredentials() {
        signInPage.setEmailField(config.email);
        signInPage.setPasswordField(config.password);
        signInPage.clickSubmitBtn();
        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.isFormOpen());
    }

    @Test
    public void failLoginWithIncorrectCredentials() {
        signInPage.setEmailField(config.email);
        signInPage.setPasswordField(config.password + "1");
        signInPage.clickSubmitBtn();
        ProfilePage profilePage = new ProfilePage();
        Assert.assertFalse(profilePage.isFormOpen());
    }

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
        quitDriver();
    }

}
