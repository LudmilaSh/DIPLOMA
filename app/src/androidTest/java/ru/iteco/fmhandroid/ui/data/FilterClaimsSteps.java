package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.childAtPosition;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.elementWaiting;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterClaimsSteps {



    public void openAndCloseClaimsOnMain() {
        elementWaiting(withId(R.id.main_menu_image_button), 5000);
        ViewInteraction materialButton = onView(allOf(withId(R.id.expand_material_button),
                childAtPosition(childAtPosition(
                        withId(R.id.container_list_claim_include_on_fragment_main), 0), 3)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
        onView(allOf(withId(R.id.claim_list_recycler_view ), isNotFocusable()));
        materialButton.perform(click());
        onView(allOf(withId(R.id.claim_list_recycler_view ), isDisplayed()));
    }

    public void checkAllClaims() {
        elementWaiting(withId(R.id.all_claims_text_view), 10000);
        ViewInteraction materialTextView = onView(allOf(withId(R.id.all_claims_text_view), withText("all claims"),
                        childAtPosition(allOf(withId(R.id.container_list_claim_include_on_fragment_main),
                                        childAtPosition(withClassName(is("android.widget.LinearLayout")), 1)), 1)));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction textView = onView(allOf(withText("Claims")));
        textView.check(matches(isDisplayed()));
        textView.check(matches(withText("Claims")));
    }

    public void checkFilterButton() {
        LoadingIdlingResource loadingIdlingResource = new LoadingIdlingResource(5000);
        long waitingTime = 5000;
        IdlingRegistry.getInstance().register(new LoadingIdlingResource(waitingTime));
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.filters_material_button), withContentDescription("Filter claim list menu button"),
                        childAtPosition(childAtPosition(withId(R.id.container_list_claim_include), 0), 1)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
        IdlingRegistry.getInstance().unregister(loadingIdlingResource);
    }

    public void clickCheckBoxInProgress() {
        elementWaiting(withId(R.id.item_filter_in_progress), 5000);
        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.item_filter_in_progress), withText("In progress")));
        materialCheckBox.perform(scrollTo(), click());
    }

    public void clickCheckBoxOpen() {
        elementWaiting(withId(R.id.item_filter_open), 5000);
        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.item_filter_open), withText("Open")));
        materialCheckBox.perform(scrollTo(), click());
    }

    public void clickCheckBoxExecuted() {
        elementWaiting(withId(R.id.item_filter_executed), 5000);
        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.item_filter_executed), withText("Executed")));
        materialCheckBox.perform(scrollTo(), click());
    }

    public void clickCheckBoxCancelled() {
        elementWaiting(withId(R.id.item_filter_cancelled), 5000);
        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.item_filter_cancelled), withText("Cancelled")));
        materialCheckBox.perform(scrollTo(), click());
    }

    public void clickOkButton() {
        ViewInteraction materialButton2 = onView(allOf(withId(R.id.claim_list_filter_ok_material_button), withText("OK")));
        materialButton2.perform(scrollTo(), click());
    }

    public void filterClaimsOneStatus(String text) {
        elementWaiting(withId(R.id.claim_list_recycler_view), 8000);
        ViewInteraction recyclerView = onView(allOf(withId(R.id.claim_list_recycler_view)));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        ViewInteraction textView = onView(allOf(withId(R.id.status_label_text_view), withText(text)));
        elementWaiting(withId(R.id.status_label_text_view), 8000);
        textView.check(matches(withText(text)));
    }

    public void filterWithoutStatus() {
        ViewInteraction textView = onView(allOf(withId(R.id.empty_claim_list_text_view)));
        textView.check(matches(isDisplayed()));
    }

    public void fullInfoAboutClaims(){
        elementWaiting(withId(R.id.claim_list_recycler_view), 10000);
        ViewInteraction recyclerView = onView(allOf(withId(R.id.claim_list_recycler_view)));
        recyclerView.check(matches(isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(3, click()));
        ViewInteraction textView = onView(allOf(withId(R.id.status_label_text_view)));
        elementWaiting(withId(R.id.status_label_text_view), 10000);
        textView.check(matches(isDisplayed()));
    }
}
