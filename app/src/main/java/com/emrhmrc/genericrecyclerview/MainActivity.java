package com.emrhmrc.genericrecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.emrhmrc.genericrecyclerview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btn.setOnClickListener(view -> {
            ExampleBottomSheetDialog bottomSheet = new ExampleBottomSheetDialog();
            bottomSheet.show(getSupportFragmentManager(), "exampleBottomSheet");
        });
    }


}
