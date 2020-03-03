package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    public static final String KEY_POSITION = "position";
    private boolean myBol;
    private int mPagePosition;
    List<Neighbour> mNeighbours;


    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(int mPagePosition) {
        NeighbourFragment frag = new NeighbourFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("mPagePosition", mPagePosition);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        assert getArguments() != null;
        mPagePosition = getArguments().getInt("mPagePosition", 0);
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
        if (mPagePosition == 0) {
            mApiService.deleteNeighbour(event.neighbour);
            initList();
        }
        else {
            mApiService.getFavorite().remove(event.neighbour);
            initList();
        }
    }

    @Subscribe
    public void onAddFavoriteEvent (AddFavoriteEvent event){

        mApiService.getFavorite().add(event.neighbour);
        initList();
    }


    //configure click on recycler view

    public void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour_list)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    Bundle bundle = new Bundle();
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("mPagePosition",mPagePosition);
                    bundle.putInt(KEY_POSITION, position);
                    Intent intent = new Intent(getContext(), NeighbourActivityDetail.class);
                    intent.putExtras(bundle);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                });
    }
}






