package tech.libin.rahul.ideaproject.events;

import tech.libin.rahul.ideaproject.events.base.BaseEvent;

/**
 * Created by libin on 05/03/17.
 */

public class SearchEvent extends BaseEvent {
    private String name;
    private String msisdn;
    private String zip;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
