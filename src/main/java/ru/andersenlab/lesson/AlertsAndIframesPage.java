package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.andersenlab.lesson.baseclasses.BaseForm;
import ru.andersenlab.lesson.elements.Button;
import ru.andersenlab.lesson.elements.Label;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;

public class AlertsAndIframesPage extends BaseForm {

    private static final WebElement I_FRAME = getDriver().findElement(By.xpath("//iframe[@title='Finish your registration']"));

    public AlertsAndIframesPage() {
        super(By.xpath("//iframe[@title='Finish your registration']"));
    }

    public void clickConfirmBtn() {
        final Button confirmBtn = new Button(By.xpath("//button[@id='AlertButton']"));
        confirmBtn.click();
    }

    public void clickGetDiscountBtn() {
        final Button getDiscountBtn = new Button(By.xpath("//button[text()='Get Discount']"));
        getDiscountBtn.doubleClick();
    }

    public void clickCancelCourseBtn() {
        final Button cancelCourseBtn = new Button(By.xpath("//button[text()='Cancel course']"));
        cancelCourseBtn.contextClick();
    }

    public String getResult() {
        final Label confirmResult = new Label(By.xpath("//span[@class='font-light flex']"));
        return confirmResult.getText();
    }

    public void switchToIframe() {
        getDriver().switchTo().frame(I_FRAME);
    }

    public void switchOutIframe() {
        getDriver().switchTo().defaultContent();
    }

}
