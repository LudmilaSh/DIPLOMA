package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;
import androidx.core.widget.NestedScrollView;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.childAtPosition;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;

public class EditClaimsSteps {

    public void editComments(String comment) {
        ViewInteraction textInputEditText = onView(allOf(
                childAtPosition(childAtPosition(withId(R.id.comment_text_input_layout), 0), 1)));
        textInputEditText.perform(replaceText(comment));
    }

    public void editCommentClaims() {
        String comment = "редактирование комментария";
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.edit_comment_image_button), childAtPosition(
                childAtPosition(withId(R.id.claim_comments_list_recycler_view), 0), 1)));
        appCompatImageButton.perform(click());
        editComments(comment);
        ViewInteraction materialButton = onView(allOf(withId(R.id.save_button)));
        materialButton.perform(scrollTo(), click());
        elementWaiting(withId(R.id.add_comment_image_button), 8000);
        onView(withId(R.id.claim_comments_list_recycler_view))
                .check(matches(hasDescendant(allOf(
                        withId(R.id.comment_description_text_view),
                        withText(comment)))));
    }

    public void clickAddCommentInputText(String text) {
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 8000);
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.add_comment_image_button)));
        appCompatImageButton.perform(click());
        ViewInteraction textInputEditText = onView(allOf(childAtPosition(childAtPosition(
                withId(R.id.comment_text_input_layout), 0), 1)));
        textInputEditText.perform(replaceText(text), closeSoftKeyboard());
        ViewInteraction materialButton = onView(allOf(withId(R.id.save_button)));
        materialButton.perform(scrollTo(), click());
    }

    public void addComment() {
        String text = "new comment";
        clickAddCommentInputText(text);
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 8000);
        onView(withId(R.id.claim_comments_list_recycler_view))
                .check(matches(hasDescendant(allOf(
                        withId(R.id.comment_description_text_view),
                        withText(text)))));
    }

    public void addCommentWithoutText() {
        String text = " ";
        clickAddCommentInputText(text);
        onView(withText(R.string.toast_empty_field)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("The field cannot be empty.")));
    }

    public void cancelAddComment() {
        String text = "не должен сохраниться";
        clickAddCommentInputText(text);
        elementWaiting(withId(R.id.cancel_button), 8000);
        ViewInteraction materialButton = onView(allOf(withId(R.id.cancel_button)));
        materialButton.perform(scrollTo(), click());
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 8000);
        onView(allOf(
                withId(R.id.claim_comments_list_recycler_view),
                not(hasDescendant(allOf(
                        withId(R.id.comment_description_text_view),
                        withText(text)
                )))
        )).check(matches(isDisplayed()));
    }

    public void clickChangeStatusButton() {
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 8000);
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.status_processing_image_button)));
        appCompatImageButton.perform(click());
    }

    public void clickEditClaimButton() {
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 8000);
        onView(withId(R.id.claim_comments_list_recycler_view)).perform(swipeUp());
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.edit_processing_image_button)));
        appCompatImageButton.perform(click());
    }

    public void changeOpenStatusClaims(String status1, String status2) {
        onView(withText(status1)).perform(click());
        ViewInteraction textView = onView(allOf(withId(R.id.status_label_text_view), withText(status2)));
        elementWaiting(withId(R.id.status_label_text_view), 5000);
        textView.check(matches(withText(status2)));
    }

    public void changeInProgressStatusClaims(String status1, String status2) {
        onView(withText(status1)).perform(click());
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.editText)));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText("comment"), closeSoftKeyboard());
        ViewInteraction textView = onView(allOf(withId(R.id.status_label_text_view), withText(status2)));
        ViewInteraction materialButton = onView(allOf(withId(android.R.id.button1), withText("OK")));
        materialButton.perform(scrollTo(), click());
        elementWaiting(withId(R.id.status_label_text_view), 5000);
        textView.check(matches(withText(status2)));
    }

    public void editClaimNotInOpenStatus() {
        onView(withText(R.string.inability_to_edit_claim)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText(R.string.inability_to_edit_claim)));
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 5000);
    }

    public void clickableEdit() {
        elementWaiting(withId(R.id.claim_comments_list_recycler_view), 8000);
        onView(withId(R.id.claim_comments_list_recycler_view)).perform(swipeUp());
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.edit_processing_image_button)));
        appCompatImageButton.check(matches(isClickable()));
    }

    public void changeTitle(String title) {
        ViewInteraction textInputEditText = onView(withId(R.id.title_edit_text));
        textInputEditText.perform(click());
        textInputEditText.perform(replaceText(title), closeSoftKeyboard());
    }

    public void clickSaveButton() {
        ViewInteraction materialButton = onView(allOf(withId(R.id.save_button)));
        materialButton.perform(scrollTo(), click());
    }

    public void clickCancelButton() {
        ViewInteraction materialButton = onView(allOf(withId(R.id.cancel_button)));
        materialButton.perform(scrollTo(), click());
    }

    public void emptyTitleEditClaim() {
        clickSaveButton();
        onView(withText(R.string.empty_fields)).check(matches(withText("Fill empty fields")));
    }

    public void editClaim() {
        String title = "Изменена";
        changeTitle(title);
        clickSaveButton();
        onView(withId(R.id.title_text_view)).check(matches(withText(title)));
    }

    public void massageCancelEditClaim() {
        String title = "Другой заголовок";
        changeTitle(title);
        clickCancelButton();
        onView(withText(R.string.cancellation))
                .check(matches(withText("The changes won't be saved, do you really want to log out?")));
    }

    public void cancelEditClaim() {
        onView(withText("OK")).perform(click());
        elementWaiting(withId(R.id.title_text_view), 8000);
        onView(withId(R.id.title_text_view)).check(matches(not(withText("Другой заголовок"))));
    }
}
