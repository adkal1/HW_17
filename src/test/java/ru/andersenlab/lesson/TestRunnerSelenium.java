package ru.andersenlab.lesson;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-report/cucumber.html",
                "json:target/cucumber-report/cucumber.json"},
        features = {"src/test/resources/feature"},
        glue = {"ru/andersenlab/lesson/selenium_step_definition"},
        tags = "@need_to_run"
)

public class TestRunnerSelenium extends AbstractTestNGCucumberTests {
}