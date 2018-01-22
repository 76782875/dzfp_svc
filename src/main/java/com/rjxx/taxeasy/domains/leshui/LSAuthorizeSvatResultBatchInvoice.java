package com.rjxx.taxeasy.domains.leshui;

/**
 * Created by wangyahui on 2018/1/9 0009.
 */
public class LSAuthorizeSvatResultBatchInvoice {
    private AuthHead head;
    private AuthBody[] body;

    public AuthHead getHead() {
        return head;
    }

    public void setHead(AuthHead head) {
        this.head = head;
    }

    public AuthBody[] getBody() {
        return body;
    }

    public void setBody(AuthBody[] body) {
        this.body = body;
    }
}

