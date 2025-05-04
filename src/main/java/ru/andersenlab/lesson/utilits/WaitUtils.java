package ru.andersenlab.lesson.utilits;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;

public class WaitUtils {
    static WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    public static boolean waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
    }

    public static void waitForElementToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

}
