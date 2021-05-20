package com.example.mvvmpayments.ui.main;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmpayments.BR;
import com.example.mvvmpayments.R;
import com.example.mvvmpayments.data.models.ApiResponse;
import com.example.mvvmpayments.data.models.ApplicableItem;
import com.example.mvvmpayments.databinding.ActivityMainBinding;
import com.example.mvvmpayments.ui.BaseNavigator;
import com.example.mvvmpayments.ui.ViewModelProviderFactory;
import com.example.mvvmpayments.util.HAlert;
import com.example.mvvmpayments.util.RecyclerBindingAdapter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class MainActivity extends AppCompatActivity implements BaseNavigator, RecyclerBindingAdapter.OnItemClickListener<Object> {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    private RecyclerBindingAdapter adapter;
    private List<ApplicableItem> listItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(binding.getRoot());

        viewModel = (MainViewModel) new ViewModelProviderFactory(new MainViewModel(getApplication(), this, this)).create(MainViewModel.class);

        //init recyclerView
        adapter = new RecyclerBindingAdapter(R.layout.list_item_payments, BR.network, (AbstractList<ApplicableItem>) listItems);
        adapter.setOnItemClickListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (viewModel != null) {
            viewModel.getPaymentTypes();
        }
    }

    @Override
    public void notifyUser(String text, NotifyType type) {
        switch (type) {

            case TYPE_TOAST:
                HAlert.showToast(this, text);
                break;
            case TYPE_ALERT:
                HAlert.showAlertDialog(this, getString(R.string.app_name), text);
                break;
            case TYPE_SNACKBAR:
                HAlert.showSnackBar(findViewById(android.R.id.content), text);
                break;
        }
    }

    @Override
    public void onNavigateBack(String className) {

    }

    @Override
    public void onNavigateForward(String className) {

    }

    @Override
    public void onAPIResponse(Object obj) {

        if (obj instanceof ApiResponse) {
            listItems.clear();

            listItems.addAll(((ApiResponse) obj).getNetworks().getApplicable());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(int position, Object item) {

    }
}
