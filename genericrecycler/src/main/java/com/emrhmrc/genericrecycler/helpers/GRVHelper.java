package com.emrhmrc.genericrecycler.helpers;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emrhmrc.genericrecycler.adapters.BaseAdapterSwipe;
import com.emrhmrc.genericrecycler.adapters.GenericAdapter;
import com.emrhmrc.genericrecycler.interfaces.IOnSwipe;

public class GRVHelper {
    //Needs to be more usefull bu after this version :)

    public static void setup(GenericAdapter adapter,
                             @NonNull RecyclerView recyclerView,
                             RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public static void setup(GenericAdapter adapter,
                             @NonNull RecyclerView recyclerView
    ) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public static void setupWithSwipe(GenericAdapter adapter,
                                      @NonNull RecyclerView recyclerView, IOnSwipe iOnSwipe
    ) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(new BaseAdapterSwipe(adapter, iOnSwipe));
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }


}
