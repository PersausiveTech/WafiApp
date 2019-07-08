package com.mobtecnica.wafiapps.listeners;

import com.mobtecnica.wafiapps.model.Products;

public interface OnProductListGridItemClickListener {
    void onAddtoCartClick(Products product);

    void onShareClick(Products product);

    void onAddtoWishlistClick(Products product);

    void onItemClick(Products product);

    void onListEmpty(boolean isEmpty);
}