package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.ItemClickSupport;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public  class NeighbourFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.NeighbourPosition {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
    private String name;

    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance(/*String name*/) {
        NeighbourFragment fragment = new NeighbourFragment();
        //Bundle arguments = new Bundle();
       // arguments.putString("name", name);
        //fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
        //Bundle arguments = getArguments();
       // name = (arguments != null)? arguments.getString("name", "empty"): "empty";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();

        //use method to configure OnClickRecyclerView
        this.configureOnClickRecyclerView();
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mNeighbours = mApiService.getNeighbours();

        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));
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

    //implement the interface method getUser
    @Override
    public  Neighbour getUser(int position) {

        return mNeighbours.get(position);


    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

    //configure click on recycler view

    private void configureOnClickRecyclerView() {

        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour_list)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Neighbour neighbour = getUser(position);

                       /* Bundle bundle = new Bundle();
                        String myMessage = "Stack Overflow is cool!";
                        bundle.putString("message", myMessage );
                        NeighbourFragment fragInfo = new NeighbourFragment();
                        fragInfo.setArguments(bundle);
                        fragmentManager.beginTransaction()
                        transaction.replace(R.id., fragInfo);
                        transaction.commit();*/

                        //- Show result in a Toast
                        Toast.makeText(getContext(), "You clicked on user : " + neighbour.getName(), Toast.LENGTH_SHORT).show();
                       }
                });

    }

    //public  Boolean isItClicked = this.configureOnClickRecyclerView(this);

    }






