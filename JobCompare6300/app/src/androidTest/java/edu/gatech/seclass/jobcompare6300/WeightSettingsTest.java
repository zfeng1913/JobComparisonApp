package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.widget.Button;
import android.widget.EditText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class WeightSettingsTest {

    @Rule
    public ActivityScenarioRule<EditWeightsSettings> tActivityRule = new ActivityScenarioRule<>(
            EditWeightsSettings.class);

    private void replaceTextHelper(int viewId, String stringToBeSet) {
        // to reduce flaky test, https://stackoverflow.com/a/53430379/1326678
        onView(withId(viewId)).perform(clearText(), replaceText(stringToBeSet), closeSoftKeyboard());
    }

    @Test
    public void salaryWeightTestOutOfBounds() throws InterruptedException {
        replaceTextHelper(R.id.yearly_salary_weight, "");
        replaceTextHelper(R.id.yearly_bonus_weight, "");
        replaceTextHelper(R.id.stock_weight, "");
        replaceTextHelper(R.id.home_weight, "");
        replaceTextHelper(R.id.holiday_weight, "");
        replaceTextHelper(R.id.stipend_weight, "");

        // Pressing save button is necessary to throw the errors
        onView(withId(R.id.saveButtonID)).perform(click());

        onView(withId(R.id.yearly_salary_weight)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.yearly_bonus_weight)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.stock_weight)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.home_weight)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.holiday_weight)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.stipend_weight)).check(matches(hasErrorText("Can't be an empty value!")));
    }

}
