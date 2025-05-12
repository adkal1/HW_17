package ru.andersenlab.lesson;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.andersenlab.lesson.utilits.DateUtils.getDateInTwoWeeks;
import static ru.andersenlab.lesson.utilits.DateUtils.getNextMonday;

public class SelectPageTest extends BaseTest {
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
}
