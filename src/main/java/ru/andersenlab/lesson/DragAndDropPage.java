package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.baseclasses.BaseForm;
import ru.andersenlab.lesson.elements.Label;

import static ru.andersenlab.lesson.utilits.DragAndDrop.dragAndDrop;

public class DragAndDropPage extends BaseForm {
    private final Label manual1 = new Label(By.id("manual1"));
    private final Label manual2 = new Label(By.id("manual2"));
    private final Label auto1 = new Label(By.id("auto1"));
    private final Label auto2 = new Label(By.id("auto2"));
    private final Label targetManual1 = new Label(By.id("target-manual1"));
    private final Label targetManual2 = new Label(By.id("target-manual2"));
    private final Label targetAuto1 = new Label(By.id("target-auto1"));
    private final Label targetAuto2 = new Label(By.id("target-auto2"));

    public DragAndDropPage() {
        super(By.xpath("//button[@id='DragNDropPageFinishButton']"));
    }


    public void dragAndDropManual1() {
        dragAndDrop(manual1, targetManual1);
    }

    public void dragAndDropManual2() {
        dragAndDrop(manual2, targetManual2);
    }

    public void dragAndDropAuto1() {
        dragAndDrop(auto1, targetAuto1);
    }

    public void dragAndDropAuto2() {
        dragAndDrop(auto2, targetAuto2);
    }

    public String getResultLabel() {
        final Label resultLabel = new Label(By.xpath("//div[contains(@class, 'transition-opacity')]"));
        return resultLabel.getText();
    }

}
