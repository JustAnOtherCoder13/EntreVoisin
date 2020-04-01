
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;


/**
 * Test class for list of neighbours
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAV_COUNT = 2;

    private ListNeighbourActivity mActivity;
    //private ViewInteraction listFavorite ;
    //private ViewInteraction listNeighbour ;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        //listFavorite = onView(withId(R.id.list_favorites));
        //listNeighbour = onView(withId(R.id.list_neighbours));
    }

    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myFavoritesList_shouldNotBeEmpty() throws InterruptedException {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.container))
                .perform(swipeLeft());
        Thread.sleep(100);
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() throws InterruptedException {
        //
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(ITEMS_COUNT));

        //Given : We remove the element at position 2 When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()))
                // Then : the number of element is 11
                .check(withItemCount(ITEMS_COUNT - 1));
        //and should be delete of favorite too
        //swipe left
       onView(withId(R.id.container))
                .perform(swipeLeft());
        Thread.sleep(100);
         onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(FAV_COUNT - 1));
    }

    @Test
    public void myFavoriteList_deleteAction_shouldRemoveItem_onlyInFavList() throws InterruptedException {

        //swipe left
        onView(withId(R.id.container))
                .perform(swipeLeft());
        Thread.sleep(100);
        //perform click on deleteButton on first favorite
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()))
                .check(withItemCount(0));
        //ensure neighbourList is always full
        //swipe left
        onView(withId(R.id.container))
                .perform(swipeRight());
        Thread.sleep(100);
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void Neighbour_click_shouldOpen_NeighbourActivityDetail_onSameNeighbour() {
        // perform click on the neighbour at index 1
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        // check if the avatar of detailed list is displayed, if true so activity is well launched
        onView(withId(R.id.item_list_avatar))
                .check(matches(isDisplayed()));
        // ensure that the name in the textView is what we expected
        onView(withId(R.id.item_list_name))
                .check(matches(withText("Chloé")));
        //ensure that return button work well
        onView(withId(R.id.item_list_return_button))
                .perform(click());
    }

    @Test
    public void Favorite_list_should_onlyContained_favoriteNeighbours() throws InterruptedException {

        //swipe left
        onView(withId(R.id.container))
                .perform(swipeLeft());
        Thread.sleep(100);

        //ensure fav list is empty
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(0));

        onView(withId(R.id.container))
                .perform(swipeRight());
        Thread.sleep(100);
        //click on third neighbour
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        //and add it to fav list
        onView(withId(R.id.item_list_favorite_button))
                .perform(click());
        onView(withId(R.id.item_list_return_button))
                .perform(click());
        //swipe left
        onView(withId(R.id.container))
                .perform(swipeLeft());
        Thread.sleep(100);
        //check if fav list contains the good neighbour
        onView(allOf(withId(R.id.list_neighbours),isDisplayed()))
                .check(withItemCount(1))

        //ensure we' ve had the good neighbour
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.item_list_name))
                .check(matches(withText("Vincent")));
    }
}