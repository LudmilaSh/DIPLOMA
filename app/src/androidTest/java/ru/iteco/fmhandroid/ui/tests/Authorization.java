package ru.iteco.fmhandroid.ui.tests;

import android.os.SystemClock;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Step;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.AuthorizationAndLogoutSteps;
import ru.iteco.fmhandroid.ui.data.CommonSteps;

@RunWith(AndroidJUnit4.class)
public class Authorization {
    AuthorizationAndLogoutSteps authorizationAndLogoutSteps = new AuthorizationAndLogoutSteps();
    CommonSteps commonSteps = new CommonSteps();

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);
    @Before
    public void checkLogOut() {
        try {
            commonSteps.userLogout();
        } catch (Exception e) {
            authorizationAndLogoutSteps.logOut();
            commonSteps.userLogout();
        }
    }


    @Test
    @DisplayName("Авторизация с валидными данными")
    @Description("При вводе валидных логина и пароля пользователь входит в приложение")
    public void validAuthorization() {
        authorizationAndLogoutSteps.login();
    }

    @Test
    @DisplayName("Авторизация с невалидным логином")
    @Description("При вводе невалидного логина появляется сообщение, что логин или пароль неправильный")
    public void authWithInvalidLogin() {
        authorizationAndLogoutSteps.invalidLogin();
    }

    @Test
    @DisplayName("Авторизация с невалидным паролем")
    @Description("При вводе невалидного пароля появляется сообщение, что логин или пароль неправильный")
    public void authWithInvalidPassword() {
        authorizationAndLogoutSteps.invalidPassword();
    }

    @Test
    @DisplayName("Авторизация с незаполненным полем пароль")
    @Description("При незаполненном поле пароль появляется сообщение, что поле логин или пароль не может быть пустым")
    public void authWithEmptyPassword() {
        authorizationAndLogoutSteps.emptyPassword();
    }

    @Test
    @DisplayName("Авторизация с незаполненным полем логин")
    @Description("При незаполненном поле логин появляется сообщение, что поле логин или пароль не может быть пустым")
    public void authWithEmptyLogin() {
        authorizationAndLogoutSteps.emptyLogin();
    }

    @Test
    @DisplayName("Авторизация с незаполненным полем логин и пароль")
    @Description("При незаполненном поле пароль и логин появляется сообщение, что поле логин или пароль не может быть пустым")
    public void authWithEmptyLoginAndPassword() {
        authorizationAndLogoutSteps.emptyLoginAndPassword();
    }

    @Test
    @DisplayName("Авторизация с невалидными паролем и логином")
    @Description("При вводе невалидного логина и пароля появляется сообщение, что поле логин или пароль неправильные")
    public void authWithInvalidLoginAndPassword() {
        authorizationAndLogoutSteps.invalidPasswordAndLogin();
    }

    @Test
    @DisplayName("Выход из приложения")
    @Description("При нажатии на кнопку Выхода из приложения пользователь попадает на страницу авторизации")
    public void shouldLogout() {
        authorizationAndLogoutSteps.login();
        authorizationAndLogoutSteps.logOut();
    }
}
