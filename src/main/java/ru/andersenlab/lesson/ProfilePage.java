package ru.andersenlab.lesson;

import org.openqa.selenium.By;

import static ru.andersenlab.lesson.utilits.WaitUtils.*;

public class ProfilePage {
    private static final By aqaPracticeBtn = By.xpath("//div[text()='AQA Practice']");


    public void clickAqaPracticeBtn() {
        waitForClickable(aqaPracticeBtn).click();
    }

    public void clickDragDropBtn() {
        final By dragDropBtn = By.xpath("//div[text()='Drag & Drop']");
        waitForClickable(dragDropBtn).click();
    }

    public void clickSelectBtn() {
        final By selectBtn = By.xpath("//div[text()='Select']");
        waitForClickable(selectBtn).click();
    }

    public void clickAlertsAndIframesBtn() {
        final By selectBtn = By.xpath("//div[text()='Actions, Alerts & Iframes']");
        waitForClickable(selectBtn).click();
    }

}
