package com.example.nowe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.SeekBar;

import com.example.nowe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayAdapter<Osoba> adapter;
    ArrayList<Osoba> osoby;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        osoby = new ArrayList<>();
        adapter = new ArrayAdapter<Osoba>(MainActivity.this, android.R.layout.simple_list_item_1,osoby);
        binding.confirm.setOnClickListener( view1 -> confirm());
        binding.date.setOnClickListener(view1 -> picker());
        binding.lista.setAdapter(adapter);
    }

    private void picker() {
        calendar = Calendar.getInstance();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    calendar.set(Calendar.DAY_OF_MONTH,i);
                    calendar.set(Calendar.MONTH,i1);
                    calendar.set(Calendar.YEAR,i2);
                }
            },2026,01,01);
            datePicker.show();
        }
    }

    private void confirm() {
        osoby.add(new Osoba(binding.name.getText().toString(),calendar));
        adapter.notifyDataSetChanged();
    }
}