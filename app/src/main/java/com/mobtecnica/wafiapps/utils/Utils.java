package com.mobtecnica.wafiapps.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.mobtecnica.wafiapps.BuildConfig;
import com.mobtecnica.wafiapps.R;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.CheckoutAttributes;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.GetCartResponse;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.GiftCards;
import com.mobtecnica.wafiapps.model.cart.getCart.getCartResponse.Values;
import com.mobtecnica.wafiapps.model.productDetails.ProductDetailsResponse;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class Utils {

    private static Typeface typefaceGothamRegular, typefaceGothamBold;

    public static String getTagForFragment(Fragment fragment) {
        return fragment.getClass().getSimpleName();
    }

    public static boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    public static String toUtc(String givenDate) {
        String formattedDate = "";
        try {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = inputFormat.parse(givenDate);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            formattedDate = dateFormat.format(date);
        } catch (Exception e) {
            Log.d("Utils", "Could not convert date to utc", e);
        }
        return formattedDate;
    }

    public static long dateToTimeInMillisecond(String givenDate) {
        long timeinmillis = 0;
        try {
            String formattedDate = toUtc(givenDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(formattedDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            timeinmillis = calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeinmillis;
    }

    public static String toFormattedDate(String givenDate) {
        try {
            long milliSeconds = Long.parseLong(givenDate);
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, hh:ss aaa");
            // Create a calendar object that will convert the date and time value in milliseconds to date.
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds * 1000L);
            return formatter.format(calendar.getTime());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return givenDate;
    }

//    public static String getDeviceId(Context context) {
//        TelephonyManager telephonyManager =
//                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        return telephonyManager.getDeviceId();
//    }

    public static void hideSoftKeyboard(View view, Context context) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Returns the consumer friendly device name
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }


    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    /**
     * return Lato-Regular TypeFace.
     *
     * @param context
     * @return Typeface
     */
    public static Typeface getTypefaceLatoRegular(Context context) {
        if (typefaceGothamRegular == null) {
            typefaceGothamRegular = getTypeface(context, context.getString(R.string.font_path_gotham_regular));
        }
        return typefaceGothamRegular;
    }

    /**
     * create TypeFace with corresponding font path.
     *
     * @param context
     * @param font_path
     * @return TypeFace
     */
    private static Typeface getTypeface(Context context, String font_path) {
        return Typeface.createFromAsset(context.getAssets(), font_path);
    }

    /**
     * return Lato-Bold TypeFace.
     *
     * @param context
     * @return Typeface
     */
    public static Typeface getTypefaceLatoBold(Context context) {
        if (typefaceGothamBold == null) {
            typefaceGothamBold = getTypeface(context, context.getString(R.string.font_path_gotham_bold));
        }
        return typefaceGothamBold;
    }

    public static String loadJSONFromAsset(Context context) {

        String json = null;
        try {
            InputStream is = context.getAssets().open("AllInHomePage.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public enum BUTTON_TYPE {
        CART, DELETE, ADD, EMPTY
    }

    /**
     * set Lato font for snackbar
     *
     * @param snackbar
     */
    public static Snackbar changeSnackBarFont(final Snackbar snackbar) {
        try {
            TextView tv = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
            tv.setTypeface(Utils.getTypefaceLatoRegular(snackbar.getContext()));
            View v = snackbar.getView().findViewById(android.support.design.R.id.snackbar_action);
            if (v != null) {
                if (v instanceof TextView) {
                    ((TextView) v).setTypeface(Utils.getTypefaceLatoRegular(snackbar.getContext()));
                }
            }
            snackbar.getView().bringToFront();
            snackbar.show();
            snackbar.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    snackbar.dismiss();
                    v.setOnClickListener(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return snackbar;
    }

    public static Snackbar makeSnackBar(View view, int string_res_id, int duration) {
        try {
            return changeSnackBarFont(Snackbar.make(view, string_res_id, duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Snackbar makeSnackBar(View view, String message, int duration) {
        try {
            return changeSnackBarFont(Snackbar.make(view, message, duration));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void shareProduct(Context context, String se_name,String id) {
        //TODO correct the url
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "http://www.wafiapps.com/" + se_name+"?id="+id);
        context.startActivity(Intent.createChooser(shareIntent, "Share link using"));
    }


    public static String getCheckoutPrice(GetCartResponse cartResponse) {
        String checkoutPrice = "BD 0.000";
        if (cartResponse != null) {
            if (cartResponse.getSuccess().equals("true")) {
                checkoutPrice = updateSubTotal(checkoutPrice, cartResponse.getData().getCartTotal());
                CheckoutAttributes[] checkoutAttributes = cartResponse.getData().getCart().getCheckoutAttributes();
                for (CheckoutAttributes attributes : checkoutAttributes) {
                    if (attributes.getIsRequired().equalsIgnoreCase("true")) {
                        for (Values values : attributes.getValues()) {
                            if (values.getIsPreSelected()) {
                                checkoutPrice = updateSubTotal(checkoutPrice, values.getPriceAdjustment());
                            }
                        }
                    }
                }
                checkoutPrice = updateSubTotal(checkoutPrice, cartResponse.getData().getOrderTotals().getShipping());
                checkoutPrice = updateSubTotal(checkoutPrice, cartResponse.getData().getOrderTotals().getTax());
                checkoutPrice = updateSubTotal(checkoutPrice, cartResponse.getData().getOrderTotals().getOrderTotalDiscount());
                GiftCards[] giftCards = cartResponse.getData().getOrderTotals().getGiftCards();
                for (GiftCards giftCard : giftCards) {
                    checkoutPrice = updateSubTotal(checkoutPrice, giftCard.getAmount());
                }
            }
        }

        return checkoutPrice;
    }
    public static int getDeviceWidth(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return  width;
    }
    private static String updateSubTotal(String value1, String value2) {
        String sub_total_temp = value1.toLowerCase().replaceAll("[a-zA-Z ,]", "");
        float subTotalFloat = Float.parseFloat(sub_total_temp);
        if (!TextUtils.isEmpty(value2) && !value2.equals("null")) {
            value2 = value2.toLowerCase().replaceAll("[a-zA-Z ,]", "");
            if (value2.startsWith("-")) {
                subTotalFloat = subTotalFloat - Float.parseFloat(value2.replace("-", ""));
            } else {
                subTotalFloat = subTotalFloat + Float.parseFloat(value2.replace("+", ""));
            }
        }
        return ("BD " + String.format(Locale.US, "%,.3f", subTotalFloat));
    }

    public static String getTotalAmount(GetCartResponse response) {
        String priceFormatted = getCheckoutPrice(response);
        priceFormatted = priceFormatted.toLowerCase().replaceAll("[a-zA-Z ,]", "");
        return String.format(Locale.US, "%,.3f",  Float.parseFloat(priceFormatted));

    }

    public static String getFormattedAmount(String amount) {
        if (!TextUtils.isEmpty(amount)) {
            amount = amount.replaceAll("[a-zA-Z ,]", "");
        }
        return amount;
    }
    public static boolean isGiftCardApplied(GetCartResponse cartResponse) {

        if (cartResponse != null) {
            try {
//                return cartResponse.getData().getCart().getGiftCardBox().getDisplay();
                return cartResponse.getData().getOrderTotals().getGiftCards().length > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean isDiscountCouponApplied(GetCartResponse cartResponse) {

        if (cartResponse != null) {
            try {
                String totalDiscount = cartResponse.getData().getOrderTotals().getOrderTotalDiscount();
                if (!TextUtils.isEmpty(totalDiscount) && !totalDiscount.equalsIgnoreCase("null")) { //TODO correct this method and API
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static PaymentDataHolder getPaymentBreakdownList(GetCartResponse cartResponse) {
        PaymentDataHolder paymentDataHolder = new PaymentDataHolder();
        if (cartResponse != null) {
            if (cartResponse.getSuccess().equals("true")) {
                paymentDataHolder.setCartTotal(cartResponse.getData().getCartTotal());
                CheckoutAttributes[] checkoutAttributes = cartResponse.getData().getCart().getCheckoutAttributes();
                for (CheckoutAttributes attributes : checkoutAttributes) {
                    if (attributes.getIsRequired().equalsIgnoreCase("true")) {
                        for (Values values : attributes.getValues()) {
                            if (values.getIsPreSelected()) {
                                if (!TextUtils.isEmpty(values.getPriceAdjustment()) && !values.getPriceAdjustment().equalsIgnoreCase("null")) {
                                    paymentDataHolder.addPriceBreakdownData(attributes.getName(), values.getName() + " " + values.getPriceAdjustment());
                                }
                            }
                        }
                    }
                }
                if (!TextUtils.isEmpty(cartResponse.getData().getOrderTotals().getDisplayTax()) && !cartResponse.getData().getOrderTotals().getDisplayTax().equalsIgnoreCase("null")) {
                    paymentDataHolder.addPriceBreakdownData("VAT", cartResponse.getData().getOrderTotals().getTax());
                }
                if (!TextUtils.isEmpty(cartResponse.getData().getOrderTotals().getShipping()) && !cartResponse.getData().getOrderTotals().getShipping().equalsIgnoreCase("null")) {
                    paymentDataHolder.addPriceBreakdownData("Delivery Charges", cartResponse.getData().getOrderTotals().getShipping());
                }

                if (!TextUtils.isEmpty(cartResponse.getData().getOrderTotals().getOrderTotalDiscount()) && !cartResponse.getData().getOrderTotals().getOrderTotalDiscount().equalsIgnoreCase("null")) {
                    paymentDataHolder.addPriceBreakdownData("Discount", cartResponse.getData().getOrderTotals().getOrderTotalDiscount());
                }
                GiftCards[] giftCards = cartResponse.getData().getOrderTotals().getGiftCards();
                for (GiftCards giftCard : giftCards) {
                    if (!TextUtils.isEmpty(giftCard.getAmount()) && !giftCard.getAmount().equalsIgnoreCase("null")) {
                        paymentDataHolder.addPriceBreakdownData("Gift cards", giftCard.getCouponCode() + "  " + giftCard.getAmount());
                    }
                }
                paymentDataHolder.setSubTotal(getCheckoutPrice(cartResponse));

            }
        }
        return paymentDataHolder;
    }

    public static void getKeyHash(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    BuildConfig.APPLICATION_ID,
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public static int calculateDiscount(String strOldPrice, String strNewPrice) {
        String price = strNewPrice.toLowerCase().replaceAll("[a-zA-Z ,]", "");
        float item_price = Float.parseFloat(price);
        String old_price = strOldPrice.toLowerCase().replaceAll("[a-zA-Z ,]", "");
        float oldPrice = Float.parseFloat(old_price);
        float percentage = ((oldPrice - item_price) / oldPrice) * 100;
        return Math.round(percentage);
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
