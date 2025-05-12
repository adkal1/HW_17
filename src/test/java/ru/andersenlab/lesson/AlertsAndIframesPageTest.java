package ru.andersenlab.lesson;

import io.qameta.allure.*;
import org.openqa.selenium.Alert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;

public class AlertsAndIframesPageTest extends BaseTest {
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
}
