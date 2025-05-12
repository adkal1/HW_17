package ru.andersenlab.lesson;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropPageTest extends BaseTest {
    @Epic("AndersenLab")
    @Story("Drag-and-Drop functionality")
    @Description("Checks drag-and-drop functionality both manual and automatic")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TMS-002")
    @Test
    public void testDragAndDropPage() {
        signInPage.setEmailField(config.email);
        signInPage.setPasswordField(config.password);
        signInPage.clickSubmitBtn();

        ProfilePage profilePage = new ProfilePage();
        profilePage.clickAqaPracticeBtn();
        profilePage.clickDragDropBtn();

        DragAndDropPage dragAndDropPage = new DragAndDropPage();
        dragAndDropPage.dragAndDropManual1();
        dragAndDropPage.dragAndDropManual2();
        dragAndDropPage.dragAndDropAuto1();
        dragAndDropPage.dragAndDropAuto2();
        Assert.assertEquals(dragAndDropPage.getResultLabel(), testDatas.dragAndDropResult);
    }
}
