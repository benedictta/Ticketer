package com.udinus.ticketer;

public class ticket_item {
    private String nama;
    private String noktp;

    public ticket_item(String xnama, String xnoktp)
    {
        nama = xnama;
        noktp = xnoktp;
    }

    public String getNama()
    {
        return nama;
    }

    public String getNoktp()
    {
        return noktp;
    }
}
