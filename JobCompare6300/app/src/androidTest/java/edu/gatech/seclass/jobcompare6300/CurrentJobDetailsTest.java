package edu.gatech.seclass.jobcompare6300;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CurrentJobDetailsTest {

    @Rule
    public ActivityScenarioRule<EnterCurrentJobDetails> tActivityRule = new ActivityScenarioRule<>(
            EnterCurrentJobDetails.class);

    private void replaceTextHelper(int viewId, String stringToBeSet) {
        // to reduce flaky test, https://stackoverflow.com/a/53430379/1326678
        onView(withId(viewId)).perform(clearText(), replaceText(stringToBeSet), closeSoftKeyboard());
    }

    @Test
    public void errorTest() {
        replaceTextHelper(R.id.TitleTextID, "");
        replaceTextHelper(R.id.CompanyTextID, "");
        replaceTextHelper(R.id.CityTextID, "");
        replaceTextHelper(R.id.StateTextID, "");
        replaceTextHelper(R.id.CostOfLivingNumberID, "");
        replaceTextHelper(R.id.YearlySalaryDecimalID, "");
        replaceTextHelper(R.id.YearlyBonusDecimalID, "");
        replaceTextHelper(R.id.StockOptionSharesDecimalID, "");
        //replaceTextHelper(R.id.HomeBuyingFundDecimalID, "");
        //replaceTextHelper(R.id.PersonalChoiceHolidaysNumberID, "");
        //replaceTextHelper(R.id.MonthlyInternetStipendNumberID, "");
        onView(withId(R.id.saveButtonID)).perform(click());
        onView(withId(R.id.TitleTextID)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.CompanyTextID)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.CityTextID)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.StateTextID)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.CostOfLivingNumberID)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.YearlySalaryDecimalID)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.YearlyBonusDecimalID)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.StockOptionSharesDecimalID)).check(matches(hasErrorText("Can't be an empty value!")));
        //onView(withId(R.id.HomeBuyingFundDecimalID)).check(matches(hasErrorText("Can't be an empty value!")));
        //onView(withId(R.id.PersonalChoiceHolidaysNumberID)).check(matches(hasErrorText("Can't be an empty value!")));
        //onView(withId(R.id.MonthlyInternetStipendNumberID)).check(matches(hasErrorText("Can't be an empty value!")));
    }

    @Test
    public void homeBuyingFundCheck() {
        replaceTextHelper(R.id.TitleTextID, "Software Engineer");
        replaceTextHelper(R.id.CompanyTextID, "Amazon");
        replaceTextHelper(R.id.CityTextID, "New York City");
        replaceTextHelper(R.id.StateTextID, "New York");
        replaceTextHelper(R.id.CostOfLivingNumberID, "900");
        replaceTextHelper(R.id.YearlySalaryDecimalID, "145000");
        replaceTextHelper(R.id.YearlyBonusDecimalID, "10000");
        replaceTextHelper(R.id.StockOptionSharesDecimalID, "230");
        replaceTextHelper(R.id.HomeBuyingFundDecimalID, "23200"); // 16%
        replaceTextHelper(R.id.PersonalChoiceHolidaysNumberID, "18");
        replaceTextHelper(R.id.MonthlyInternetStipendNumberID, "72");
        onView(withId(R.id.saveButtonID)).perform(click());
        onView(withId(R.id.HomeBuyingFundDecimalID)).check(matches(hasErrorText("Needs to be a value between 0 and 15% of the salary")));
    }

    @Test
    public void personalHolidaysCheck() {
        replaceTextHelper(R.id.TitleTextID, "Boston Celtics Professional Basketball Player");
        replaceTextHelper(R.id.CompanyTextID, "NBA");
        replaceTextHelper(R.id.CityTextID, "Boston");
        replaceTextHelper(R.id.StateTextID, "Massachusetts");
        replaceTextHelper(R.id.CostOfLivingNumberID, "800");
        replaceTextHelper(R.id.YearlySalaryDecimalID, "30000000");
        replaceTextHelper(R.id.YearlyBonusDecimalID, "500000");
        replaceTextHelper(R.id.StockOptionSharesDecimalID, "20");
        replaceTextHelper(R.id.HomeBuyingFundDecimalID, "12");
        replaceTextHelper(R.id.PersonalChoiceHolidaysNumberID, "21");
        replaceTextHelper(R.id.MonthlyInternetStipendNumberID, "72");
        onView(withId(R.id.saveButtonID)).perform(click());
        onView(withId(R.id.PersonalChoiceHolidaysNumberID)).check(matches(hasErrorText("Needs to be a value between 0 and 20")));
    }

    @Test
    public void monthlyInternetStipendCheck() {
        replaceTextHelper(R.id.TitleTextID, "Janitor");
        replaceTextHelper(R.id.CompanyTextID, "JPMorgan");
        replaceTextHelper(R.id.CityTextID, "Austin");
        replaceTextHelper(R.id.StateTextID, "Texas");
        replaceTextHelper(R.id.CostOfLivingNumberID, "300");
        replaceTextHelper(R.id.YearlySalaryDecimalID, "45000");
        replaceTextHelper(R.id.YearlyBonusDecimalID, "2000");
        replaceTextHelper(R.id.StockOptionSharesDecimalID, "15");
        replaceTextHelper(R.id.HomeBuyingFundDecimalID, "10");
        replaceTextHelper(R.id.PersonalChoiceHolidaysNumberID, "14");
        replaceTextHelper(R.id.MonthlyInternetStipendNumberID, "75.02");
        onView(withId(R.id.saveButtonID)).perform(click());
        onView(withId(R.id.MonthlyInternetStipendNumberID)).check(matches(hasErrorText("Needs to be a value between $0.00 and $75.00")));
    }
}