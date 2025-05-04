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
        SignInPage signInPage = new SignInPage();
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
        SignInPage signInPage = new SignInPage();
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
        SignInPage signInPage = new SignInPage();
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

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }

}
