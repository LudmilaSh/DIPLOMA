package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

import static ru.iteco.fmhandroid.ui.data.EspressoHelper.childAtPosition;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.getCurrentTime;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.getRecyclerViewItemCount;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class CreateClaimsSteps {

    public void clickCreateClaim() {
        elementWaiting(withId(R.id.add_new_claim_material_button), 10000);
        ViewInteraction materialButton = onView(allOf(withId(R.id.add_new_claim_material_button)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }

    public void inputTitle(String title) {
        elementWaiting(withId(R.id.title_edit_text), 10000);
        ViewInteraction textInputEditText = onView(withId(R.id.title_edit_text));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(click());
        textInputEditText.perform(replaceText(title));
    }
    public void inputDate(String date) {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.date_in_plan_text_input_edit_text)));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(date));
    }
    public void inputTime(String time) {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.time_in_plan_text_input_edit_text)));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(time));
    }
    public void inputDescription(String description) {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.description_edit_text)));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(description));
    }
    public void clickSaveButton() {
        ViewInteraction materialButton = onView(allOf(withId(R.id.save_button)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(scrollTo(), click());
    }

    public void clickCancelButton() {
        ViewInteraction materialButton = onView(allOf(withId(R.id.cancel_button)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(scrollTo(), click());
    }

    public void recyclerViewClaims(String title) {
        elementWaiting(withId(R.id.claim_list_recycler_view), 8000);
        ViewInteraction recyclerView = onView(allOf(withId(R.id.claim_list_recycler_view)));
        recyclerView.check(matches(isDisplayed()));
        onView(withId(R.id.claim_list_recycler_view))
                .check(matches(not(hasDescendant(allOf(
                        withId(R.id.title_text_view),
                        withText(title),
                        isDisplayed()
                )))));
    }

    public void createClaim() {
        String title = "Новая заявка";
        String date = getCurrentDate();
        String time = getCurrentTime();
        String description = "Описание";
        elementWaiting(withId(R.id.main_menu_image_button), 8000);
        clickCreateClaim();
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        elementWaiting(withId(R.id.claim_list_recycler_view), 8000);
        recyclerViewClaims(title);
    }

    public void createClaimWithEmptyFields() {
        String title = " ";
        String date = getCurrentDate();
        String time = getCurrentTime();
        String description = " ";
        elementWaiting(withId(R.id.main_menu_image_button), 8000);
        clickCreateClaim();
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        onView(withText(R.string.empty_fields)).check(matches(withText("Fill empty fields")));
    }

    public void createClaimWithTitleMore50() {
        String title = "Создание заголовка с количеством символов более пятидесяти";
        String date = getCurrentDate();
        String time = getCurrentTime();
        String description = "Любое описание";
        elementWaiting(withId(R.id.main_menu_image_button), 8000);
        clickCreateClaim();
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        recyclerViewClaims("Создание заголовка с количеством символов более пя");
    }

    public void massageCancelCreateClaim() {
        String title = "Абсолютно новая заявка";
        String date = getCurrentDate();
        String time = getCurrentTime();
        String description = "Любое описание";
        elementWaiting(withId(R.id.main_menu_image_button), 8000);
        clickCreateClaim();
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickCancelButton();
        onView(withText(R.string.cancellation))
                .check(matches(withText("The changes won't be saved, do you really want to log out?")));
    }

    public void cancelCreateClaim() {
        onView(withText("OK")).perform(click());
        onView(withId(R.id.claim_list_recycler_view))
                .check(matches(not(hasDescendant(allOf(withId(R.id.title_text_view), withText("Абсолютно новая заявка"))))));
    }

    public void manualInputTime(String hour, String minute) {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.time_in_plan_text_input_edit_text),
                isDisplayed()));
        textInputEditText.perform(click());

        ViewInteraction appCompatImageButton = onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Switch to text input mode for the time input."),
                childAtPosition(childAtPosition(withClassName(is("android.widget.LinearLayout")), 4), 0),
                isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction appCompatEditText = onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                childAtPosition(allOf(withClassName(is("android.widget.RelativeLayout")),
                        childAtPosition(withClassName(is("android.widget.TextInputTimePickerView")), 1)), 0),
                isDisplayed()));
        appCompatEditText.perform(replaceText(hour), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatEditText")),
                childAtPosition(allOf(withClassName(is("android.widget.RelativeLayout")),
                        childAtPosition(withClassName(is("android.widget.TextInputTimePickerView")), 1)), 3),
                isDisplayed()));
        appCompatEditText2.perform(replaceText(minute), closeSoftKeyboard());

        ViewInteraction materialButton = onView(allOf(withId(android.R.id.button1), withText("OK"),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0), 3)));
        materialButton.perform(scrollTo(), click());
    }

    public void createClaimWithInvalidTime() {
        String date = getCurrentDate();
        String hour = "28";
        String minute = "68";
        String title = "Абсолютно новая заявка";
        clickCreateClaim();
        inputTitle(title);
        inputDate(date);
        manualInputTime(hour, minute);
        ViewInteraction textView = onView(allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Enter a valid time"),
                withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))), isDisplayed()));
        textView.check(matches(withText("Enter a valid time")));
    }
}
