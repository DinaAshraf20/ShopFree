package com.example.omar.shopfree.login;

import com.google.gson.annotations.Expose;

/**
 * Created by Nayle on 3/20/2015.
 */
public class LoginReply {

    @Expose
    private long responseId;

    /**
     * @return The responseId
     */
    public long getResponseId() {
        return responseId;
    }

    /**
     * @param responseId The responseId
     */
    public void setResponseId(long responseId) {
        this.responseId = responseId;
    }

}

