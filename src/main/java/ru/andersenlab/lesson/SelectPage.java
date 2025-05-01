package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;
import static ru.andersenlab.lesson.utilits.WaitUtils.*;

public class SelectPage {
    private static final By countryDropdown = By.xpath("//select[@title='Select country']");
    private static final By languageDropdown = By.xpath("//select[@title='Select language']");
    private static final By typeDropdown = By.xpath("//select[@title='Select type']");
    private static final By startCalendar = By.xpath("//input[@title='Start date']");
    private static final By endCalendar = By.xpath("//input[@title='End date']");
    private static final By selectCourses = By.xpath("//select[@id='MultipleSelect']");
    private static final By searchBtn = By.xpath("//button[@type='submit']");

    public void setCountryDropdown(String country) {
        Select select = new Select(getDriver().findElement(countryDropdown));
        select.selectByVisibleText(country);
    }

    public void setLanguageDropdown(String language) {
        Select select = new Select(getDriver().findElement(languageDropdown));
        select.selectByVisibleText(language);
    }

    public void setTypeDropdown(String type) {
        Select select = new Select(getDriver().findElement(typeDropdown));
        select.selectByVisibleText(type);
    }

    public void setStartCalendar(String startDate) {
        waitForVisibility(startCalendar).sendKeys(startDate);
    }

    public void setEndCalendar(String endDate) {
        waitForVisibility(endCalendar).sendKeys(endDate);
    }

    public void setSelectCourses(String course1, String course2) {
        Select select = new Select(waitForVisibility(selectCourses));
        select.selectByVisibleText(course1);
        select.selectByVisibleText(course2);
    }

    public void clickSearchBtn() {
        waitForClickable(searchBtn).click();
    }
}
