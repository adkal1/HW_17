package ru.andersenlab.lesson.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import ru.andersenlab.lesson.baseclasses.BaseElement;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;

public class Button extends BaseElement {
    private Actions actions = new Actions(getDriver());

    public Button(By uniqueLocator) {
        super(uniqueLocator);
    }

    public void doubleClick() {
        actions.doubleClick(getElement()).build().perform();
    }

    public void contextClick() {
        actions.contextClick(getElement()).build().perform();
    }
}
