package com.emrhmrc.genericrecyclerview.adapter;


import androidx.annotation.Nullable;

import com.emrhmrc.genericrecycler.adapters.BaseViewHolder;
import com.emrhmrc.genericrecycler.interfaces.IOnItemClickListener;
import com.emrhmrc.genericrecyclerview.TestModel;
import com.emrhmrc.genericrecyclerview.databinding.ItemTestBinding;

import butterknife.ButterKnife;

public class TestViewHolder extends BaseViewHolder<TestModel,
        IOnItemClickListener<TestModel>> {

    private ItemTestBinding binding;

    public TestViewHolder(ItemTestBinding binding) {
        super(binding.getRoot());
        ButterKnife.bind(this, binding.getRoot());
        this.binding = binding;

    }


    @Override
    public void onBind(final TestModel item, final @Nullable
            IOnItemClickListener<TestModel> listener) {
        binding.setItem(item);


    }


}
