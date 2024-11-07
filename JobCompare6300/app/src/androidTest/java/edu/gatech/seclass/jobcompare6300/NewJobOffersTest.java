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
public class NewJobOffersTest {

    @Rule
    public ActivityScenarioRule<EnterNewJobOffers> tActivityRule = new ActivityScenarioRule<>(
            EnterNewJobOffers.class);

    private void replaceTextHelper(int viewId, String stringToBeSet) {
        // to reduce flaky test, https://stackoverflow.com/a/53430379/1326678
        onView(withId(viewId)).perform(clearText(), replaceText(stringToBeSet), closeSoftKeyboard());
    }

    @Test
    public void errorTest1() {
        replaceTextHelper(R.id.OfferTitleEntry, "");
        replaceTextHelper(R.id.OfferCompanyEntry, "");
        replaceTextHelper(R.id.OfferCityEntry, "");
        replaceTextHelper(R.id.OfferStateEntry, "");
        replaceTextHelper(R.id.OfferLivingEntry, "");
        replaceTextHelper(R.id.OfferSalaryEntry, "");
        replaceTextHelper(R.id.OfferBonusEntry, "");
        replaceTextHelper(R.id.OfferStockEntry, "");
        //replaceTextHelper(R.id.HomeBuyingFundDecimalID, "");
        //replaceTextHelper(R.id.PersonalChoiceHolidaysNumberID, "");
        //replaceTextHelper(R.id.MonthlyInternetStipendNumberID, "");
        onView(withId(R.id.navigate_to_page_transfer)).perform(click());
        onView(withId(R.id.OfferTitleEntry)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.OfferCompanyEntry)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.OfferCityEntry)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.OfferStateEntry)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.OfferLivingEntry)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.OfferSalaryEntry)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.OfferBonusEntry)).check(matches(hasErrorText("Can't be an empty value!")));
        onView(withId(R.id.OfferStockEntry)).check(matches(hasErrorText("Can't be an empty value!")));
        //onView(withId(R.id.HomeBuyingFundDecimalID)).check(matches(hasErrorText("Can't be an empty value!")));
        //onView(withId(R.id.PersonalChoiceHolidaysNumberID)).check(matches(hasErrorText("Can't be an empty value!")));
        //onView(withId(R.id.MonthlyInternetStipendNumberID)).check(matches(hasErrorText("Can't be an empty value!")));
    }

    @Test
    public void homeBuyingFundCheck1() {
        replaceTextHelper(R.id.OfferTitleEntry, "Software Engineer II");
        replaceTextHelper(R.id.OfferCompanyEntry, "Amazon");
        replaceTextHelper(R.id.OfferCityEntry, "New York City");
        replaceTextHelper(R.id.OfferStateEntry, "New York");
        replaceTextHelper(R.id.OfferLivingEntry, "930");
        replaceTextHelper(R.id.OfferSalaryEntry, "175000");
        replaceTextHelper(R.id.OfferBonusEntry, "12000");
        replaceTextHelper(R.id.OfferStockEntry, "230");
        replaceTextHelper(R.id.offerHouseEntry, "28000"); //16% -> this should fail
        replaceTextHelper(R.id.OfferholidaysEntry, "18");
        replaceTextHelper(R.id.OfferInternetEntry, "72");
        onView(withId(R.id.navigate_to_page_transfer)).perform(click());
        onView(withId(R.id.offerHouseEntry)).check(matches(hasErrorText("Needs to be a value between 0 and 15% of the salary")));
    }

    @Test
    public void personalHolidaysCheck1() {
        replaceTextHelper(R.id.OfferTitleEntry, "New York Yankees Professional Baseball Player");
        replaceTextHelper(R.id.OfferCompanyEntry, "MLB");
        replaceTextHelper(R.id.OfferCityEntry, "New York City");
        replaceTextHelper(R.id.OfferStateEntry, "New York");
        replaceTextHelper(R.id.OfferLivingEntry, "930");
        replaceTextHelper(R.id.OfferSalaryEntry, "2000000");
        replaceTextHelper(R.id.OfferBonusEntry, "100000");
        replaceTextHelper(R.id.OfferStockEntry, "4");
        replaceTextHelper(R.id.offerHouseEntry, "12");
        replaceTextHelper(R.id.OfferholidaysEntry, "30"); // should fail
        replaceTextHelper(R.id.OfferInternetEntry, "65");
        onView(withId(R.id.navigate_to_page_transfer)).perform(click());
        onView(withId(R.id.OfferholidaysEntry)).check(matches(hasErrorText("Needs to be a value between 0 and 20")));
    }

    @Test
    public void monthlyInternetStipendCheck1() {
        replaceTextHelper(R.id.OfferTitleEntry, "Investment Banker");
        replaceTextHelper(R.id.OfferCompanyEntry, "Goldman Sachs");
        replaceTextHelper(R.id.OfferCityEntry, "Los Angeles");
        replaceTextHelper(R.id.OfferStateEntry, "California");
        replaceTextHelper(R.id.OfferLivingEntry, "1000");
        replaceTextHelper(R.id.OfferSalaryEntry, "150000");
        replaceTextHelper(R.id.OfferBonusEntry, "75000");
        replaceTextHelper(R.id.OfferStockEntry, "45");
        replaceTextHelper(R.id.offerHouseEntry, "15");
        replaceTextHelper(R.id.OfferholidaysEntry, "12");
        replaceTextHelper(R.id.OfferInternetEntry, "90.72");
        onView(withId(R.id.navigate_to_page_transfer)).perform(click());
        onView(withId(R.id.OfferInternetEntry)).check(matches(hasErrorText("Needs to be a value between $0.00 and $75.00")));
    }
}