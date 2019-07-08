package com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse;

import com.mobtecnica.wafiapps.model.home.HomeBrands;

/**
 * Created by SIby on 20-03-2017.
 */


public class Data
{
    private OrderTotals OrderTotals;

    private Cart Cart;
    private String CartTotal;
    private int CartItemsCount;

    public OrderTotals getOrderTotals ()
    {
        return OrderTotals;
    }

    public void setOrderTotals (OrderTotals OrderTotals)
    {
        this.OrderTotals = OrderTotals;
    }

    public Cart getCart ()
    {
        return Cart;
    }

    public void setCart (Cart Cart)
    {
        this.Cart = Cart;
    }

    public String getCartTotal() {
        return CartTotal;
    }

    public void setCartTotal(String cartTotal) {
        CartTotal = cartTotal;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [OrderTotals = "+OrderTotals+", Cart = "+Cart+"]";
    }

    public int getCartItemsCount() {
        return CartItemsCount;
    }

    public void setCartItemsCount(int cartItemsCount) {
        CartItemsCount = cartItemsCount;
    }
}

