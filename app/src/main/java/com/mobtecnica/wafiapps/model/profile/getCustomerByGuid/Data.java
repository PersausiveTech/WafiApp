package com.mobtecnica.wafiapps.model.profile.getCustomerByGuid;

/**
 * Created by SIby on 10-01-2017.
 */


public class Data {
    private String Deleted;
    private String LastIpAddress;
    private String HasShoppingCartItems;
    private String LastLoginDateUtc;
    private String IsTaxExempt;
    private String UserName;
    private ShoppingCartItems[] ShoppingCartItems;
    private String LastActivityDateUtc;
    private String IsSystemAccount;
    private String AffiliateId;
    private String AdminComment;
    private String SystemName;
    private String Active;
    private String CreatedOnUtc;
    private String Email;
    private String CustomerGuid;
    private String VendorId;
    private String Id;

    private String[] ExternalAuthenticationRecords;

    private CustomerRoles[] CustomerRoles;

    public String getDeleted() {
        return Deleted;
    }

    public String getAdminComment() {
        return AdminComment;
    }

    public void setAdminComment(String adminComment) {
        AdminComment = adminComment;
    }

    public String getSystemName() {
        return SystemName;
    }

    public void setSystemName(String systemName) {
        SystemName = systemName;
    }

    public void setDeleted(String Deleted) {
        this.Deleted = Deleted;
    }

    public String getLastIpAddress() {
        return LastIpAddress;
    }

    public void setLastIpAddress(String LastIpAddress) {
        this.LastIpAddress = LastIpAddress;
    }

    public String getHasShoppingCartItems() {
        return HasShoppingCartItems;
    }

    public void setHasShoppingCartItems(String HasShoppingCartItems) {
        this.HasShoppingCartItems = HasShoppingCartItems;
    }

    public String getLastLoginDateUtc() {
        return LastLoginDateUtc;
    }

    public void setLastLoginDateUtc(String LastLoginDateUtc) {
        this.LastLoginDateUtc = LastLoginDateUtc;
    }


    public String getIsTaxExempt() {
        return IsTaxExempt;
    }

    public void setIsTaxExempt(String IsTaxExempt) {
        this.IsTaxExempt = IsTaxExempt;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public ShoppingCartItems[] getShoppingCartItems() {
        return ShoppingCartItems;
    }

    public void setShoppingCartItems(ShoppingCartItems[] ShoppingCartItems) {
        this.ShoppingCartItems = ShoppingCartItems;
    }

    public String getLastActivityDateUtc() {
        return LastActivityDateUtc;
    }

    public void setLastActivityDateUtc(String LastActivityDateUtc) {
        this.LastActivityDateUtc = LastActivityDateUtc;
    }

    public String getIsSystemAccount() {
        return IsSystemAccount;
    }

    public void setIsSystemAccount(String IsSystemAccount) {
        this.IsSystemAccount = IsSystemAccount;
    }

    public String getAffiliateId() {
        return AffiliateId;
    }

    public void setAffiliateId(String AffiliateId) {
        this.AffiliateId = AffiliateId;
    }


    public String getActive() {
        return Active;
    }

    public void setActive(String Active) {
        this.Active = Active;
    }

    public String getCreatedOnUtc() {
        return CreatedOnUtc;
    }

    public void setCreatedOnUtc(String CreatedOnUtc) {
        this.CreatedOnUtc = CreatedOnUtc;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCustomerGuid() {
        return CustomerGuid;
    }

    public void setCustomerGuid(String CustomerGuid) {
        this.CustomerGuid = CustomerGuid;
    }

    public String getVendorId() {
        return VendorId;
    }

    public void setVendorId(String VendorId) {
        this.VendorId = VendorId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String[] getExternalAuthenticationRecords() {
        return ExternalAuthenticationRecords;
    }

    public void setExternalAuthenticationRecords(String[] ExternalAuthenticationRecords) {
        this.ExternalAuthenticationRecords = ExternalAuthenticationRecords;
    }

    public CustomerRoles[] getCustomerRoles() {
        return CustomerRoles;
    }

    public void setCustomerRoles(CustomerRoles[] CustomerRoles) {
        this.CustomerRoles = CustomerRoles;
    }

    @Override
    public String toString() {
        return "ClassPojo [Deleted = " + Deleted + ", LastIpAddress = " + LastIpAddress + ", HasShoppingCartItems = " + HasShoppingCartItems + ", LastLoginDateUtc = " + LastLoginDateUtc + ", SystemName = " + SystemName + ", IsTaxExempt = " + IsTaxExempt + ", UserName = " + UserName + ", ShoppingCartItems = " + ShoppingCartItems + ", LastActivityDateUtc = " + LastActivityDateUtc + ", IsSystemAccount = " + IsSystemAccount + ", AffiliateId = " + AffiliateId + ", AdminComment = " + AdminComment + ", Active = " + Active + ", CreatedOnUtc = " + CreatedOnUtc + ", Email = " + Email + ", CustomerGuid = " + CustomerGuid + ", VendorId = " + VendorId + ", Id = " + Id + ", ExternalAuthenticationRecords = " + ExternalAuthenticationRecords + ", CustomerRoles = " + CustomerRoles + "]";
    }
}
