package ru.andersenlab.lesson.baseclasses;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.utilits.WaitUtils;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;


public abstract class BaseForm {
    private final By uniqueFormLocator;

    public BaseForm(By uniqueFormLocator) {
        this.uniqueFormLocator = uniqueFormLocator;
    }

    public boolean isFormOpen() {
        return WaitUtils.waitForElementPresence(uniqueFormLocator);
    }

    public String getTitlePage() {
        return getDriver().getTitle();
    }

}
