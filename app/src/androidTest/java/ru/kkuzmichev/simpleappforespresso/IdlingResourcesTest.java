package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.kkuzmichev.simpleappforespresso.CustomViewAssertions.isRecycleView;
import static ru.kkuzmichev.simpleappforespresso.CustomViewMatcher.recyclerViewSizeMatcher;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)

public class IdlingResourcesTest {

    private final ViewInteraction appCompatImageButton = onView(withContentDescription("Open navigation drawer"));
    private final ViewInteraction checkedTextGallery = onView(withId(R.id.nav_gallery));
    private final ViewInteraction itemSeventh = onView(allOf(withId(R.id.item_number), withText("7")));
    private final ViewInteraction recycleListing = onView(withId(R.id.recycle_view));

    @Rule
//    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
//            new ActivityScenarioRule<>(MainActivity.class);
    public IntentsTestRule<MainActivity> activityTestRule = new IntentsTestRule<>(MainActivity.class);

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
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        checkedTextGallery.check(matches(isDisplayed()));
        checkedTextGallery.perform(click());
        itemSeventh.check(matches(isDisplayed()));
        itemSeventh.check(matches(withText("7")));
    }

    @Test
    public void checkingNumberElementsTest() {
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        checkedTextGallery.check(matches(isDisplayed()));
        checkedTextGallery.perform(click());
        recycleListing.check(matches(isDisplayed()));
        recycleListing.check(matches(recyclerViewSizeMatcher(10)));
    }

    @Test
    public void checkingThatListIsListTest() {
        appCompatImageButton.check(matches(isDisplayed()));
        appCompatImageButton.perform(click());
        checkedTextGallery.check(matches(isDisplayed()));
        checkedTextGallery.perform(click());
        recycleListing.check(isRecycleView());
    }
}
