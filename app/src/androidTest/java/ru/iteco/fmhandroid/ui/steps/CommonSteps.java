package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocused;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.waitForElement;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class CommonSteps {

    public void userLoggedIn() {
        elementWaiting(withId(R.id.main_menu_image_button), 10000);
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.main_menu_image_button),
                withContentDescription("Main menu")));
        appCompatImageButton.check(matches(isDisplayed()));
    }

    public void userLogout() {
        elementWaiting(withId(R.id.enter_button), 8000);
        ViewInteraction materialButton = onView(allOf(withId(R.id.enter_button),
                withContentDescription("Save")));
        materialButton.check(matches(isDisplayed()));
    }

    public void checkMenuButton(String text) {
        elementWaiting(withId(R.id.main_menu_image_button), 8000);
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.main_menu_image_button),
                withContentDescription("Main menu")));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        ViewInteraction materialTextView = onView(allOf(withId(android.R.id.title), withText(text)));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction textView = onView(allOf(withText(text)));
        textView.check(matches(isDisplayed()));
        textView.check(matches(withText(text)));
    }

}
