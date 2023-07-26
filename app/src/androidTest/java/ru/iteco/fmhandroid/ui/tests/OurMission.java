package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.ScreenshotTestRule;
import ru.iteco.fmhandroid.ui.steps.AuthorizationAndLogoutSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.OurMissionSteps;

@RunWith(AllureAndroidJUnit4.class)
public class OurMission {
    AuthorizationAndLogoutSteps authorizationAndLogoutSteps = new AuthorizationAndLogoutSteps();
    CommonSteps commonSteps = new CommonSteps();
    OurMissionSteps ourMissionSteps = new OurMissionSteps();
    @Rule
    public ActivityTestRule<AppActivity> activityTestRule =
            new ActivityTestRule<>(AppActivity.class);

    @Rule
    public ScreenshotTestRule screenshotTestRule = new ScreenshotTestRule();


    @Before
    public void setUp() {
        try {
            commonSteps.userLoggedIn();
        } catch (AssertionError e) {
            authorizationAndLogoutSteps.login();
        }
    }


    @Test
    @DisplayName("Переход в раздел цитаты через иконку Бабочка")
    @Description("При нажатии на иконку Бабочка пользователь переходит в раздел Цитат")
    public void shouldCheckOurMission() {
        ourMissionSteps.sectionOurMission();
    }

    @Test
    @DisplayName("Отображение полного и сокращенного содержания цитаты")
    @Description("При нажатии на цитату отображается полное ее содержимое")
    public void shouldOpenAndCloseDescription() {
        ourMissionSteps.sectionOurMission();
        ourMissionSteps.descriptionOurMission();
    }

}
