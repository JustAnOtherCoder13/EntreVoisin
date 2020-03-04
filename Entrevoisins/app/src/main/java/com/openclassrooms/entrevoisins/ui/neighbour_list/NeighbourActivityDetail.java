package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

        //U.I dynamics views.
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
        //U.I Images buttons.
    @BindView(R.id.item_list_return_button)
    ImageButton mReturnButton;
    @BindView(R.id.item_list_favorite_button)
    ImageButton mFavoriteButton;

    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;
    private List<Neighbour> mNeighbourList;
    private List<Neighbour> mFavoriteList;
    private int mPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

             //create bundle and get position and page number.
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        mPosition = arguments.getInt("position", -1);
        int page = arguments.getInt("pagePosition", -1);
            //initialize apiService and neighbour
        mApiService = DI.getNeighbourApiService();
        mNeighbour = getUser(mPosition, page);
            //bind and fill the views
        ButterKnife.bind(this);
        mItemListName.setText(mNeighbour.getName());
        mItemListNameDetail.setText(mNeighbour.getName());
        mItemListNameDetailLocalisation.setText(mNeighbour.getAddress());
        mItemListNameDetailMail.setText(mNeighbour.getFacebook().concat(mNeighbour.getName().toLowerCase()));
            //use random to create fake phone number.
        Random random = new Random();
        mItemListNameDetailPhone.setText(mNeighbour.getPhone().concat(String.valueOf(random.nextInt(100))).concat(" ").concat(String.valueOf(random.nextInt(90) + 10)).concat(" ").concat(String.valueOf(random.nextInt(90) + 10)).concat(" ").concat(String.valueOf(random.nextInt(90) + 10)));
            //comment getAboutMeTxt cause is empty and lorem ipsum is in the textView.
        //mItemListPresentationAboutMe.setText(neighbour.getAboutMeTxt());
        Glide.with(mItemListAvatar.getContext())
                .load(mNeighbour.getAvatarUrl())
                .fitCenter()
                .into(mItemListAvatar);
            //use listener to close activity by clicking return button
        mReturnButton.setOnClickListener(v -> backToMain());
            //use a listener to add a favorite by clicking the favorite button.
        mFavoriteButton.setOnClickListener(view -> addFavorite(mPosition));
    }

        //method to catch the neighbour clicked and the page where the click was donne
    private Neighbour getUser(int position, int page) {
        if (page == 0) {
            mNeighbourList = mApiService.getNeighbours();
            mNeighbour = mNeighbourList.get(position);
        } else {
            mFavoriteList = mApiService.getFavorite();
            mNeighbour = mFavoriteList.get(position);
        }
        return mNeighbour;
    }
        //method to close the activity
    private void backToMain() { this.finish(); }

        //method to addFavorite
    private void addFavorite(int position) {
        mFavoriteList = mApiService.getFavorite();
        mNeighbourList = mApiService.getNeighbours();
        mNeighbour = mNeighbourList.get(position);
        mFavoriteList.add(mNeighbour);
    }
}
