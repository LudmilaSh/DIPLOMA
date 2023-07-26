package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocused;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.ViewActions;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.not;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;

import static ru.iteco.fmhandroid.ui.data.EspressoHelper.childAtPosition;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.waitForElement;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.EspressoHelper;
import ru.iteco.fmhandroid.ui.data.LoadingIdlingResource;

public class AuthorizationAndLogoutSteps {

    public void login() {
        LoadingIdlingResource loadingIdlingResource = new LoadingIdlingResource(5000);
        long waitingTime = 5000;
        IdlingRegistry.getInstance().register(new LoadingIdlingResource(waitingTime));
        elementWaiting(withId(R.id.login_text_input_layout), 8000);
        Espresso.onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
        Espresso.onView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))))
                .perform(ViewActions.typeText("login2"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout)))))
                .perform(ViewActions.typeText("password2"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.enter_button)).perform(click());
        elementWaiting(withId(R.id.main_menu_image_button), 8000);
        Espresso.onView(withId(R.id.main_menu_image_button)).check(matches(isCompletelyDisplayed()));
        IdlingRegistry.getInstance().unregister(loadingIdlingResource);
    }

    public void invalidLogin() {
        LoadingIdlingResource loadingIdlingResource = new LoadingIdlingResource(5000);
        long waitingTime = 5000;
        IdlingRegistry.getInstance().register(new LoadingIdlingResource(waitingTime));
        elementWaiting(withId(R.id.login_text_input_layout), 8000);
        onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
        onView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))))
                .perform(ViewActions.typeText("qwerty"));
        Espresso.closeSoftKeyboard();

        onView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout)))))
                .perform(ViewActions.typeText("password2"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.enter_button)).perform(click());
        onView(withText(R.string.wrong_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(isDisplayed()));
        onView(withText(R.string.wrong_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Wrong login or password")));
        IdlingRegistry.getInstance().unregister(loadingIdlingResource);
    }

    public void invalidPassword() {
        LoadingIdlingResource loadingIdlingResource = new LoadingIdlingResource(5000);
        long waitingTime = 5000;
        IdlingRegistry.getInstance().register(new LoadingIdlingResource(waitingTime));
        elementWaiting(withId(R.id.login_text_input_layout), 8000);
        onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
        onView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))))
                .perform(ViewActions.typeText("login2"));
        Espresso.closeSoftKeyboard();

        onView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout)))))
                .perform(ViewActions.typeText("qwerty"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.enter_button)).perform(click());
        onView(withText(R.string.wrong_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(isDisplayed()));
        onView(withText(R.string.wrong_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Wrong login or password")));
        IdlingRegistry.getInstance().unregister(loadingIdlingResource);
    }

    public void emptyPassword() {
        LoadingIdlingResource loadingIdlingResource = new LoadingIdlingResource(5000);
        long waitingTime = 5000;
        IdlingRegistry.getInstance().register(new LoadingIdlingResource(waitingTime));
        elementWaiting(withId(R.id.login_text_input_layout), 8000);
        onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
        onView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))))
                .perform(ViewActions.typeText("login2"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.enter_button)).perform(click());
        onView(withText(R.string.empty_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(isDisplayed()));
        onView(withText(R.string.empty_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Login and password cannot be empty")));
        IdlingRegistry.getInstance().unregister(loadingIdlingResource);
    }

    public void emptyLogin() {
        LoadingIdlingResource loadingIdlingResource = new LoadingIdlingResource(5000);
        long waitingTime = 5000;
        IdlingRegistry.getInstance().register(new LoadingIdlingResource(waitingTime));
        elementWaiting(withId(R.id.login_text_input_layout), 8000);
        onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
        onView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout)))))
                .perform(ViewActions.typeText("password2"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.enter_button)).perform(click());
        onView(withText(R.string.empty_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(isDisplayed()));
        onView(withText(R.string.empty_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Login and password cannot be empty")));
        IdlingRegistry.getInstance().unregister(loadingIdlingResource);
    }

    public void emptyLoginAndPassword() {
        LoadingIdlingResource loadingIdlingResource = new LoadingIdlingResource(5000);
        long waitingTime = 5000;
        IdlingRegistry.getInstance().register(new LoadingIdlingResource(waitingTime));
        elementWaiting(withId(R.id.login_text_input_layout), 8000);
        onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.enter_button)).perform(click());
        onView(withText(R.string.empty_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(isDisplayed()));
        onView(withText(R.string.empty_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Login and password cannot be empty")));
        IdlingRegistry.getInstance().unregister(loadingIdlingResource);
    }

    public void invalidPasswordAndLogin() {
        LoadingIdlingResource loadingIdlingResource = new LoadingIdlingResource(5000);
        long waitingTime = 5000;
        IdlingRegistry.getInstance().register(new LoadingIdlingResource(waitingTime));
        elementWaiting(withId(R.id.login_text_input_layout), 8000);
        onView(withId(R.id.login_text_input_layout)).check(matches(isDisplayed()));
        onView(allOf(withHint("Login"), withParent(withParent(withId(R.id.login_text_input_layout)))))
                .perform(ViewActions.typeText("qwerty"));
        Espresso.closeSoftKeyboard();
        onView(allOf(withHint("Password"), withParent(withParent(withId(R.id.password_text_input_layout)))))
                .perform(ViewActions.typeText("qwerty"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.enter_button)).perform(click());
        onView(withText(R.string.wrong_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(isDisplayed()));
        onView(withText(R.string.wrong_login_or_password)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Wrong login or password")));
        IdlingRegistry.getInstance().unregister(loadingIdlingResource);
    }


    public void logOut() {
        LoadingIdlingResource loadingIdlingResource = new LoadingIdlingResource(5000);
        long waitingTime = 5000;
        IdlingRegistry.getInstance().register(new LoadingIdlingResource(waitingTime));
        elementWaiting(withId(R.id.authorization_image_button), 5000);
        Espresso.onView(withId(R.id.authorization_image_button)).perform(click());
        onView(isRoot()).perform(waitForElement(withText("Log out"), 1000));
        Espresso.onView(withText("Log out")).perform(click());
        elementWaiting(withId(R.id.enter_button), 5000);
        Espresso.onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
        IdlingRegistry.getInstance().unregister(loadingIdlingResource);
    }

}
