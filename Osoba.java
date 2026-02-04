package com.example.nowe;

import androidx.annotation.NonNull;

import java.util.Calendar;

public class Osoba {
    public String name;
    public Calendar data;

    public Osoba(String name, Calendar data) {
        this.name = name;
        this.data = data;
    }

    @NonNull
    @Override
    public String
    toString() {
        return name + " " + data.get(Calendar.DAY_OF_MONTH) + "." + (data.get(Calendar.MONTH)+1);
    }
}