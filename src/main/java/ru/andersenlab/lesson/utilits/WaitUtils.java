package ru.andersenlab.lesson.utilits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;

public class WaitUtils {
    static WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    public static WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
