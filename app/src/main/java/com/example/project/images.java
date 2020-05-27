package com.example.project;

import android.content.Context;

public class images {



    private String party,url;

    public images() {
    }


    public images(String party, String url) {
        this.party = party;
        this.url = url;
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
