package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.ItemClickSupport;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    private int mPagePosition;
    List<Neighbour> mNeighbours;
    private RecyclerView.Adapter mAdapter;
    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(int pagePosition) {
        NeighbourFragment frag = new NeighbourFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pagePosition", pagePosition);
        frag.setArguments(bundle);
        return frag;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        assert getArguments() != null;
        mPagePosition = getArguments().getInt("pagePosition", -1);
        Log.i("test", "onCreate: "+mPagePosition);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));

        initList();
        //use method to configure OnClickRecyclerView
        this.configureOnClickRecyclerView();
        return view;
    }
    /**
     * Init the List of neighbours
     */
    private void initList() {

        if (mPagePosition == 0) {
            mNeighbours = mApiService.getNeighbours();
            mRecyclerView.setId(R.id.list_neighbours);
        } else {
            mNeighbours = mApiService.getFavorites();
            mRecyclerView.setId(R.id.list_favorites);
        }
        mAdapter = new MyNeighbourRecyclerViewAdapter(mNeighbours);
        mRecyclerView.setAdapter(mAdapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onPause() {
        super.onPause();
        AddFavoriteEvent stickyEvent = EventBus.getDefault().getStickyEvent(AddFavoriteEvent.class);
        if (stickyEvent != null) {
            // "Consume" the sticky event
            EventBus.getDefault().removeStickyEvent(stickyEvent);
        }
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {

        boolean isNeighbourIsFav = event.neighbour.isFavorite();
        Neighbour neighbourInFavList = searchNeighbourInFavList(event.neighbour.getName(), event.neighbour.getAddress());

        if (isNeighbourIsFav) {
            Log.i("test", "onDeleteNeighbour: "+mPagePosition);
            mApiService.deleteFavorite(neighbourInFavList);
            event.neighbour.setFavorite(false);
        } else  {
            mApiService.deleteNeighbour(event.neighbour);
        }
        mAdapter.notifyDataSetChanged();
    }


    /**
     * Fired if the user clicks on favorite button
     *
     * @param event
     */
    @Subscribe(sticky = true)
    public void onAddFavorite(AddFavoriteEvent event) {
        if (!event.neighbour.isFavorite()) {
            Neighbour favoriteNeighbour = createFavoriteNeighbour(event.neighbour);
            mApiService.addFavorite(favoriteNeighbour);
            event.neighbour.setFavorite(true);
            reAttributeFavoriteId();
        }
        mAdapter.notifyDataSetChanged();
    }
    //re attribute fav list id in order to have two list with different id
    private void reAttributeFavoriteId() {
        for (int i = 0; i < mApiService.getFavorites().size(); i++) {
            mApiService.getFavorites().get(i).setId(i);
        }
    }
    //search neighbour in fav list with a name and address, if two matches, return the neighbour of fav list
    private Neighbour searchNeighbourInFavList(String name, String address) {

        Neighbour neighbourToSend = null;
        for (int i = 0; i < mApiService.getFavorites().size(); i++) {
            String nameToCompare = mApiService.getFavorites().get(i).getName();
            String addressToCompare = mApiService.getFavorites().get(i).getAddress();
            if (address.equals(addressToCompare) && name.equals(nameToCompare)) {
                neighbourToSend = mApiService.getFavorites().get(i);
            }
        }
        return neighbourToSend;
    }
    //create a new Neighbour instance to add in fav list
    public Neighbour createFavoriteNeighbour(Neighbour favorite) {
        return new Neighbour(favorite.getId(), favorite.getName(), favorite.getAvatarUrl(), favorite.getAddress(), favorite.getPhone(), favorite.getFacebook(), favorite.getAboutMeTxt(), true);
    }
    //configure click on recycler view
    public void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour_list)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    Bundle bundle = new Bundle();
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("pagePosition", mPagePosition);
                    bundle.putInt("position", position);
                    Intent intent = new Intent(getContext(), NeighbourActivityDetail.class);
                    intent.putExtras(bundle);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                });
    }
}