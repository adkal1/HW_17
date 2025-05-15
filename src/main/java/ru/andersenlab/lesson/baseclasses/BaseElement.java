package ru.andersenlab.lesson.baseclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;

import ru.andersenlab.lesson.utilits.WaitUtils;

public abstract class BaseElement {
    private final By uniqueLocator;

    public BaseElement(By uniqueLocator) {
        this.uniqueLocator = uniqueLocator;
    }

    public WebElement getElement() {
        return getDriver().findElement(uniqueLocator);
    }

    public void click() {
        WaitUtils.waitForElementToBeClickable(uniqueLocator);
        getElement().click();
    }

    public boolean isElementPresent() {
        return WaitUtils.waitForElementPresence(uniqueLocator);
    }

    public By getLocator() {
        return uniqueLocator;
    }
}
