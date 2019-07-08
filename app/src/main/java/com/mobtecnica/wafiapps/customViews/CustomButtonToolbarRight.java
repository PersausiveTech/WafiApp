package com.mobtecnica.wafiapps.customViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.utils.Utils;

/**
 * Created by Praveen on 3/26/2018.
 */

public class CustomButtonToolbarRight extends RelativeLayout implements View.OnClickListener {

    Utils.BUTTON_TYPE button_type = Utils.BUTTON_TYPE.CART;
    private int cart_order_count = 0;
    TextView tv_toolbar_cart_quantity;
    View view_cart, view_delete, view_add;
    CustomButtonToolbarRightOnclickListener customButtonToolbarRightOnclickListener;
    private View view_search;

    public CustomButtonToolbarRight(Context context) {
        super(context);
        initView(context);
    }

    public CustomButtonToolbarRight(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomButtonToolbarRight(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_button_toolbar_right, this, true);
        tv_toolbar_cart_quantity = (TextView) view.findViewById(R.id.tv_toolbar_cart_quantity);
        view_cart = view.findViewById(R.id.iv_toolbar_cart_container);
        view_delete = view.findViewById(R.id.iv_toolbar_delete);
        view_add = view.findViewById(R.id.iv_toolbar_add);
        view_search = view.findViewById(R.id.iv_toolbar_search);
        view_cart.setOnClickListener(this);
        view_delete.setOnClickListener(this);
        view_add.setOnClickListener(this);
        view_search.setOnClickListener(this);
        refreshView(button_type);
        hideSearch();
    }

    public void setCustomButtonToolbarRightOnclickListener(CustomButtonToolbarRightOnclickListener customButtonToolbarRightOnclickListener) {
        this.customButtonToolbarRightOnclickListener = customButtonToolbarRightOnclickListener;
    }

    public void refreshView(Utils.BUTTON_TYPE button_type) {
        switch (button_type) {
            case CART:
                view_cart.setVisibility(VISIBLE);
                view_search.setVisibility(VISIBLE);
                view_add.setVisibility(GONE);
                view_delete.setVisibility(GONE);
                tv_toolbar_cart_quantity.setText(getCartOrderCount() + "");
                break;
            case ADD:
                view_cart.setVisibility(GONE);
                view_search.setVisibility(GONE);
                view_add.setVisibility(VISIBLE);
                view_delete.setVisibility(GONE);
                break;
            case DELETE:
                view_cart.setVisibility(GONE);
                view_search.setVisibility(GONE);
                view_add.setVisibility(GONE);
                view_delete.setVisibility(VISIBLE);
                break;
            case EMPTY:
                view_cart.setVisibility(INVISIBLE);
                view_search.setVisibility(GONE);
                view_add.setVisibility(GONE);
                view_delete.setVisibility(GONE);
                break;

        }
    }

    public void hideSearch() {
        if (view_search != null) {
            view_search.setVisibility(GONE);
        }
    }

    public int getCartOrderCount() {
        return cart_order_count;
    }

    public void setCartOrderCount(int cart_order_count) {
        this.cart_order_count = cart_order_count;
        if (tv_toolbar_cart_quantity != null) {
            tv_toolbar_cart_quantity.setVisibility(cart_order_count > -1 ? VISIBLE : GONE);
            tv_toolbar_cart_quantity.setText(cart_order_count + "");
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_toolbar_cart_container:
                if (customButtonToolbarRightOnclickListener != null) {
                    customButtonToolbarRightOnclickListener.onCartClick();
                }
                break;
            case R.id.iv_toolbar_delete:
                if (customButtonToolbarRightOnclickListener != null) {
                    customButtonToolbarRightOnclickListener.onDeleteClick();
                }
                break;
            case R.id.iv_toolbar_add:
                if (customButtonToolbarRightOnclickListener != null) {
                    customButtonToolbarRightOnclickListener.onAddClick();
                }
                break;
            case R.id.iv_toolbar_search:
                if (customButtonToolbarRightOnclickListener != null) {
                    customButtonToolbarRightOnclickListener.onSearchClick();
                }
                break;
            default:
                break;
        }
    }

    public interface CustomButtonToolbarRightOnclickListener {
        void onCartClick();

        void onDeleteClick();

        void onAddClick();

        void onSearchClick();

    }
}
