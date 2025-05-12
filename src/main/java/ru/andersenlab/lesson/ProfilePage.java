package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.baseclasses.BaseForm;
import ru.andersenlab.lesson.elements.Button;

public class ProfilePage extends BaseForm {
    private final Button aqaPracticeBtn = new Button(By.xpath("//div[text()='AQA Practice']"));

    public ProfilePage() {
        super(By.xpath("//div[contains(text(), 'Sign Out')]"));
    }

    public void clickAqaPracticeBtn() {
        aqaPracticeBtn.click();
    }

    public void clickDragDropBtn() {
        final Button dragDropBtn = new Button(By.xpath("//div[text()='Drag & Drop']"));
        dragDropBtn.click();
    }

    public void clickSelectBtn() {
        final Button selectBtn = new Button(By.xpath("//div[text()='Select']"));
        selectBtn.click();
    }

    public void clickAlertsAndIframesBtn() {
        final Button selectBtn = new Button(By.xpath("//div[text()='Actions, Alerts & Iframes']"));
        selectBtn.click();
    }

}
