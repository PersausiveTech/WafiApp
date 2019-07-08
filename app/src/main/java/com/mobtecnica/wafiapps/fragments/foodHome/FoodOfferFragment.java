package com.mobtecnica.wafiapps.fragments.foodHome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.etsy.android.grid.StaggeredGridView;

import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.adapters.OfferGridEats;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WafiEatsManager;
import com.mobtecnica.wafiapps.model.wafiEats.Offers.AllOffer;
import com.mobtecnica.wafiapps.model.wafiEats.home.HomeRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 09-06-2017.
 */

public class FoodOfferFragment extends BaseFragment {
    View rootView;
    OfferGridEats gridEats = null;
    List<AllOffer> allOffer = null;
    private StaggeredGridView gridView_offers;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(WafiEatsManager.BROADCAST_EATS_OFFERS_RESPONSE)) {
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_EATS_OFFERS_RESPONSE_STATUS, false)) {
                    setOffers();
                }
            }
        }
    };

    private void setOffers() {
        allOffer = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getOffersList();
        gridEats = new OfferGridEats(getActivity(), allOffer);
        gridView_offers.setAdapter(gridEats);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_offers_food, container, false);
        initViews();
        return rootView;
    }

    private void initViews() {
        gridView_offers = (StaggeredGridView) rootView.findViewById(R.id.gridView_offers);
        IntentFilter intent = new IntentFilter();
        intent.addAction(WafiEatsManager.BROADCAST_EATS_OFFERS_RESPONSE);
        addBroadcastListener(receiver, intent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressbarShowing();
        apiCall();
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    private void apiCall() {
        HomeRequest homeRequest = new HomeRequest();
        homeRequest.setApiToken(Constants.API_TOKEN_EATS);
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getAllOffers(homeRequest);
    }
}
