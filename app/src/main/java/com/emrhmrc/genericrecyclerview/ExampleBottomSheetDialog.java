package com.emrhmrc.genericrecyclerview;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.emrhmrc.genericrecycler.helpers.GRVHelper;
import com.emrhmrc.genericrecycler.interfaces.BaseModel;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.genericrecycler.interfaces.IOnSwipe;
import com.emrhmrc.genericrecyclerview.adapter.TestAdapter;
import com.emrhmrc.genericrecyclerview.adapter.TestSwipeDragAdapter;
import com.emrhmrc.genericrecyclerview.databinding.FragmentExampleBottomsheetDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hamurcuabi on 05,October,2020
 **/
public class ExampleBottomSheetDialog extends BottomSheetDialogFragment implements IOnSwipe, IOnItemClickListener<TestModel> {

    private static final String TAG = "ExampleBottomSheetDialo";
    //Adapter
    private TestAdapter testAdapter;
    //Our Model
    private List<BaseModel> testModelList;
    private FragmentExampleBottomsheetDialogBinding binding;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExampleBottomsheetDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setAdapter();
        prepareData();
        GRVHelper.setAlphabeticSection(testModelList, binding.genericRecylerview, true);
      /*  binding.genericRecylerview.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                if (motionEvent.getY() <= 32) {
                    // Handle the clicks on the header here ...
                    return true;
                }
                return false;
            }

            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/
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

        testAdapter = new TestAdapter(requireContext(), this, null);
        TestSwipeDragAdapter swipe = new TestSwipeDragAdapter(testAdapter, this);
        GRVHelper.setupWithSwipe(testAdapter, binding.genericRecylerview, swipe);


    }

    @Override
    public void OnSwipe(Object item, int position, int direction) {
        Log.d(TAG, "OnSwipe:Position:" + position + " Direction:" + direction);
        // testAdapter.remove(position);
    }


    @Override
    public void onItemClicked(TestModel item, int positon) {
        Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_LONG).show();
        dismiss();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(dialog1 -> {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            BottomSheetDialog d = (BottomSheetDialog) dialog1;
            FrameLayout bottomSheet = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                bottomSheet.getLayoutParams().height = height / 2;
            }
        });

        return dialog;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

}
