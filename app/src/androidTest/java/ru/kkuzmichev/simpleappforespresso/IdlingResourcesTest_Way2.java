package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.kkuzmichev.simpleappforespresso.CustomViewAssertions.isRecycleView;
import static ru.kkuzmichev.simpleappforespresso.CustomViewMatcher.recyclerViewSizeMatcher;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
//@RunWith(AllureAndroidJUnit4.class)

public class IdlingResourcesTest_Way2 {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }

    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    public void idlingResourcesTest() {
        ViewInteraction appCompatImageButton = onView(isAssignableFrom(AppCompatImageButton.class));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction navigationMenuItemView = onView(withId(R.id.nav_gallery));
        navigationMenuItemView.check(matches(isDisplayed()));
        navigationMenuItemView.perform(click());

        ViewInteraction sevenItem = onView(allOf(withId(R.id.item_number), withText("7")));
        sevenItem.check(matches(isDisplayed()));
        sevenItem.check(matches(withText("7")));
    }

    @Test
    public void checkingNumberElementsTest() {
        ViewInteraction appCompatImageButton = onView(isAssignableFrom(AppCompatImageButton.class));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction checkedTextGallery = onView(withId(R.id.nav_gallery));
        checkedTextGallery.check(matches(isDisplayed()));
        checkedTextGallery.perform(click());

        ViewInteraction recycleListing = onView(withId(R.id.recycle_view));
        recycleListing.check(matches(isDisplayed()));
        recycleListing.check(matches(recyclerViewSizeMatcher(10)));
    }

    @Test
    public void checkingThatListIsListTest() {
        ViewInteraction appCompatImageButton = onView(isAssignableFrom(AppCompatImageButton.class));
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction checkedTextGallery = onView(withId(R.id.nav_gallery));
        checkedTextGallery.check(matches(isDisplayed()));
        checkedTextGallery.perform(click());

        ViewInteraction recycleListing = onView(withId(R.id.recycle_view));
        recycleListing.check(isRecycleView());
    }
}
