package com.madinfotech.orderbox.network.data;

/**
 * Created by prathameshkesarkar on 12/07/16.
 */
public class ResponseUser {
    private int id;
    private String email;
    private String updateAt;
    private String createdAt;
    private String header="";

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }
}
