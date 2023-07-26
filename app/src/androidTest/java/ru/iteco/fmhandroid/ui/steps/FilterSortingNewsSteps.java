package ru.iteco.fmhandroid.ui.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;

import androidx.test.espresso.contrib.RecyclerViewActions;

import static kotlinx.coroutines.flow.FlowKt.withIndex;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.childAtPosition;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.elementWaiting;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.withIndex;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.withRecyclerViewItemResource;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Assert;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.data.EspressoHelper;

public

class FilterSortingNewsSteps {
    public String dateFromFuture = "01.01.2029";

    public void startDate (String startDate) {
        ViewInteraction textInputEditText = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
        textInputEditText.perform(replaceText(startDate));
    }
    public void endDate (String endDate) {
        ViewInteraction textInputEditText = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
        textInputEditText.perform(replaceText(endDate));
    }
    public void openAndCloseNewsOnMain() {
        elementWaiting(withId(R.id.main_menu_image_button), 5000);
        ViewInteraction materialButton3 = onView(allOf(withId(R.id.expand_material_button),
                childAtPosition(childAtPosition(withId(R.id.container_list_news_include_on_fragment_main), 0), 4)));
        materialButton3.check(matches(isDisplayed()));
        materialButton3.perform(click());
        onView(allOf(withId(R.id.news_list_recycler_view ), isNotFocusable()));
        materialButton3.perform(click());
        onView(allOf(withId(R.id.news_list_recycler_view ), isDisplayed()));
    }

    public void checkAllNews() {
        elementWaiting(withId(R.id.main_menu_image_button), 5000);
        ViewInteraction materialTextView = onView(allOf(withId(R.id.all_news_text_view), withText("All news"),
                childAtPosition(allOf(withId(R.id.container_list_news_include_on_fragment_main),
                        childAtPosition(withClassName(is("android.widget.LinearLayout")), 0)), 1)));
        materialTextView.check(matches(isDisplayed()));
        materialTextView.perform(click());
        ViewInteraction textView = onView(allOf(withText("News"), withParent(withParent(withId(R.id.container_list_news_include)))));
        textView.check(matches(isDisplayed()));
        textView.check(matches(withText("News")));
    }

    public void clickDateSortingNews() {
        ViewInteraction materialButton = onView(allOf(withId(R.id.sort_news_material_button)));
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }

    public String getFirstNewsPublicationDate(int position) {
        return EspressoHelper.getText(onView(withIndex(withId(R.id.news_item_publication_date_text_view), position)));
    }

    public String getFirstNewsDate(int position) {
        return EspressoHelper.getText(onView(withIndex(withId(R.id.news_item_date_text_view), position)));
    }

    public void dateSortingOneClick() {
        String date = getFirstNewsDate(0);
        clickDateSortingNews();
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        String dateAfterSorting = getFirstNewsDate(8);
        Assert.assertEquals(date, dateAfterSorting);
    }

    public void dateSortingDoubleClick() {
        clickDateSortingNews();
        clickDateSortingNews();
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        String dateAfterDoubleSorting = getFirstNewsDate(0);
        Assert.assertEquals(dateAfterDoubleSorting, "01.01.001");
    }


    public void dateSortingOneClickControlPanel() {
        String date = getFirstNewsPublicationDate(0);
        clickDateSortingNews();
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        String dateAfterSorting = getFirstNewsPublicationDate(3);
        Assert.assertEquals(date, dateAfterSorting);
    }

    public void dateSortingDoubleClickControlPanel() {
        clickDateSortingNews();
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        clickDateSortingNews();
        elementWaiting(withId(R.id.news_list_recycler_view), 8000);
        String dateAfterDoubleSorting = getFirstNewsPublicationDate(0);
        Assert.assertEquals(dateAfterDoubleSorting, "01.01.001");
    }

    public void filterDateFromFuture() {
        ViewInteraction materialButton2 = onView(allOf(withId(R.id.filter_news_material_button)));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());
        startDate(dateFromFuture);
        endDate(dateFromFuture);
        ViewInteraction materialButton5 = onView(allOf(withId(R.id.filter_button), withText("Filter")));
        materialButton5.perform(click());
        elementWaiting(withId(R.id.empty_news_list_text_view), 8000);
        ViewInteraction textView = onView(allOf(withId(R.id.empty_news_list_text_view)));
        textView.check(matches(isDisplayed()));

    }



    public void filterDate() {
        String date = "01.05.2023";
        ViewInteraction materialButton2 = onView(allOf(withId(R.id.filter_news_material_button)));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());
        startDate(date);
        endDate(date);
        ViewInteraction materialButton5 = onView(allOf(withId(R.id.filter_button), withText("Filter")));
        materialButton5.perform(click());
        onView(withId(R.id.news_list_recycler_view)).check(matches(hasDescendant(withText(date))));
    }

    public void cancelFilter() {
        String date = "01.05.2023";
        ViewInteraction materialButton2 = onView(allOf(withId(R.id.filter_news_material_button)));
        materialButton2.check(matches(isDisplayed()));
        materialButton2.perform(click());
        startDate(date);
        endDate(date);
        ViewInteraction cancelButton = onView(allOf(withId(R.id.cancel_button)));
        cancelButton.perform(click());
        onView(ViewMatchers.withId(R.id.news_list_recycler_view))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
