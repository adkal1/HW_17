package ru.andersenlab.lesson.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import ru.andersenlab.lesson.baseclasses.BaseElement;
import ru.andersenlab.lesson.utilits.WaitUtils;

public class Dropdown extends BaseElement {
    public Dropdown(By uniqueLocator) {
        super(uniqueLocator);
    }

    public void setDropdown(String value) {
        WaitUtils.waitForElementToBeClickable(getLocator());
        Select select = new Select(getElement());
        select.selectByVisibleText(value);
    }
}