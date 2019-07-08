package com.mobtecnica.wafiapps.model.search;

import com.mobtecnica.wafiapps.utils.Constants;

/**
 * Created by SIby on 02-03-2017.
 */


public class SearchRequest
{
    public SearchRequest (){}
    public SearchRequest (String search_text,String pageNumber, String orderby,String[] spec, String[] manufcId){
        setApiToken(Constants.API_TOKEN);
        Model model = new Model();
        model.setOrderby(orderby);
//        model.setPageNumber(pageNumber);
        model.setAdvancedSearch("true");
        model.setPriceFrom("");
        model.setPriceTo("");
        model.setQ(search_text);
        model.setSearchInBrand("true");
        model.setSearchInSubCategories("true");
        model.setSearchInDescriptions("true");
        model.setSearchInCategory("0");
        model.setSpecs(spec);
        model.setFilteredManufacturerIds(manufcId);
        setModel(model);
        Command command =new Command();
        command.setPageNumber(pageNumber);
        setCommand(command);

//        model.setSearchInDescriptions("false");
    }
    private Model model;
    private Command command;
    private String apiToken;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Model getModel ()
    {
        return model;
    }

    public void setModel (Model model)
    {
        this.model = model;
    }

    public String getApiToken ()
    {
        return apiToken;
    }

    public void setApiToken (String apiToken)
    {
        this.apiToken = apiToken;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [model = "+model+", command = "+command+" apiToken = "+apiToken+"]";
    }
}
