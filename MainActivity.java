package com.example.nowe;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nowe.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ArrayAdapter<Osoba> adapter;
    ArrayList<Osoba> osoby;
    ArrayList<Osoba> osobyToShow;

    ArrayAdapter<String> adapterMiesiace;
    String[] miesiace = new String[]
    {
    "Wszystkie",
    "Styczeń",
    "Luty",
    "Marzec",
    "Kwiecień",
    "Maj",
    "Czerwiec",
    "Lipiec",
    "Sierpień",
    "Wrzesień",
    "Październik",
    "Listopad",
    "Grudzień"};
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        osoby = new ArrayList<>();
        osobyToShow = new ArrayList<>();
        adapter = new ArrayAdapter<Osoba>(MainActivity.this, android.R.layout.simple_list_item_1,osobyToShow);
        adapterMiesiace = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,miesiace);
        binding.confirm.setOnClickListener( view1 -> confirm());
        binding.date.setOnClickListener(view1 -> picker());
        binding.lista.setAdapter(adapter);
        binding.spinner.setAdapter(adapterMiesiace);
        binding.spinner.setSelection(0);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ShowList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ShowList();
            }
        });
    }

    private void ShowList()
    {
        if (binding.spinner.getSelectedItemPosition()==0)
        {
            osobyToShow = osoby;
        }
        else {
            osobyToShow = new ArrayList<>();
            for (Osoba osoba :
                    osoby) {
                if (osoba.data.get(Calendar.MONTH) == binding.spinner.getSelectedItemPosition()-1) {
                    osobyToShow.add(osoba);
                }
            }
        }
        adapter = new ArrayAdapter<Osoba>(MainActivity.this, android.R.layout.simple_list_item_1,osobyToShow);
        binding.lista.setAdapter(adapter);


    }

    private void picker() {
        calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.YEAR, i);
            }
        }, 2026, 0, 1);
        datePicker.show();
    }

    private void confirm() {
        if (calendar==null)
        {
            calendar = Calendar.getInstance();
        }
        osoby.add(new Osoba(binding.name.getText().toString(),calendar));
        ShowList();
    }
}