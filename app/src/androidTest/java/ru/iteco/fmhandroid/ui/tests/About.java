package ru.iteco.fmhandroid.ui.tests;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Step;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.AboutSteps;
import ru.iteco.fmhandroid.ui.data.AuthorizationAndLogoutSteps;
import ru.iteco.fmhandroid.ui.data.CommonSteps;

@RunWith(AndroidJUnit4.class)
public class About {
    AboutSteps aboutSteps = new AboutSteps();
    CommonSteps commonSteps = new CommonSteps();
    AuthorizationAndLogoutSteps authorizationAndLogoutSteps = new AuthorizationAndLogoutSteps();

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            commonSteps.userLoggedIn();
        } catch (AssertionError e) {
            authorizationAndLogoutSteps.login();
        }
    }


    @Test
    @DisplayName("Переход в раздел о приложении")
    @Description("При переходе в раздел О приложении через кнопку главного меню отображается полная информация о приложении")
    public void shouldCheckAbout() {
        aboutSteps.about();
    }

    @Test
    @DisplayName("Наличие информации о версии приложения")
    @Description("При переходе в раздел О приложении через кнопку главного меню отображается информация о версии приложения")
    public void infoVersion() {
        aboutSteps.about();
        aboutSteps.version();
    }

    @Test
    @DisplayName("Наличие информации о компании-разработчике")
    @Description("При переходе в раздел О приложении через кнопку главного меню отображается информация о компании-разработчике")
    public void infoCompany() {
        aboutSteps.about();
        aboutSteps.company();
    }

    //должен упасть, т.к. при переходе ничего не открывается
    @Test
    @DisplayName("Наличие ссылки на Пользовательское соглашение и переход по ней")
    @Description("При переходе в раздел О приложении через кнопку главного меню присутствует ссылка на пользовательское соглашение" +
            "ссылка кликабельна и при переходе по ней отображаются условия пользовательского соглашения")
    public void infoPrivacyPolicy() {
        aboutSteps.about();
        aboutSteps.privacyPolicy();
    }

    //должен упасть, т.к. при переходе ничего не открывается
    @Test
    @DisplayName("Наличие ссылки на Политику конфиденциальности")
    @Description("При переходе в раздел О приложении через кнопку главного меню присутствует ссылка на политику конфиденциальности" +
            "ссылка кликабельна и при переходе по ней отображается информация о политике конфиденциальности")
    public void infoTermsOfUse() {
        aboutSteps.about();
        aboutSteps.termsOfUse();
    }
}
