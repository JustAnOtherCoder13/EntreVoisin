package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import butterknife.BindView;


public class NeighbourActivityDetail extends AppCompatActivity {


    @BindView(R.id.item_list_avatar)
    ImageView mItemListAvatar;
    @BindView(R.id.item_list_name)
    TextView mItemListName;
    @BindView(R.id.item_list_name_detail)
    TextView mItemListNameDetail;
    @BindView(R.id.item_list_name_detail_localisation_txt)
    TextView mItemListNameDetailLocalisation;
    @BindView(R.id.item_list_name_detail_phone_txt)
    TextView mItemListNameDetailPhone;
    @BindView(R.id.item_list_name_detail_mail_txt)
    TextView mItemListNameDetailMail;
    @BindView(R.id.item_list_presentation_about_me_txt)
    TextView mItemListPresentationAboutMe;

    @BindView(R.id.item_list_return_button)
    ImageButton mReturnButton;
    @BindView(R.id.item_list_favorite_button)
    ImageButton mFavoriteButton;

    private static final String KEY_POSITION = "position";
    private NeighbourApiService mApiService;
    private Neighbour neighbour;
    private List<Neighbour> mNeighbours;
    private int position;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        int position = arguments.getInt(KEY_POSITION, -1);
        mApiService = DI.getNeighbourApiService();
        neighbour = getUser(position);
        Log.i("test", "onCreate: " + neighbour.getName());
    }


    private Neighbour getUser(int position) {
        mNeighbours = mApiService.getNeighbours();
        neighbour = mNeighbours.get(position);
        return neighbour;
    }



}
