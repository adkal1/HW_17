package ru.andersenlab.lesson.utilits;

import org.openqa.selenium.interactions.Actions;
import ru.andersenlab.lesson.elements.Label;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;

public class DragAndDrop {
    static Actions actions = new Actions(getDriver());

    public static void dragAndDrop(Label element, Label targetElement) {
        actions.dragAndDrop(element.getElement(), targetElement.getElement()).build().perform();
    }
}
