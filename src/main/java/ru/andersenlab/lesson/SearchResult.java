package ru.andersenlab.lesson;

import org.openqa.selenium.By;

import static ru.andersenlab.lesson.utilits.WaitUtils.*;

public class SearchResult {
    private static final By resultText = By.xpath("//h2");

    public String getResultText() {
        return waitForVisibility(resultText).getText();
    }
}
