package ru.andersenlab.lesson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

import static ru.andersenlab.lesson.utilits.Driver.getDriver;
import static ru.andersenlab.lesson.utilits.Driver.quitDriver;

public abstract class BaseTest {
    Config config;
    TestDatas testDatas;
    SignInPage signInPage;

    @BeforeMethod
    public void setUp() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        config = mapper.readValue(new File("src/test/resources/config.json"), Config.class);
        testDatas = mapper.readValue(new File("src/test/resources/testDatas.json"), TestDatas.class);
        getDriver().manage().window().maximize();
        getDriver().get(config.url);
        signInPage = new SignInPage();
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}
