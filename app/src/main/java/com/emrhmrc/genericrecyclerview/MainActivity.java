package com.emrhmrc.genericrecyclerview;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.emrhmrc.genericrecycler.helpers.GRVHelper;
import com.emrhmrc.genericrecycler.interfaces.BaseModel;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.genericrecycler.interfaces.IOnSwipe;
import com.emrhmrc.genericrecyclerview.adapter.TestAdapter;
import com.emrhmrc.genericrecyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IOnSwipe, IOnItemClickListener<TestModel> {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private TestAdapter testAdapter;
    private List<BaseModel> testModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setAdapter();
        prepareData();
    }

    private void prepareData() {
        testModelList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestModel testModel = new TestModel();
            testModel.setId(i);
            testModel.setCount(i + 1);
            testModel.setHeader("Header-" + i + 1);
            testModelList.add(testModel);
            testAdapter.add(testModel);
        }
        for (int i = 0; i < 10; i++) {
            TestModel testModel = new TestModel();
            testModel.setId(i);
            testModel.setCount(i + 1);
            testModel.setHeader("Ceader-" + i + 1);
            testModelList.add(testModel);
            testAdapter.add(testModel);
        }
        for (int i = 0; i < 10; i++) {
            TestModel testModel = new TestModel();
            testModel.setId(i);
            testModel.setCount(i + 1);
            testModel.setHeader("Deader-" + i + 1);
            testModelList.add(testModel);
            testAdapter.add(testModel);
        }
    }

    private void setAdapter() {

        testAdapter = new TestAdapter(this, this, null);
        // TestSwipeDragAdapter swipe = new TestSwipeDragAdapter(testAdapter, this);
        GRVHelper.setup(testAdapter, binding.genericRecylerview);


    }

    @Override
    public void OnSwipe(Object item, int position, int direction) {
        Log.d(TAG, "OnSwipe:Position:" + position + " Direction:" + direction);
        // testAdapter.remove(position);
    }


    @Override
    public void onItemClicked(TestModel item, int positon) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_LONG).show();
    }


}
