package com.mobtecnica.wafiapps.fragments.wafi_main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.manager.ObjectFactory;
import com.mobtecnica.wafiapps.manager.ProfileManager;
import com.mobtecnica.wafiapps.model.profile.get_address.Addresses;
import com.mobtecnica.wafiapps.model.profile.get_address.GetUserAddressRequest;
import com.mobtecnica.wafiapps.model.profile.update_address.Address;
import com.mobtecnica.wafiapps.model.profile.update_address.Model;
import com.mobtecnica.wafiapps.model.profile.update_address.UpdateUserAddressRequest;
import com.mobtecnica.wafiapps.utils.BaseFragment;
import com.mobtecnica.wafiapps.utils.Constants;
import com.mobtecnica.wafiapps.utils.Utils;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SIby on 22-02-2017.
 */

public class UpdateAddressFragment extends BaseFragment implements View.OnClickListener {
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches(ProfileManager.BROADCAST_UPDATE_ADDRESS_RESPONSE)) {
                if (intent.getBooleanExtra(ProfileManager.BROADCAST_UPDATE_ADDRESS_RESPONSE_STATUS, false)) {
                    getActivity().onBackPressed();
                    GetUserAddressRequest getUserAddressRequest = new GetUserAddressRequest();
                    getUserAddressRequest.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
                    getUserAddressRequest.setApiToken(Constants.API_TOKEN);
                    progressbarShowing();
                    ObjectFactory.getInstance().getProfileManager(getActivity()).getUserAddress(getUserAddressRequest,1);
                }
            }
        }
    };
    View rootView;
    TextView et_first_name,
            et_last_name,
            et_email,
            et_phone_number,
            et_flat_number,
            et_building_num, et_road_num,
            et_block_num,
            et_area_name,
            et_nearest_land_mark;
    SearchableSpinner spinner_country;
    Button button_save;
    Addresses selectedAddress;
    List<String> list = new ArrayList<>();
    private CountryCodePicker codePicker;
    private int id = 0;

    private void navigateToHome() {
        getActivity().onBackPressed();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_update_address, container, false);
        initialize();
        setOnClick();
        setAddressData();
        return rootView;
    }

    private void setAddressData() {
        selectedAddress  = ObjectFactory.getInstance().getProfileManager(getActivity()).getSelectedAddress();
        et_first_name.setText(selectedAddress.getFirstName());
        et_last_name.setText(selectedAddress.getLastName());
        et_email.setText(selectedAddress.getEmail());
        et_phone_number.setText(selectedAddress.getPhoneNumber());
        et_flat_number.setText(selectedAddress.getCustomAddressAttributes()[0].getDefaultValue());
        et_building_num.setText(selectedAddress.getCustomAddressAttributes()[1].getDefaultValue());
        et_road_num.setText(selectedAddress.getCustomAddressAttributes()[2].getDefaultValue());
        et_block_num.setText(selectedAddress.getCustomAddressAttributes()[3].getDefaultValue());
        et_area_name.setText(selectedAddress.getCustomAddressAttributes()[4].getDefaultValue());
        et_nearest_land_mark.setText(selectedAddress.getCustomAddressAttributes()[5].getDefaultValue());
        try {
//            id = Integer.parseInt(selectedAddress.getCountryId());
            spinner_country.onSearchableItemClicked(spinner_country.getItemAtPosition(0),0); //TODO only for bahrain, update this for other countries too.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setOnClick() {
        button_save.setOnClickListener(this);
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        id = 97;
                        break;
                    case 1:
                        id = 193;
                        break;
                    case 2:
                        id = 66;
                        break;
                    case 3:
                        id = 69;
                        break;
                    case 5:
                        id = 81;
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initialize() {
        IntentFilter intent = new IntentFilter();
        intent.addAction(ProfileManager.BROADCAST_UPDATE_ADDRESS_RESPONSE);
        addBroadcastListener(receiver, intent);
        setTitle(getResources().getString(R.string.update_address));
        et_first_name = (TextView) rootView.findViewById(R.id.et_first_name);
        et_last_name = (TextView) rootView.findViewById(R.id.et_last_name);
        et_email = (TextView) rootView.findViewById(R.id.et_email);
        et_phone_number = (TextView) rootView.findViewById(R.id.et_phone_number);
        et_flat_number = (TextView) rootView.findViewById(R.id.et_flat_number);
        et_building_num = (TextView) rootView.findViewById(R.id.et_building_num);
        et_road_num = (TextView) rootView.findViewById(R.id.et_road_num);
        et_block_num = (TextView) rootView.findViewById(R.id.et_block_num);
        et_area_name = (TextView) rootView.findViewById(R.id.et_area_name);
        et_nearest_land_mark = (TextView) rootView.findViewById(R.id.et_nearest_land_mark);
        button_save = (Button) rootView.findViewById(R.id.btn_save);
        spinner_country = (SearchableSpinner) rootView.findViewById(R.id.spinner_country);
        codePicker = (CountryCodePicker) rootView.findViewById(R.id.ccp_mobile_number);

        spinner_country.setTitle(getString(R.string.select_country));
        spinner_country.setPositiveButton(getString(R.string.ok));

        list.add(getString(R.string.bahrain));
//        list.add("Oman");
//        list.add("Qatar");
//        list.add("Saudi Arabia");
//        list.add("United  Arab Emigrates");

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_country.setAdapter(adp1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                if (validation())
                    apiCall();
                break;
            default:
                break;
        }
    }

    private boolean validation() {
        boolean status = true;
        if (TextUtils.isEmpty(et_first_name.getText().toString().trim()) || !(et_first_name.getText().toString().trim().matches("[a-zA-Z.? ]*"))) {
            status = false;
            et_first_name.setError(getString(R.string.enter_valid_name));
        } else if (TextUtils.isEmpty(et_last_name.getText().toString().trim()) || !(et_last_name.getText().toString().trim().matches("[a-zA-Z.? ]*"))) {
            status = false;
            et_last_name.setError("");
        } else if (TextUtils.isEmpty(et_email.getText().toString().trim())) {
            status = false;
            et_email.setError(getString(R.string.enter_name));
        } else if (TextUtils.isEmpty(et_phone_number.getText().toString().trim())) {
            status = false;
            et_phone_number.setError(getString(R.string.enter_mobile_number));
        } else if (TextUtils.isEmpty(et_flat_number.getText().toString().trim())) {
            status = false;
            et_flat_number.setError(getString(R.string.enter_flat_number));
        } else if (TextUtils.isEmpty(et_building_num.getText().toString().trim())) {
            status = false;
            et_building_num.setError(getString(R.string.enter_building_number));
        } else if (TextUtils.isEmpty(et_road_num.getText().toString().trim())) {
            status = false;
            et_road_num.setError("");
        } else if (TextUtils.isEmpty(et_block_num.getText().toString().trim())) {
            status = false;
            et_block_num.setError("");
        } else if (TextUtils.isEmpty(et_area_name.getText().toString().trim())) {
            status = false;
            et_area_name.setError("");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(et_email.getText().toString()).matches()) {
            status = false;
            et_email.setError(Html.fromHtml(getString(R.string.email_is_not_valid)));

        } else if (et_phone_number.getText().toString().trim().length() < 8) {
            status = false;
            et_phone_number.setError(Html.fromHtml(getString(R.string.invalid_number)));

        } else if (id == 0) {
            status = false;
            Utils.makeSnackBar(et_area_name, R.string.please_select_country, Snackbar.LENGTH_LONG);
        }
        return status;
    }

    private void apiCall() {
        try {
            UpdateUserAddressRequest request = new UpdateUserAddressRequest();
            request.setAddressId(selectedAddress.getId());
            request.setGuid(ObjectFactory.getInstance().getAppPreference(getActivity()).getGuid());
            request.setApiToken(Constants.API_TOKEN);
            Address address = new Address();
            address.setFirstName(et_first_name.getText().toString().trim());
            address.setLastName(et_last_name.getText().toString().trim());
            address.setEmail(et_email.getText().toString().trim());
            String code = codePicker.getSelectedCountryCodeWithPlus();
            address.setCountryId(String.valueOf(id));
            address.setPhoneNumber(et_phone_number.getText().toString().trim());
            address.setAddress_attribute_1(et_flat_number.getText().toString().trim());
            address.setAddress_attribute_2(et_building_num.getText().toString().trim());
            address.setAddress_attribute_3(et_road_num.getText().toString().trim());
            address.setAddress_attribute_4(et_block_num.getText().toString().trim());
            address.setAddress_attribute_5(et_area_name.getText().toString().trim());
            address.setAddress_attribute_6(et_nearest_land_mark.getText().toString().trim());
            address.setAddress1(getString(R.string.flat_no) + address.getAddress_attribute_1() + getString(R.string.building_no) + address.getAddress_attribute_2() + getString(R.string.road_no) +
                    address.getAddress_attribute_3() + getString(R.string.block_no) +
                    address.getAddress_attribute_4() + getString(R.string.area_name) + address.getAddress_attribute_5() + getString(R.string.landmark) + address.getAddress_attribute_6());

            Model model = new Model();
            model.setAddress(address);
            request.setModel(model);
            progressbarShowing();
            ObjectFactory.getInstance().getProfileManager(getActivity()).updateUserAddress(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void progressbarShowing() {
        Intent intent = new Intent(Constants.Login.BROADCAST_PROGRESS_WHEEL);
        intent.putExtra(Constants.Login.BROADCAST_ISDISPLAY_PROGRESS_WHEEL, true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }


}
