package ru.andersenlab.lesson.baseclasses;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.utilits.WaitUtils;


public abstract class BaseForm {
    private final By uniqueFormLocator;

    public BaseForm(By uniqueFormLocator) {
        this.uniqueFormLocator = uniqueFormLocator;
    }

    public boolean isFormOpen() {
        return WaitUtils.waitForElementPresence(uniqueFormLocator);
    }

}
