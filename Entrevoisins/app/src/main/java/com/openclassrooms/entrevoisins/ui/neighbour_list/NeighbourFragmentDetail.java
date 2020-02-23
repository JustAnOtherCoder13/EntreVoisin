package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class NeighbourFragmentDetail extends Fragment {

    @BindView(R.id.item_list_avatar)
     ImageView mItemListAvatar;
    @BindView (R.id.item_list_name)
     TextView mItemListName;
    @BindView (R.id.item_list_name_detail)
     TextView mItemListNameDetail;
    @BindView (R.id.item_list_name_detail_localisation_txt)
     TextView mItemListNameDetailLocalisation;
    @BindView (R.id.item_list_name_detail_phone_txt)
     TextView mItemListNameDetailPhone;
    @BindView (R.id.item_list_name_detail_mail_txt)
     TextView mItemListNameDetailMail;
    @BindView (R.id.item_list_presentation_about_me_txt)
     TextView mItemListPresentationAboutMe;

    @BindView (R.id.item_list_return_button)
    ImageButton mReturnButton;
    @BindView (R.id.item_list_favorite_button)
    ImageButton mFavoriteButton;

    private Unbinder unbinder;




    public static NeighbourFragmentDetail newInstance() {
        NeighbourFragmentDetail fragment = new NeighbourFragmentDetail();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_detail, container, false);
        unbinder = ButterKnife.bind(this,view);


        return view;
    }
}
