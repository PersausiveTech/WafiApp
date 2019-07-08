package com.mobtecnica.wafiapps.model.search.searchAutoComplete;

/**
 * Created by SIby on 02-03-2017.
 */

public class SearchAutoCompletionRequest {
    private String apiToken;

    private String term;

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

    public String getTerm ()
    {
        return term;
    }

    public void setTerm (String term)
    {
        this.term = term;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [apiToken = "+apiToken+", term = "+term+"]";
    }
}
