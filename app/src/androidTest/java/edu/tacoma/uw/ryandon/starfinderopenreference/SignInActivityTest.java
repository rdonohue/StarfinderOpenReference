package edu.tacoma.uw.ryandon.starfinderopenreference;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import edu.tacoma.uw.ryandon.starfinderopenreference.authenticate.SignInActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class SignInActivityTest {

    @Rule
    public ActivityTestRule<SignInActivity> mActivityRule = new ActivityTestRule<> (SignInActivity.class);

    @Test
    public void testLogin() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUsername))
                .perform(typeText("faker2@uw.edu"));
        onView(withId(R.id.editTextPassword))
                .perform(typeText("123456"));
        onView(withId(R.id.loginButton))
                .perform(click());

        onView(withText("Choose Spell Filters"))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testLoginInvalidEmail() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUsername))
                .perform(typeText("mmuppauw.edu"));
        onView(withId(R.id.editTextPassword))
                .perform(typeText("test1@#"));
        onView(withId(R.id.loginButton))
                .perform(click());

        onView(withText("Enter valid email address"))
                .check(matches(isDisplayed()));
//        onView(withText("Enter valid email address"))
//                .inRoot(withDecorView(not(is(mActivityRule.getActivity().
//                        getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

    @Test
    public void testRegisterInvalidPassword() {
        // Type text and then press the button.
        onView(withId(R.id.editTextUsername))
                .perform(typeText("mmuppa@uw.edu"));
        onView(withId(R.id.editTextPassword))
                .perform(typeText(""));
        onView(withId(R.id.loginButton))
                .perform(click());

        onView(withText(R.string.invalid_pwd))
                .check(matches(isDisplayed()));
//        onView(withText(R.string.invalid_pwd))
//                .inRoot(withDecorView(not(is(mActivityRule.getActivity()
//                .getWindow().getDecorView()))))
//                .check(matches(isDisplayed()));

    }

}
