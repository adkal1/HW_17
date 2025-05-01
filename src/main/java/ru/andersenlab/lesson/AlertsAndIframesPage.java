package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;
import static ru.andersenlab.lesson.utilits.WaitUtils.*;

public class AlertsAndIframesPage {

    private static final WebElement iFrame = getDriver().findElement(By.xpath("//iframe[@title='Finish your registration']"));
    private Actions actions = new Actions(getDriver());

    public void clickConfirmBtn() {
        final By confirmBtn = By.xpath("//button[@id='AlertButton']");
        waitForClickable(confirmBtn).click();
    }

    public void clickGetDiscountBtn() {
        final By getDiscountBtn = By.xpath("//button[text()='Get Discount']");
        actions.doubleClick(waitForClickable(getDiscountBtn)).build().perform();
    }

    public void clickCancelCourseBtn() {
        final By cancelCourseBtn = By.xpath("//button[text()='Cancel course']");
        actions.contextClick(waitForClickable(cancelCourseBtn)).build().perform();
    }

    public String getResult() {
        final By confirmResult = By.xpath("//span[@class='font-light flex']");
        return waitForVisibility(confirmResult).getText();
    }

    public void switchToIframe() {
        getDriver().switchTo().frame(iFrame);
    }

    public void switchOutIframe() {
        getDriver().switchTo().defaultContent();

    }

}
