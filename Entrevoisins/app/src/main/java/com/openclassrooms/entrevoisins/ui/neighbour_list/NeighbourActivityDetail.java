package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


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
    private Neighbour mNeighbour;
    private List<Neighbour> mNeighbourList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Random random = new Random();
        setContentView(R.layout.activity_neighbour_detail);
                //create bundle and get position
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        int position = arguments.getInt(KEY_POSITION, -1);
                //initialize apiService and neighbour
        mApiService = DI.getNeighbourApiService();
        mNeighbour = getUser(position);
                //bind and fill the view
        ButterKnife.bind(this);
        mItemListName.setText(mNeighbour.getName());
        mItemListNameDetail.setText(mNeighbour.getName());
        mItemListNameDetailLocalisation.setText(mNeighbour.getAddress());
        mItemListNameDetailMail.setText(mNeighbour.getFacebook().concat(mNeighbour.getName().toLowerCase()));
        mItemListNameDetailPhone.setText(mNeighbour.getPhone().concat(String.valueOf(random.nextInt(100))).concat(" ").concat(String.valueOf(random.nextInt(90)+10)).concat(" ").concat(String.valueOf(random.nextInt(90)+10)).concat(" ").concat(String.valueOf(random.nextInt(90)+10)));
                //mItemListPresentationAboutMe.setText(neighbour.getAboutMeTxt());
        Glide.with(mItemListAvatar.getContext())
                .load(mNeighbour.getAvatarUrl())
                .fitCenter()
                .into(mItemListAvatar);
                        //use listener to close activity by clicking return button
        mReturnButton.setOnClickListener(v -> backToMain());
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFavorite(position);

            }
        });
    }
            //method to catch the neighbour clicked
    private Neighbour getUser(int position) {
        mNeighbourList = mApiService.getNeighbours();
        mNeighbour = mNeighbourList.get(position);
        return mNeighbour;
    }

    private void backToMain(){
        this.finish();
    };

    private void addFavorite(int position){
        List<Neighbour> favList = mApiService.getFavorite();
        mNeighbourList = mApiService.getNeighbours();
        mNeighbour = mNeighbourList.get(position);
        favList.add(mNeighbour);
    }

}
