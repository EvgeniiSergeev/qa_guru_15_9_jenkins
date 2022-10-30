package com.demoqa.tests;

import static io.qameta.allure.Allure.step;
import com.codeborne.selenide.Configuration;
import com.demoqa.BaseTest;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class RegistrationFormWithPageObjectsTests extends BaseTest {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }
    @Test
    @DisplayName("Заполнение всех полей регистрационной формы")
    void fillFormTest() {
        step("Заполнение формы", () -> {
            registrationFormPage.openPage()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setEmail("alex@egorov.com")
                .setGender("Other")
                .setNumber("1234567890")
                .setBirthDate("30", "July", "2008")
                .setSubjects("Maths")
                .setHobbies("Sports")
                .uploadPicture("img/Picture 1.png")
                .setCurrentAddress("Some address 1")
                .setState("NCR")
                .setCity("Delhi")
                .clickSubmitButton();
        });

        step("Проверяем результат отправки формы", () -> {
            registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", "Alex Egorov")
                .checkResult("Student Email", "alex@egorov.com")
                .checkResult("Date of Birth", "30 July,2008")
                .checkResult("Gender", "Other")
                .checkResult("Mobile", "1234567890")
                .checkResult("Subjects", "Maths")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "Picture 1.png")
                .checkResult("Address", "Some address 1")
                .checkResult("State and City", "NCR Delhi");
        });
    }
    @Test
    void fillFormWithMinimumDataTest() {
        step("Заполнение обязательных полей формы", () -> {
            registrationFormPage.openPage()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setGender("Other")
                .setNumber("1234567890")
                .setBirthDate("30", "July", "2008")
                .clickSubmitButton();
        });

        step("Проверяем результат отправки формы", () -> {
            registrationFormPage.checkResultsTableVisible()
                .checkResult("Student Name", "Alex Egorov")
                .checkResult("Date of Birth", "30 July,2008")
                .checkResult("Gender", "Other")
                .checkResult("Mobile", "1234567890");
        });

    }

}
