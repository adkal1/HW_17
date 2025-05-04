package ru.andersenlab.lesson.elements;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.baseclasses.BaseElement;

public class Label extends BaseElement {
    public Label(By uniqueLocator) {
        super(uniqueLocator);
    }

    public String getText() {
        if (isElementPresent()) {
            return getElement().getText();
        }
        return "";
    }


}
