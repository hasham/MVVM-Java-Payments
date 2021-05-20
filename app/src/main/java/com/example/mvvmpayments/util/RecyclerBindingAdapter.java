package com.example.mvvmpayments.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.AbstractList;
import java.util.ArrayList;

public class RecyclerBindingAdapter extends RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder> {

    private int holderLayout;
    private int variableId;
    private AbstractList<?> items;
    private OnItemClickListener<Object> onItemClickListener = null;

    public RecyclerBindingAdapter(int holderLayout, int variableId, AbstractList<?> items) {
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerBindingAdapter.BindingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(holderLayout, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBindingAdapter.BindingHolder holder, int position) {
        Object item = items.get(position);

        holder.binding.getRoot().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position, item);
            }
        });

        holder.binding.setVariable(variableId, item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(OnItemClickListener<Object> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void updateList(AbstractList<Object> list) {
        this.items = list;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener<Object> {
        void onItemClick(int position, Object item);
    }

    static class BindingHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        BindingHolder(View v) {
            super(v);
            this.binding = DataBindingUtil.bind(v);
        }

    }
}
