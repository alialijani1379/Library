package com.example.library.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.ListenerLibrary;
import com.example.library.R;
import com.example.library.activity.DetailsActivity;
import com.example.library.adapter.SearchAdapter;
import com.example.library.databinding.FragmentSearchBinding;
import com.example.library.model.LibraryModel;
import com.example.library.viewmodel.ViewModelLibrary;

import java.util.ArrayList;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;

public class SearchFragment extends Fragment implements ListenerLibrary {
    //<editor-fold desc="--Declaration--">
    private FragmentSearchBinding searchBinding;
    private EditText edtSearch;
    private ShimmerLayout shimmerLayout;
    private RecyclerView rvSearch;
    private SearchAdapter searchAdapter;
    private List<LibraryModel> modelList;
    private ViewModelLibrary viewModelLibrary;
    //</editor-fold>
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        searchBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        bindViews(searchBinding);

        shimmerLayout.startShimmerAnimation();
        modelList = new ArrayList<>();
        rvSearch.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvSearch.setHasFixedSize(true);
        searchAdapter = new SearchAdapter(getActivity(), modelList, this);
        rvSearch.setAdapter(searchAdapter);
        viewModelLibrary = new ViewModelProvider(getActivity()).get(ViewModelLibrary.class);
        viewModelLibrary.getAllLibrary().observe(getActivity(), libraryModels -> {
            searchAdapter.setLibrary(libraryModels);

            edtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s.toString().isEmpty()) {
                    } else {
                        filter(s.toString());
                    }
                }
            });

        });
        shimmerLayout.stopShimmerAnimation();
        shimmerLayout.setVisibility(View.GONE);

        return searchBinding.getRoot();
    }

    private void filter(String text) {
        List<LibraryModel> filterList = new ArrayList<>();
        for (LibraryModel model : modelList) {
            if (model.getWriter().toLowerCase().contains(text.toLowerCase())) {
                filterList.add(model);
            }
        }
        searchAdapter.filterList(filterList);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void Listener(LibraryModel libraryModel, ImageView imageView) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("bookName", libraryModel.getBook());
        intent.putExtra("writerName", libraryModel.getWriter());
        intent.putExtra("pages", libraryModel.getPages());
        intent.putExtra("price", libraryModel.getPrice());
        intent.putExtra("desc", libraryModel.getDesc());
        intent.putExtra("img", libraryModel.getUrl());
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(imageView, "img");
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity(), pairs);
        startActivity(intent, activityOptions.toBundle());
    }

    private void bindViews(FragmentSearchBinding searchBinding) {
        edtSearch = searchBinding.edtSearch;
        rvSearch = searchBinding.rvSearch;
        shimmerLayout = searchBinding.shimmerSearch;
    }

}