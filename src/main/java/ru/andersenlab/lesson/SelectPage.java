package ru.andersenlab.lesson;

import org.openqa.selenium.By;
import ru.andersenlab.lesson.baseclasses.BaseForm;
import ru.andersenlab.lesson.elements.Button;
import ru.andersenlab.lesson.elements.Dropdown;
import ru.andersenlab.lesson.elements.TextBox;

public class SelectPage extends BaseForm {
    private final Dropdown countryDropdown = new Dropdown(By.xpath("//select[@title='Select country']"));
    private final Dropdown languageDropdown = new Dropdown(By.xpath("//select[@title='Select language']"));
    private final Dropdown typeDropdown = new Dropdown(By.xpath("//select[@title='Select type']"));
    private final TextBox startCalendar = new TextBox(By.xpath("//input[@title='Start date']"));
    private final TextBox endCalendar = new TextBox(By.xpath("//input[@title='End date']"));
    private final Dropdown selectCourses = new Dropdown(By.xpath("//select[@id='MultipleSelect']"));
    private final Button searchBtn = new Button(By.xpath("//button[@type='submit']"));

    public SelectPage() {
        super(By.xpath("//button[@name='SelectPageSearchButton']"));
    }

    public void setCountryDropdown(String country) {
        countryDropdown.setDropdown(country);
    }

    public void setLanguageDropdown(String language) {
        languageDropdown.setDropdown(language);
    }

    public void setTypeDropdown(String type) {
        typeDropdown.setDropdown(type);
    }

    public void setStartCalendar(String startDate) {
        startCalendar.setText(startDate);
    }

    public void setEndCalendar(String endDate) {
        endCalendar.setText(endDate);
    }

    public void setSelectCourses(String course1, String course2) {
        selectCourses.setDropdown(course1);
        selectCourses.setDropdown(course2);
    }

    public void clickSearchBtn() {
        searchBtn.click();
    }
}
