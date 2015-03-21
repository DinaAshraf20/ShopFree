package com.example.omar.shopfree.ordernow;

import com.google.gson.annotations.Expose;

/**
 * Created by omar on 3/20/2015.
 */
public class OrderNowReply {

    @Expose
    private long responseId;

    /**
     *
     * @return
     * The responseId
     */
    public long getResponseId() {
        return responseId;
    }

    /**
     *
     * @param responseId
     * The responseId
     */
    public void setResponseId(long responseId) {
        this.responseId = responseId;
    }

}
