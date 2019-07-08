package com.mobtecnica.wafiapps.fragments.foodHome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.adapters.EatsCartListingRecyclerViewAdapter;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.WafiEatsManager;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.CartItem;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.CartRequest;
import com.mobtecnica.wafiapps.model.wafiEats.cartRestaurant.removeFromCart.RemoveFromEatsCartRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by SIby on 10-06-2017.
 */

public class CartWafiEatsFragment extends BaseFragment implements View.OnClickListener {
    EatsCartListingRecyclerViewAdapter adapter = null;
    private List<CartItem> cartItems;
    private View rootView;
    private RecyclerView rv_cart_eats;
    private TextView tv_total_price;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(WafiEatsManager.BROADCAST_EATS_CART_RESPONSE)) {
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_EATS_CART_RESPONSE_STATUS, false)) {
                    setCartItems();
                }
            } else if (intent.getAction().matches(WafiEatsManager.BROADCAST_REMOVE_EATS_CART_RESPONSE)) {
                if (intent.getBooleanExtra(WafiEatsManager.BROADCAST_REMOVE_EATS_CART_RESPONSE_STATUS, false)) {
                    apiCall();
                }
            }
        }
    };
    private TextView check_out;

    private void setCartItems() {
        cartItems = ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getEatsCartList();
        adapter.setDataset(cartItems);
        adapter.notifyDataSetChanged();
        tv_total_price.setText("not available");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart_wafi_eats, container, false);
        initViews();
        progressbarShowing();
        onClickListners();
        return rootView;
    }

    private void onClickListners() {
        adapter.setOnItemClickListener(new EatsCartListingRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                switch (v.getId()) {
                    case R.id.iv_delete_items:
                        progressbarShowing();
                        deleteItems(position);
                        break;
                    default:
                        break;
                }
            }
        });
        check_out.setOnClickListener(this);
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    private void deleteItems(int position) {
        RemoveFromEatsCartRequest removeFromEatsCartRequest = new RemoveFromEatsCartRequest();
        removeFromEatsCartRequest.setEmailID(ObjectFactory.getInstance().getAppPreference(getActivity()).getEmailId());
        removeFromEatsCartRequest.setApiToken(Constants.API_TOKEN_EATS);
        removeFromEatsCartRequest.setMenuID(cartItems.get(position).getMenuID());
        removeFromEatsCartRequest.setCartItemID(cartItems.get(position).getCartItemId());
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).removeFromCart(removeFromEatsCartRequest);

    }

    private void initViews() {

        IntentFilter intent = new IntentFilter();
        intent.addAction(WafiEatsManager.BROADCAST_EATS_CART_RESPONSE);
        intent.addAction(WafiEatsManager.BROADCAST_REMOVE_EATS_CART_RESPONSE);
        addBroadcastListener(receiver, intent);

        rv_cart_eats = (RecyclerView) rootView.findViewById(R.id.rv_cart_eats);
        tv_total_price = (TextView) rootView.findViewById(R.id.tv_total_price);
        check_out = (TextView) rootView.findViewById(R.id.check_out);
        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_cart_eats.setLayoutManager(verticalLayoutmanager);
        adapter = new EatsCartListingRecyclerViewAdapter(getActivity(), new ArrayList<CartItem>());
        rv_cart_eats.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiCall();
    }

    private void apiCall() {
        CartRequest request = new CartRequest();
        request.setApiToken(Constants.API_TOKEN_EATS);
        request.setEmailID(ObjectFactory.getInstance().getAppPreference(getActivity()).getEmailId());
        ObjectFactory.getInstance().getWafiEatsManager(getActivity()).getWafiEatsCart(request);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_out:
                navigateToCheckoutFragment();
                break;
            default:
                break;
        }
    }

    private void navigateToCheckoutFragment() {
        if (cartItems.size() > 0){
            Fragment fragment = new WafieatsCheckoutFragment();
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .replace(R.id.sb_root_product, fragment, Utils.getTagForFragment(fragment))
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
        }else {

        }
    }
}
