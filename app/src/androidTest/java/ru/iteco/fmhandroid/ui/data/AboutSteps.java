package ru.iteco.fmhandroid.ui.data;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.matchesPattern;

import static ru.iteco.fmhandroid.ui.data.EspressoHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;


public class AboutSteps {

    public void about() {
        elementWaiting(withId(R.id.main_menu_image_button), 8000);
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.main_menu_image_button),
                withContentDescription("Main menu")));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        ViewInteraction materialTextView = onView(allOf(withId(android.R.id.title), withText("About")));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());
        onView(allOf(withId(R.id.about_version_title_text_view), withText("Version:"), isDisplayed()));
    }

    public void version() {
        onView(allOf(withId(R.id.about_version_value_text_view), withText("Version:"), isDisplayed()));
    }

    public void company() {
        onView(allOf(withId(R.id.about_company_info_label_text_view), isDisplayed()));
    }

    public void privacyPolicy() {
        ViewInteraction materialTextView = onView(allOf(withId(R.id.about_privacy_policy_label_text_view), withText("Privacy Policy:"), isDisplayed()));
        materialTextView.perform(click());
        materialTextView.check(matches(withText("Privacy policy")));
    }

    public void termsOfUse() {
        ViewInteraction materialTextView = onView(allOf(withId(R.id.about_terms_of_use_label_text_view), withText("Terms of use:"), isDisplayed()));
        materialTextView.perform(click());
        materialTextView.check(matches(withText("Terms of use")));
    }
}
