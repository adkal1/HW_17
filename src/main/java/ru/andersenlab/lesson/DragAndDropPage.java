package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;
import static ru.andersenlab.lesson.utilits.WaitUtils.*;

public class DragAndDropPage {
    Actions actions = new Actions(getDriver());
    private static final WebElement manual1 = getDriver().findElement(By.id("manual1"));
    private static final WebElement manual2 = getDriver().findElement(By.id("manual2"));
    private static final WebElement auto1 = getDriver().findElement(By.id("auto1"));
    private static final WebElement auto2 = getDriver().findElement(By.id("auto2"));
    private static final WebElement targetManual1 = getDriver().findElement(By.id("target-manual1"));
    private static final WebElement targetManual2 = getDriver().findElement(By.id("target-manual2"));
    private static final WebElement targetAuto1 = getDriver().findElement(By.id("target-auto1"));
    private static final WebElement targetAuto2 = getDriver().findElement(By.id("target-auto2"));


    public void dragAndDropManual1() {
        actions.dragAndDrop(manual1, targetManual1).build().perform();
    }

    public void dragAndDropManual2() {
        actions.dragAndDrop(manual2, targetManual2).build().perform();
    }

    public void dragAndDropAuto1() {
        actions.dragAndDrop(auto1, targetAuto1).build().perform();
    }

    public void dragAndDropAuto2() {
        actions.dragAndDrop(auto2, targetAuto2).build().perform();
    }

    public String getResultLabel() {
        final By resultLabel = By.xpath("//div[contains(@class, 'transition-opacity')]");
        return waitForVisibility(resultLabel).getText();
    }

}
