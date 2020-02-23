package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.ItemClickSupport;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.openclassrooms.entrevoisins.di.DI.getNeighbourApiService;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;
    private NeighbourApiService mApiService = getNeighbourApiService();
    //
    //new attributes
    //
    //get out of initList initialization of mNeighbours2
    private List<Neighbour> mNeighbours2 = mApiService.getNeighbours();

    //instantiate MyNeighbourRecyclerViewAdapter and pass mNeighbours2 list in parameter
    private MyNeighbourRecyclerViewAdapter myNeighbourRecyclerViewAdapter = new MyNeighbourRecyclerViewAdapter(mNeighbours2);
    private int mLayoutToCharge;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_neighbour_detail);
        ButterKnife.bind(this);


        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }



}





