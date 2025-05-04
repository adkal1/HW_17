package ru.andersenlab.lesson.elements;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.baseclasses.BaseElement;

public class TextBox extends BaseElement {

    public TextBox(By uniqueLocator) {
        super(uniqueLocator);
    }

    public void setText(String value) {
        getElement().sendKeys(value);
    }

    public void clear() {
        getElement().clear();
    }
}
