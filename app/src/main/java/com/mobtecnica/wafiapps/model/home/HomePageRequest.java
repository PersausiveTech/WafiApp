package com.mobtecnica.wafiapps.model.home;

import com.mobtecnica.wafiapps.model.BaseUserRequest;

/**
 * Created by SIby on 09-02-2017.
 */

public class HomePageRequest extends BaseUserRequest {
    public HomePageRequest(String guid) {
        super();
        setGuid(guid);
    }
}
