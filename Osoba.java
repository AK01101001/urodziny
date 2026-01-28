package com.example.nowe;

import java.util.Calendar;

public class Osoba {
    public String name;
    public Calendar data;

    public Osoba(String name, Calendar data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String
        toString() {
        return name + " "+String.valueOf(data.get(Calendar.DAY_OF_MONTH))  + " " +String.valueOf(data.get(Calendar.MONTH)) ;
    }
}
