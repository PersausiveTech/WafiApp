
package com.mobtecnica.wafiapps.model.topicContent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("SystemName")
    @Expose
    public String systemName;
    @SerializedName("IncludeInSitemap")
    @Expose
    public Boolean includeInSitemap;
    @SerializedName("IsPasswordProtected")
    @Expose
    public Boolean isPasswordProtected;
    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Body")
    @Expose
    public String body;
    @SerializedName("MetaKeywords")
    @Expose
    public String metaKeywords;
    @SerializedName("MetaDescription")
    @Expose
    public String metaDescription;
    @SerializedName("MetaTitle")
    @Expose
    public String metaTitle;
    @SerializedName("SeName")
    @Expose
    public String seName;
    @SerializedName("TopicTemplateId")
    @Expose
    public Integer topicTemplateId;
    @SerializedName("Id")
    @Expose
    public Integer id;
    @SerializedName("CustomProperties")
    @Expose
    public CustomProperties customProperties;

    public Data(String systemName, Boolean includeInSitemap, Boolean isPasswordProtected, String title, String body, String metaKeywords, String metaDescription, String metaTitle, String seName, Integer topicTemplateId, Integer id, CustomProperties customProperties) {
        this.systemName = systemName;
        this.includeInSitemap = includeInSitemap;
        this.isPasswordProtected = isPasswordProtected;
        this.title = title;
        this.body = body;
        this.metaKeywords = metaKeywords;
        this.metaDescription = metaDescription;
        this.metaTitle = metaTitle;
        this.seName = seName;
        this.topicTemplateId = topicTemplateId;
        this.id = id;
        this.customProperties = customProperties;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Boolean getIncludeInSitemap() {
        return includeInSitemap;
    }

    public void setIncludeInSitemap(Boolean includeInSitemap) {
        this.includeInSitemap = includeInSitemap;
    }

    public Boolean getPasswordProtected() {
        return isPasswordProtected;
    }

    public void setPasswordProtected(Boolean passwordProtected) {
        isPasswordProtected = passwordProtected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public Integer getTopicTemplateId() {
        return topicTemplateId;
    }

    public void setTopicTemplateId(Integer topicTemplateId) {
        this.topicTemplateId = topicTemplateId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomProperties getCustomProperties() {
        return customProperties;
    }

    public void setCustomProperties(CustomProperties customProperties) {
        this.customProperties = customProperties;
    }
}
