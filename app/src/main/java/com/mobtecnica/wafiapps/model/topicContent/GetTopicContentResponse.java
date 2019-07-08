
package com.mobtecnica.wafiapps.model.topicContent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTopicContentResponse {

    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("statusCode")
    @Expose
    public String statusCode;
    @SerializedName("data")
    @Expose
    public Data data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


}
