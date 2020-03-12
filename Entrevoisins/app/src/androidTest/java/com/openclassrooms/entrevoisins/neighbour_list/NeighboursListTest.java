
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewInteraction;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;


/**
 * Test class for list of neighbours
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private final int FAV_COUNT = 2;

    private ListNeighbourActivity mActivity;
    private ViewInteraction listFavorite = onView(withId(R.id.list_favorites));
    private ViewInteraction listNeighbour = onView(withId(R.id.list_neighbours));


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        listNeighbour.check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myFavoritesList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        listFavorite.check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        //
        listNeighbour.check(withItemCount(ITEMS_COUNT));
        listFavorite.check(withItemCount(FAV_COUNT));

        //Given : We remove the element at position 2 When perform a click on a delete icon
        listNeighbour
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()))
                // Then : the number of element is 11
                .check(withItemCount(ITEMS_COUNT - 1));
        //and should be delete of favorite too
        listFavorite
                .check(withItemCount(FAV_COUNT - 1));
    }

    @Test
    public void Neighbour_click_should_open_NeighbourActivityDetail_on_same_neighbour() {
        // perform click on the neighbour at index 1
        listNeighbour
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        // check if the avatar of detailed list is displayed, if true so activity is well launched
        onView(withId(R.id.item_list_avatar))
                .check(matches(isDisplayed()));
        // ensure that the name in the textView is what we expected
        onView(withId(R.id.item_list_name))
                .check(matches(withText("Jack")));
        //ensure that return button work well
        onView(withId(R.id.item_list_return_button))
                .perform(click());
    }

    @Test
    public void Favorite_list_should_only_contained_favorite_neighbours() {
        //delete the first and second neighbour of the list
        listNeighbour
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        //ensure fav list is empty
        listFavorite
                .check(withItemCount(0));
        //click on third neighbour
        listNeighbour
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        //and add it to fav list
        onView(withId(R.id.item_list_favorite_button))
                .perform(click());
        onView(withId(R.id.item_list_return_button))
                .perform(click());
        //check if fav list contains the good neighbour
        listFavorite
                .check(withItemCount(1));
        //swipe left
        onView(withId(R.id.container))
                .perform(swipeLeft());
        //ensure we' ve had the good neighbour
        listFavorite
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.item_list_name))
                .check(matches(withText("Elodie")));
    }
}