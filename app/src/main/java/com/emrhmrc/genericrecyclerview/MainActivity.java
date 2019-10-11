package com.emrhmrc.genericrecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

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

public class MainActivity extends AppCompatActivity implements IOnItemClickListener, IOnSwipe {

    private static final String TAG = "MainActivity";
    @BindView(R.id.generic_recylerview)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view)
    RelativeLayout emptyView;
    @BindView(R.id.searchView)
    SearchView searchView;

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

        for (int i = 0; i < 20; i++) {
            TestModel testModel = new TestModel();
            testModel.setCount(i + 1);
            testModel.setHeader("Header-" + i + 1);
            testModel.setFilterName("Header-" + i + 1);
            testModelList.add(testModel);
        }
        testAdapter.setItems(testModelList);

    }

    private void setAdapter() {
        testAdapter = new TestAdapter(this, this, emptyView);
        GRVHelper.setupWithSwipe(testAdapter, recyclerView, this);

    }

    @Override
    public void onItemClicked(Object item, int positon) {
        Log.d(TAG, "onItemClicked:Position:" + positon);
    }

    @Override
    public void OnSwipe(Object item, int position, int direction) {
        Log.d(TAG, "OnSwipe:Position:" + position + " Direction:" + direction);
    }
}
