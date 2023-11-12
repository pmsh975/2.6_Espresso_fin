package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;


@RunWith(AllureAndroidJUnit4.class)
public class CheckGalleryTest {
    @Rule
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
    public void testGalleryElement() {
        ViewInteraction menu = onView(isAssignableFrom(AppCompatImageButton.class));
        menu.check(matches(isDisplayed()));
        menu.perform(click());
        ViewInteraction gallery = onView(withId(R.id.nav_gallery));
        gallery.check(matches(isDisplayed()));
        gallery.perform(click());
        ViewInteraction seventhItem = onView(allOf(withId(R.id.item_number), withText("7")));
        seventhItem.check(matches(withText("7")));
    }
}