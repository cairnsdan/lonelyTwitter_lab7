package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ListView;

import com.robotium.solo.Solo;

public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    /* Runs everytime we run the test */
    public void setUp() throws Exception {
        super.setUp();
        // Method does not call super method - Fix #5
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testAddingTweetToTheList() {
        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
        solo.enterText((EditText) solo.getView(R.id.body), "Test tweet");
        solo.clickOnButton("Save");

        solo.enterText((EditText) solo.getView(R.id.body), "");

        assertTrue(solo.searchText("Test tweet"));
        solo.clickOnButton("Clear");
        assertFalse(solo.searchText("Test tweet"));
    }

    public void testClickTweetList() {
        LonelyTwitterActivity activity = (LonelyTwitterActivity) solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);

        solo.clickOnButton("Clear");

        solo.enterText((EditText) solo.getView(R.id.body), "Tweet test");
        solo.clickOnButton("Save");
        solo.waitForText("Tweet test");

        final ListView oldTweetList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetList.getItemAtPosition(0);

        assertEquals("Tweet test", tweet.getMessage());


        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong activity", EditTweetActivity.class);
        assertTrue(solo.waitForText("TextView"));

        solo.goBack();
        solo.assertCurrentActivity("Wrong activity", LonelyTwitterActivity.class);

    }

    public void testEditTweetActivity() {
        LonelyTwitterActivity activity = (LonelyTwitterActivity) solo.getCurrentActivity();
        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);

        solo.clickOnButton("Clear");

        solo.enterText((EditText) solo.getView(R.id.body), "Tweet test");
        solo.clickOnButton("Save");
        solo.waitForText("Tweet test");

        final ListView oldTweetList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetList.getItemAtPosition(0);

        assertEquals("Tweet test", tweet.getMessage());


        solo.clickInList(0);
        solo.assertCurrentActivity("Wrong activity", EditTweetActivity.class);
        assertTrue(solo.searchText("Tweet task"));

        solo.goBack();
        solo.assertCurrentActivity("Wrong activity", LonelyTwitterActivity.class);

    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        solo.finishOpenedActivities();
    }
}