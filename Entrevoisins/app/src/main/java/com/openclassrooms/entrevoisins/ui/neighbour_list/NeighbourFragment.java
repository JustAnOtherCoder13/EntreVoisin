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
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.ItemClickSupport;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Objects;
import java.util.Observable;


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private RecyclerView mRecyclerView;
    private int mPagePosition;
    List<Neighbour> mNeighbours;


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
        assert getArguments() != null;
        mPagePosition = getArguments().getInt("pagePosition", -1);
        if (mPagePosition == 0) {
            mNeighbours = mApiService.getNeighbours();
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
        } else {
            mNeighbours = mApiService.getFavorite();
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
        }
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

        initList();
        assert getArguments() != null;
        mPagePosition = getArguments().getInt("pagePosition", -1);
        Log.i("test", "onDeleteNeighbour: page position ="+mPagePosition);
        // var to compare
        int eventId = event.neighbour.getId();
        int idNeighbourCompare = mApiService.getNeighbours().get(event.position).getId();
        String eventName = event.neighbour.getName();
        String neighbourNameCompare = mApiService.getNeighbours().get(event.position).getName();
        Log.i("test", "onDeleteNeighbour: before event neighbour list =" + eventName + " " + eventId + " " + idNeighbourCompare + " " + neighbourNameCompare+" "+event.position);

        if (mPagePosition == 1 && event.position < mApiService.getFavorite().size()) {
            int idFavoriteCompare = mApiService.getFavorite().get(event.position).getId();
            String favoriteNameCompare = mApiService.getFavorite().get(event.position).getName();
            Log.i("test", "onDeleteNeighbour: if page position 1 event + favorite =" + eventName + " " + eventId + " " + idFavoriteCompare + " " + favoriteNameCompare);
            if (eventId == idFavoriteCompare && eventName==favoriteNameCompare) {
                mApiService.getFavorite().remove(event.neighbour);
                reAttributeFavoriteId();
            }
            return;
        }
        if (mPagePosition == 0 && event.position < mApiService.getNeighbours().size() && eventId == idNeighbourCompare && eventName.equals(neighbourNameCompare) ) {
                mApiService.deleteNeighbour(event.neighbour);
            Log.i("test", "onDeleteNeighbour: else if page position 0 event neighbour list =" + eventName + " " + eventId + " " + idNeighbourCompare + " " + neighbourNameCompare);
        }


    }

    /**
     * Fired if the user clicks on favorite button
     *
     * @param event
     */
    @Subscribe(sticky = true)
    public void onAddFavorite(AddFavoriteEvent event) {
        boolean myBol = !mApiService.getFavorite().contains(event.neighbour);
        Log.i("test", "onAddFavorite: event neighbour"+myBol+" "+event.neighbour.getName());
        if (!mApiService.getFavorite().contains(event.neighbour)) {
            mApiService.addFavorite(event.neighbour);
            reAttributeFavoriteId();
        }
        initList();

    }
    //attribute id by their index
    public void reAttributeFavoriteId(){
        for (int i = 0; i < mApiService.getFavorite().size(); i++) {
            mApiService.getFavorite().get(i).setId(i);
            Log.i("test", "onAddFavorite: name + id ="+mApiService.getFavorite().get(i).getName()+" "+mApiService.getFavorite().get(i).getId());
        }
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






