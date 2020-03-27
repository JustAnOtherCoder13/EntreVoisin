package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavoriteEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

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
    FloatingActionButton mFavoriteButton;

    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        //create bundle and get position and page
        Bundle arguments = getIntent().getExtras();
        assert arguments != null;
        int position = arguments.getInt("position", -1);
        int page = arguments.getInt("pagePosition", -1);
        //initialize apiService and neighbour
        mApiService = DI.getNeighbourApiService();
        mNeighbour = getUser(position, page);
        //bind and fill the view
        ButterKnife.bind(this);
        initView();
        //use listener to close activity by clicking return button
        mReturnButton.setOnClickListener(v -> backToMain());
        //postSticky to get the post in memory since it is unregistered manually
        mFavoriteButton.setOnClickListener(v -> EventBus.getDefault().postSticky(new AddFavoriteEvent(mNeighbour,mFavoriteButton)));
    }

    private void initView() {
        mItemListName.setText(mNeighbour.getName());
        mItemListNameDetail.setText(mNeighbour.getName());
        mItemListNameDetailLocalisation.setText(mNeighbour.getAddress());
        mItemListNameDetailMail.setText(mNeighbour.getFacebook().concat(mNeighbour.getName().toLowerCase()));
        mItemListNameDetailPhone.setText(mNeighbour.getPhone().concat(phoneNumber()));
        //mItemListPresentationAboutMe.setText(neighbour.getAboutMeTxt());
        Glide.with(mItemListAvatar.getContext())
                .load(mNeighbour.getAvatarUrl())
                .fitCenter()
                .into(mItemListAvatar);
    }

    //method to catch the neighbour clicked
    private Neighbour getUser(int position, int page) {
        if (page == 0) {
            List<Neighbour> neighboursList = mApiService.getNeighbours();
            return neighboursList.get(position);
        } else {
            List<Neighbour> favoritesList = mApiService.getFavorites();
            return favoritesList.get(position);
        }
    }

    private void backToMain() {
        this.finish();
    }

    //method to get random phone number
    private String phoneNumber() {

        int i;
        String number;
        String phoneNumber = "00";
        String firstNumber = "00";
        Random random = new Random();

        for (i = 0; i < 4; i++) {

            int nb = random.nextInt(100);
            if (nb < 10) number = "0".concat(String.valueOf(nb));
            else number = String.valueOf(nb);

            switch (i){
                case 0 :
                    firstNumber = number;
                    break;
                case 1 :
                    phoneNumber = firstNumber.concat(" ").concat(number);
                    break;
                default :
                    phoneNumber = phoneNumber.concat(" ").concat(number);
            }
        }
        return phoneNumber;
    }
}
