package ru.andersenlab.lesson.utilits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;

public class WaitUtils {

    public static boolean waitForElementPresence(By locator) {
        WebDriver driver = getDriver();
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator))
                .isDisplayed();
    }

    public static void waitForElementToBeClickable(By locator) {
        WebDriver driver = getDriver();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));

    }

}
