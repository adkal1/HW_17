package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.baseclasses.BaseForm;
import ru.andersenlab.lesson.elements.Label;

public class SearchResult extends BaseForm {
    private final Label resultText = new Label(By.xpath("//h2"));

    public SearchResult() {
        super(By.xpath("//h1[contains(text(), 'Search results')]"));
    }

    public String getResultText() {
        return resultText.getText();
    }
}
