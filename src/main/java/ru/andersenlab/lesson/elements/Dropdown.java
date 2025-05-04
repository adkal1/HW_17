package ru.andersenlab.lesson.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import ru.andersenlab.lesson.baseclasses.BaseElement;

public class Dropdown extends BaseElement {
    public Dropdown(By uniqueLocator) {
        super(uniqueLocator);
    }

    public void setDropdown(String value) {
        Select select = new Select(getElement());
        select.selectByVisibleText(value);
    }
}
