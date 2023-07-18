package ru.iteco.fmhandroid.ui.data;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotFocused;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.Is.is;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.childAtPosition;
import static ru.iteco.fmhandroid.ui.data.EspressoHelper.elementWaiting;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class OurMissionSteps {

    public void sectionOurMission() {
        ViewInteraction appCompatImageButton = onView(allOf(withId(R.id.our_mission_image_button), withContentDescription("Our Mission")));
        elementWaiting(withId(R.id.our_mission_image_button), 8000);
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        ViewInteraction textView = onView(allOf(withId(R.id.our_mission_title_text_view), withText("Love is all")));
        elementWaiting(withId(R.id.our_mission_title_text_view), 5000);
        textView.check(matches(isDisplayed()));
    }

    public void descriptionOurMission() {
        String text = "Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий.";
        ViewInteraction recyclerView = onView(allOf(withId(R.id.our_mission_item_list_recycler_view),
                childAtPosition(withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),0)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));
        ViewInteraction textView2 = onView(allOf(withId(R.id.our_mission_item_description_text_view), withText(text)));
        elementWaiting(withId(R.id.our_mission_item_description_text_view), 5000);
        textView2.check(matches(withText(text)));
        recyclerView.perform(actionOnItemAtPosition(1, click()));
        ViewInteraction textView3 = onView(allOf(withId(R.id.our_mission_item_title_text_view),
                withText("Хоспис в своем истинном понимании - это творчество"),
                withParent(withParent(withId(R.id.our_mission_item_material_card_view)))));
        textView3.check(matches(isDisplayed()));
        textView2.check(matches(isNotFocused()));
    }

}
