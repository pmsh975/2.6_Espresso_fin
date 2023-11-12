package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class IntentsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityScenarioRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }

    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    public void intentTest() {
        ViewInteraction imageView = onView(
                Matchers.allOf(
                        withParent(withParent(withId(R.id.toolbar)))));
        imageView.check(matches(isDisplayed()));
        imageView.perform(click());


        ViewInteraction materialTextView = onView(
                allOf(withId(androidx.constraintlayout.widget.R.id.title), withText("Settings"),
                        isDisplayed()));

        Intents.init();
        materialTextView.perform(click());
        intended(allOf(
                hasData("https://google.com"),
                hasAction(Intent.ACTION_VIEW)
        ));
        Intents.release();
    }
}