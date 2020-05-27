package com.example.project;

public class politicalimages {



    private String partyname;
    private String url;

    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public String getPartyposition() {
        return partyposition;
    }

    public void setPartyposition(String partyposition) {
        this.partyposition = partyposition;
    }

    private String partyposition;
    private String party;

    public politicalimages() {
    }


    public politicalimages(String party, String url,String partyname,String partyposition) {
        this.party = party;
        this.url = url;
        this.partyname=partyname;
        this.partyposition=partyposition;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
