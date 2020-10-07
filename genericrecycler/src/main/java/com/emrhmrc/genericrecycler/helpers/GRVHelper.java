package com.emrhmrc.genericrecycler.helpers;

import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emrhmrc.genericrecycler.R;
import com.emrhmrc.genericrecycler.adapters.BaseSwipeDragAdapter;
import com.emrhmrc.genericrecycler.adapters.GenericAdapter;
import com.emrhmrc.genericrecycler.interfaces.BaseModel;
import com.emrhmrc.genericrecycler.interfaces.IOnSwipe;
import com.emrhmrc.genericrecycler.util.Utils;

import java.util.List;

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

    public static void setupWithSwipe(GenericAdapter adapter, @NonNull RecyclerView recyclerView,
                                      IOnSwipe iOnSwipe, final int swipeDirs) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        animation = AnimationUtils.loadLayoutAnimation(recyclerView.getContext(), resId);
        recyclerView.setLayoutAnimation(animation);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new BaseSwipeDragAdapter(adapter,
                iOnSwipe, swipeDirs));
        itemTouchhelper.attachToRecyclerView(recyclerView);

    }

    public static void setupWithSwipe(GenericAdapter adapter,
                                      @NonNull RecyclerView recyclerView,
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

    public static void setAlphabeticSection(List<BaseModel> baseModels, RecyclerView recyclerView, boolean isSticky) {
        RecyclerSectionItemDecoration sectionItemDecoration =
                new RecyclerSectionItemDecoration(Utils.pxFromDp(recyclerView.getContext(), 32f),
                        isSticky,
                        new RecyclerSectionItemDecoration.SectionCallback() {
                            @Override
                            public boolean isSection(int position) {
                                return position == 0
                                        || baseModels.get(position)
                                        .getFilterText()
                                        .charAt(0) != baseModels.get(position - 1)
                                        .getFilterText()
                                        .charAt(0);
                            }

                            @Override
                            public CharSequence getSectionHeader(int position) {
                                return baseModels.get(position)
                                        .getFilterText()
                                        .subSequence(0, 1);
                            }
                        });
        recyclerView.addItemDecoration(sectionItemDecoration);
    }

    public static int getResId() {
        return resId;
    }

    public static void setResId(int resId) {
        GRVHelper.resId = resId;
    }
}
