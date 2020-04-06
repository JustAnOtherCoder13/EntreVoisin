
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
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {
    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int FAV_COUNT = 0;

    private ListNeighbourActivity mActivity;
    private ViewInteraction  listNeighbours = onView(withId(R.id.list_neighbours));
    private ViewInteraction listFavorites = onView(withId(R.id.list_favorites));

    @Rule
  public ActivityTestRule<ListNeighbourActivity> mActivityRule =
          new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() { mActivity = mActivityRule.getActivity(); }
    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void a_myNeighboursList_shouldNotBeEmpty() {
        // Ensure Neighbour list show at least one item
        listNeighbours.check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void b_myFavoritesList_shouldBeEmpty() {
        // Ensure Favorites List is empty
        listFavorites.check(withItemCount(FAV_COUNT));
    }
    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void c_myNeighboursList_deleteAction_shouldRemoveItem() {
        //Add a neighbour to favorites
        listNeighbours
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        addToFavListAndPressBackBtn();
        listFavorites.check(withItemCount(1));

        //Given : We remove the element at position 1 When perform a click on a delete icon
        listNeighbours
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()))
                // Then : the number of element is 11
                .check(withItemCount(ITEMS_COUNT - 1));
        //and should be delete of favorite too
      listFavorites.check(withItemCount(0));
    }

    @Test
    public void d_myFavoriteList_deleteAction_shouldRemoveItem() {

        listNeighbours
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        addToFavListAndPressBackBtn();
        listFavorites.check(withItemCount(1));
        //swipe left
        onView(withId(R.id.container))
                .perform(swipeLeft());
        //perform click on deleteButton on first favorite
        listFavorites
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()))
                .check(withItemCount(0));
        //ensure neighbourList have lost one more neighbour
       listNeighbours.check(withItemCount(ITEMS_COUNT - 2));
    }

    @Test
    public void e_Neighbour_click_shouldOpen_NeighbourActivityDetail_onSameNeighbour() {
        // perform click on the neighbour at position 2
       listNeighbours.perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        // check if the avatar of detailed list is displayed, if true so activity is well launched
        onView(withId(R.id.item_list_avatar))
                .check(matches(isDisplayed()));
        // ensure that the name in the textView is what we expected
        onView(withId(R.id.item_list_name))
                .check(matches(withText("Vincent")));
    }

    @Test
    public void f_Favorite_list_should_onlyContained_favoriteNeighbours(){

        //ensure fav list is empty
        listFavorites.check(withItemCount(0));
        //click on third neighbour
        listNeighbours.perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        //and add it to fav list
        addToFavListAndPressBackBtn();
        //swipe left
        onView(withId(R.id.container))
                .perform(swipeLeft());
        //check if fav list contains a neighbour
        listFavorites.check(withItemCount(1))
        //ensure we' ve had the good neighbour
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.item_list_name))
                .check(matches(withText("Elodie")));
    }
    private void addToFavListAndPressBackBtn() {
        onView(withId(R.id.item_list_favorite_button))
                .perform(click());
        onView(withId(R.id.item_list_return_button))
                .perform(click());
    }
}