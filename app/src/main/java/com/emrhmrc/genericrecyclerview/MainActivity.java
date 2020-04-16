package com.emrhmrc.genericrecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.emrhmrc.genericrecycler.helpers.GRVHelper;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.genericrecycler.interfaces.IOnSwipe;
import com.emrhmrc.genericrecyclerview.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IOnItemClickListener, IOnSwipe, AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.generic_recylerview)
    RecyclerView recyclerView;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.spinner)
    Spinner spinner;

    //Adapter
    TestAdapter testAdapter;
    //Our Model
    List<TestModel> testModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setAdapter();
        prepareData();
        setSearchView();
        setSpinner();
    }

    private void setSpinner() {
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<>();
        categories.add("Fall Down");
        categories.add("From Bottom");
        categories.add("From Right");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void setSearchView() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                testAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                testAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    private void prepareData() {
        testModelList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            TestModel testModel = new TestModel();
            testModel.setCount(i + 1);
            testModel.setHeader("Header-" + i + 1);
            testModel.setGenericFilterText("Header-" + i + 1);
            testModelList.add(testModel);
        }
        testAdapter.setItems(testModelList);

    }

    private void setAdapter() {
        testAdapter = new TestAdapter(this, this, null);
        GRVHelper.setup(testAdapter, recyclerView);

    }

    @Override
    public void onItemClicked(Object item, int positon) {
        Log.d(TAG, "onItemClicked:Position:" + positon);
    }

    @Override
    public void OnSwipe(Object item, int position, int direction) {
        Log.d(TAG, "OnSwipe:Position:" + position + " Direction:" + direction);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int resId = R.anim.layout_animation_fall_down;
        switch (i) {
            case 0:
                resId = R.anim.layout_animation_fall_down;
                break;
            case 1:
                resId = R.anim.layout_animation_from_bottom;
                break;
            case 2:
                resId = R.anim.layout_animation_from_right;
                break;

        }
        GRVHelper.setResId(resId);
        setAdapter();
        testAdapter.getItems().clear();
        setAdapter();
        prepareData();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
