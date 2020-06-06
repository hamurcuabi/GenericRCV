package com.emrhmrc.genericrecycler.helpers;

import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emrhmrc.genericrecycler.R;
import com.emrhmrc.genericrecycler.adapters.BaseAdapterSwipe;
import com.emrhmrc.genericrecycler.adapters.GenericAdapter;
import com.emrhmrc.genericrecycler.interfaces.IOnSwipe;

public class GRVHelper {

    private static int resId = R.anim.layout_animation_fall_down;
    private static LayoutAnimationController animation;

    public static void setup(GenericAdapter adapter,
                             @NonNull RecyclerView recyclerView,
                             RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        animation = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), resId);
        recyclerView.setAdapter(adapter);
    }

    public static void setup(GenericAdapter adapter,
                             @NonNull RecyclerView recyclerView
    ) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        animation = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), resId);
        recyclerView.setAdapter(adapter);
    }

    public static void setupWithSwipe(GenericAdapter adapter,
                                      @NonNull RecyclerView recyclerView, IOnSwipe iOnSwipe
    ) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        animation = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new BaseAdapterSwipe(adapter, iOnSwipe));
        itemTouchhelper.attachToRecyclerView(recyclerView);

    }

    public static void setupWithSwipe(GenericAdapter adapter,
                                      @NonNull RecyclerView recyclerView, IOnSwipe iOnSwipe,
                                      ItemTouchHelper.SimpleCallback swipeAdapter
    ) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        animation = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeAdapter);
        itemTouchhelper.attachToRecyclerView(recyclerView);

    }
    public static int getResId() {
        return resId;
    }

    public static void setResId(int resId) {
        GRVHelper.resId = resId;
    }
}
