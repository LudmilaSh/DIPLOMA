package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.childAtPosition;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.clickChildViewWithId;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.getCurrentTime;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.getRecyclerViewItemCount;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class EditCreateNewsSteps {
    FilterSortingNewsSteps filterSortingNewsSteps = new FilterSortingNewsSteps();
    CommonSteps commonSteps = new CommonSteps();

    public void checkEdit() {
        ViewInteraction materialButton = onView(withId(R.id.edit_news_material_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
        ViewInteraction addNews = onView(withId(R.id.add_news_image_view));
        elementWaiting(withId(R.id.add_news_image_view), 8000);
        addNews.check(matches(isDisplayed()));
    }

    public void filterActiveNews() {
        ViewInteraction materialButton = onView(withId(R.id.filter_news_material_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
        ViewInteraction materialCheckBox = onView(withId(R.id.filter_news_inactive_material_check_box));
        materialCheckBox.check(matches(isDisplayed()));
        materialCheckBox.perform(click());
        ViewInteraction filterButton = onView(withId(R.id.filter_button));
        filterButton.perform(click());
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(hasDescendant(withText("Active"))));
    }

    public void filterNotActiveNews() {
        ViewInteraction materialButton = onView(withId(R.id.filter_news_material_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
        ViewInteraction materialCheckBox = onView(withId(R.id.filter_news_active_material_check_box));
        materialCheckBox.check(matches(isDisplayed()));
        materialCheckBox.perform(click());
        ViewInteraction filterButton = onView(withId(R.id.filter_button));
        filterButton.perform(click());
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        onView(withId(R.id.news_list_recycler_view)).check(matches(hasDescendant(withText("Not active"))));
    }

    public void filterWithoutStatus() {
        ViewInteraction materialButton = onView(withId(R.id.filter_news_material_button));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
        ViewInteraction materialCheckBox = onView(withId(R.id.filter_news_inactive_material_check_box));
        materialCheckBox.check(matches(isDisplayed()));
        materialCheckBox.perform(click());
        ViewInteraction materialCheckBox2 = onView(withId(R.id.filter_news_active_material_check_box));
        materialCheckBox2.check(matches(isDisplayed()));
        materialCheckBox2.perform(click());
        ViewInteraction filterButton = onView(withId(R.id.filter_button));
        filterButton.check(matches(isNotClickable()));
    }

    public void filterDateFromFutureInControlPanel() {
        String dateFromFuture = "01.05.2024";
        ViewInteraction materialButton2 = onView(allOf(withId(R.id.filter_news_material_button)));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());
        filterSortingNewsSteps.startDate(dateFromFuture);
        filterSortingNewsSteps.endDate(dateFromFuture);
        ViewInteraction materialButton5 = onView(allOf(withId(R.id.filter_button), withText("Filter")));
        materialButton5.perform(click());
        ViewInteraction textView = onView(allOf(withId(R.id.news_control_panel_swipe_to_refresh)));
        textView.check(matches(isDisplayed()));

    }
    public void clickAddNews() {
        ViewInteraction materialButton = onView(allOf(withId(R.id.add_news_image_view)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }
    public void inputCategory(String category) {
        ViewInteraction textInputEditText = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
        textInputEditText.perform(click());
        textInputEditText.perform(replaceText(category));
    }
    public void inputTitle(String title) {
        ViewInteraction textInputEditText = onView(withId(R.id.news_item_title_text_input_edit_text));
        textInputEditText.perform(click());
        textInputEditText.perform(replaceText(title));
    }
    public void inputDate(String date) {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.news_item_publish_date_text_input_edit_text)));
        textInputEditText.perform(replaceText(date));
    }
    public void inputTime(String time) {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.news_item_publish_time_text_input_edit_text)));
        textInputEditText.perform(replaceText(time));
    }
    public void inputDescription(String description) {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.news_item_description_text_input_edit_text)));
        textInputEditText.perform(replaceText(description));
    }
    public void clickSaveButton() {
        ViewInteraction materialButton = onView(allOf(withId(R.id.save_button)));
        materialButton.perform(scrollTo(), click());
    }
    public void recyclerViewNews(String title) {
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        ViewInteraction recyclerView = onView(allOf(withId(R.id.news_list_recycler_view)));
        recyclerView.check(matches(isDisplayed()));
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(hasDescendant(allOf(
                        withId(R.id.news_item_title_text_view),
                        withText(title)))));
    }

    public void createNews() {
        String date = getCurrentDate();
        String time = getCurrentTime();
        String category = "Массаж";
        String title = "Тайский массаж";
        String description = "Дешево и сердито";
        clickAddNews();
        inputCategory(category);
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        recyclerViewNews(title);
    }

    public void createNewsWithoutCategory() {
        String date = getCurrentDate();
        String time = getCurrentTime();
        String title = "Тайский массаж";
        String description = "Дешево и сердито";
        clickAddNews();
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        onView(withText(R.string.empty_fields)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Fill empty fields")));
    }

    public void createNewsWithoutTitle() {
        String date = getCurrentDate();
        String time = getCurrentTime();
        String category = "Массаж";
        String description = "Дешево и сердито";
        clickAddNews();
        inputCategory(category);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        onView(withText(R.string.empty_fields)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Fill empty fields")));
    }

    public void createNewsWithoutDescription() {
        String date = getCurrentDate();
        String time = getCurrentTime();
        String category = "Массаж";
        String title = "Тайский массаж";
        clickAddNews();
        inputCategory(category);
        inputDate(date);
        inputTime(time);
        inputTitle(title);
        clickSaveButton();
        onView(withText(R.string.empty_fields)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Fill empty fields")));
    }

    public void createNewsWithoutDateAndTime() {
        String description = "Дешево и сердито";
        String category = "Массаж";
        String title = "Тайский массаж";
        clickAddNews();
        inputCategory(category);
        inputDescription(description);
        inputTitle(title);
        clickSaveButton();
        onView(withText(R.string.empty_fields)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Fill empty fields")));
    }

    public void createNewsWithSymbols () {
        String date = getCurrentDate();
        String time = getCurrentTime();
        String title = "@@@###";
        String description = "@@@$$$###";
        String category = "##$$@@@";
        clickAddNews();
        inputCategory(category);
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        onView(withText(R.string.error_saving)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Saving failed. Try again later.")));
    }

    public void createNewsFromFuture() {
        String date = "30.08.2023";
        String time = getCurrentTime();
        String category = "Объявление";
        String title = "Всем внимание";
        String description = "Проверка";
        clickAddNews();
        inputCategory(category);
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        ViewInteraction recyclerView = onView(allOf(withId(R.id.news_list_recycler_view)));
        recyclerView.check(matches(isDisplayed()));
        recyclerViewNews(title);
        //commonSteps.checkMenuButton("News");
        //elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        //ViewInteraction recyclerView1 = onView(allOf(withId(R.id.news_list_recycler_view)));
        //recyclerView1.check(matches(isDisplayed()));
        //onView(withId(R.id.news_list_recycler_view))
                //.check(matches(hasDescendant(allOf(
                        //withId(R.id.news_item_title_text_view),
                        //withText(title))))).check(ViewAssertions.doesNotExist());
    }

    public void notActiveCheckBoxStatus() {
        elementWaiting(withId(R.id.switcher), 5000);
            onView(allOf(withId(R.id.switcher))).check(matches(isNotEnabled()));
    }

    public void manualInputTime(String hour, String minute) {
        ViewInteraction textInputEditText = onView(allOf(withId(R.id.news_item_publish_time_text_input_edit_text),
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

    public void createNewsWithInvalidTime() {
        String date = getCurrentDate();
        String hour = "28";
        String minute = "68";
        String category = "Объявление";
        String title = "Всем внимание";
        clickAddNews();
        inputCategory(category);
        inputTitle(title);
        inputDate(date);
        manualInputTime(hour, minute);
        ViewInteraction textView = onView(allOf(IsInstanceOf.<View>instanceOf(android.widget.TextView.class), withText("Enter a valid time"),
                withParent(allOf(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                        withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))), isDisplayed()));
        textView.check(matches(withText("Enter a valid time")));
    }

    public void createNewsFromPast() {
        String date = "01.07.2023";
        String time = getCurrentTime();
        String category = "Объявление";
        String title = "Не должно сохраниться";
        String description = "Проверка";
        clickAddNews();
        inputCategory(category);
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        // Проверка отсутствия элемента с текстом "Не должно сохраниться"
        onView(allOf(
                withId(R.id.news_list_recycler_view),
                not(hasDescendant(allOf(
                        withId(R.id.news_item_title_text_view),
                        withText("Не должно сохраниться")
                )))
        )).check(matches(isDisplayed()));

    }

    public void warningMessageCancelCreateNews() {
        String date = getCurrentDate();
        String time = getCurrentTime();
        String title = "Тайский массаж";
        String description = "Дешево и сердито";
        clickAddNews();
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        ViewInteraction materialButton = onView(allOf(withId(R.id.cancel_button)));
        materialButton.perform(scrollTo(), click());
        onView(withText(R.string.cancellation))
                .check(matches(withText("The changes won't be saved, do you really want to log out?")));
    }

    public void cancelCreateNews() {
        onView(withText("OK")).perform(click());
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        onView(withId(R.id.news_list_recycler_view))
                .check(matches(Matchers.not(hasDescendant(allOf(withId(R.id.news_item_title_text_view), withText("Тайский массаж"))))));
    }

    public void warningMassageDeleteExistingNews() {
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItemAtPosition(0, clickChildViewWithId(R.id.delete_news_item_image_view)));
        onView(withText(R.string.irrevocable_deletion))
                .check(matches(withText("Are you sure you want to permanently delete the document? These changes cannot be reversed in the future.")));
    }

    public void deleteNews() {
        int itemCountBefore = getRecyclerViewItemCount();
        warningMassageDeleteExistingNews();
        onView(withText("OK")).perform(scrollTo(), click());
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        int itemCountAfter = getRecyclerViewItemCount();
        assertEquals(itemCountBefore - 1, itemCountAfter);
    }

    public void editNewsWithEmptyFields() {
        String category = " ";
        String title = " ";
        String description = " ";
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItemAtPosition(0, clickChildViewWithId(R.id.edit_news_item_image_view)));
        inputCategory(category);
        inputTitle(title);
        inputDescription(description);
        clickSaveButton();
        onView(withText(R.string.empty_fields)).inRoot(new EspressoHelper.ToastMatcher())
                .check(matches(withText("Fill empty fields")));
    }

    public void messageCancelEditNews() {
        String category = " ";
        String title = " ";
        String description = " ";
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItemAtPosition(0, clickChildViewWithId(R.id.edit_news_item_image_view)));
        inputCategory(category);
        inputTitle(title);
        inputDescription(description);
        ViewInteraction materialButton = onView(allOf(withId(R.id.cancel_button)));
        materialButton.perform(scrollTo(), click());
        onView(withText(R.string.cancellation))
                .check(matches(withText("The changes won't be saved, do you really want to log out?")));
    }

    public void editAllFieldsNews() {
        String date = getCurrentDate();
        String time = getCurrentTime();
        String category = "Массаж";
        String title = "Нужно изменить";
        String description = "Нужно изменить";
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItemAtPosition(0, clickChildViewWithId(R.id.edit_news_item_image_view)));
        inputCategory(category);
        inputTitle(title);
        inputDate(date);
        inputTime(time);
        inputDescription(description);
        clickSaveButton();
        recyclerViewNews(title);
    }

    public void editStatusNews() {
        filterActiveNews();
        int itemCountBefore = getRecyclerViewItemCount();
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItemAtPosition(0, clickChildViewWithId(R.id.edit_news_item_image_view)));
        ViewInteraction switchMaterial = onView(allOf(withId(R.id.switcher), withText("Active")));
        switchMaterial.perform(scrollTo(), click());
        clickSaveButton();
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        int itemCountAfter = getRecyclerViewItemCount();
        assertEquals(itemCountBefore - 1, itemCountAfter);
    }
}
