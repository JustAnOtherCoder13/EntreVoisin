package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.content.Context;
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
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.ItemClickSupport;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public  class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private  List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;
   public static final String KEY_POSITION = "position";
   private int position;




    /**
     * Create and return a new instance
     *
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();

        return fragment;
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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        Bundle arguments = new Bundle();


        arguments.putInt(KEY_POSITION,position);
        NeighbourFragment.newInstance().setArguments(arguments);
        if (arguments != null) {
            int position = arguments.getInt(KEY_POSITION, -1);
            Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);
        }

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

    public Neighbour getUser(int position){
        return mNeighbours.get(position);

    }
    //configure click on recycler view

    public void configureOnClickRecyclerView() {

        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_neighbour_list)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                       /* Bundle arguments = new Bundle();
                        arguments.putInt(KEY_POSITION,position);
                        NeighbourFragment.newInstance().setArguments(arguments);*/



                      //  if (position >= 0){
                         //  arguments = NeighbourFragment.this.getArguments();
                            //if (arguments != null){
                            //    position = arguments.getInt(NeighbourFragment.KEY_POSITION, -1);

                           // }
                            //Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+arguments);
                           // Toast.makeText(getContext(), "You clicked on user : " + this.arguments, Toast.LENGTH_SHORT).show();

                       // }

                        //- Show result in a Toast
                       }
                });


    }



    }






