package com.example.library.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.ListenerDeleteLibrary;
import com.example.library.Interface.ListenerLibrary;
import com.example.library.Interface.ListenerUpdateLibrary;
import com.example.library.R;
import com.example.library.activity.DetailsActivity;
import com.example.library.activity.EditAddActivity;
import com.example.library.adapter.LibraryAdapter;
import com.example.library.model.LibraryModel;
import com.example.library.viewmodel.ViewModelLibrary;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.supercharge.shimmerlayout.ShimmerLayout;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements View.OnClickListener, ListenerDeleteLibrary, ListenerUpdateLibrary, ListenerLibrary {
    //<editor-fold desc="--Declaration--">
    public static final int ADD_REQUEST = 1;
    public static final int UPDATE_REQUEST = 2;
    private ViewModelLibrary viewModelLibrary;
    private RecyclerView recyclerView;
    private ShimmerLayout shimmerLayout;
    private LibraryAdapter libraryAdapter;
    private List<LibraryModel> libraryModels;
    private FloatingActionButton fb;
    private String book, writer, desc, price, url;
    private int pages;
    //</editor-fold>
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findViews(view);
        fb.setOnClickListener(this);

        shimmerLayout.startShimmerAnimation();

        libraryModels = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        libraryAdapter = new LibraryAdapter(getActivity(), libraryModels, this, this, this);
        recyclerView.setAdapter(libraryAdapter);

        viewModelLibrary = new ViewModelProvider(getActivity()).get(ViewModelLibrary.class);
        viewModelLibrary.getAllLibrary().observe(getActivity(), libraryModels -> libraryAdapter.setLibrary(libraryModels));

        shimmerLayout.stopShimmerAnimation();
        shimmerLayout.setVisibility(View.GONE);

        //<editor-fold desc="--TouchHelper--">
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModelLibrary.delete(libraryAdapter.getLibraryAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Book Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        //</editor-fold>

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_REQUEST && resultCode == RESULT_OK) {
            book = data.getStringExtra(EditAddActivity.BOOK);
            writer = data.getStringExtra(EditAddActivity.WRITER);
            desc = data.getStringExtra(EditAddActivity.DESC);
            price = data.getStringExtra(EditAddActivity.PRICE);
            int price2 = Integer.parseInt(price);
            url = data.getStringExtra(EditAddActivity.URL);
            pages = data.getIntExtra(EditAddActivity.PAGES, 1);
            LibraryModel libraryModel = new LibraryModel(book, writer, desc, url, pages, price2);
            viewModelLibrary.insert(libraryModel);
            Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();

        } else if (requestCode == UPDATE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EditAddActivity.ID, -1);

            if (id == -1) {
                Toast.makeText(getActivity(), "Book Can't be Updated", Toast.LENGTH_SHORT).show();
                return;
            }
            book = data.getStringExtra(EditAddActivity.BOOK);
            writer = data.getStringExtra(EditAddActivity.WRITER);
            desc = data.getStringExtra(EditAddActivity.DESC);
            price = data.getStringExtra(EditAddActivity.PRICE);
            int price2 = Integer.parseInt(price);
            url = data.getStringExtra(EditAddActivity.URL);
            pages = data.getIntExtra(EditAddActivity.PAGES, 1);

            LibraryModel libraryModel = new LibraryModel(book, writer, desc, url, pages, price2);
            libraryModel.setId(id);
            viewModelLibrary.update(libraryModel);
            Toast.makeText(getActivity(), "Book Updated", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "Not Saved", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onListenerDelete(LibraryModel libraryModel) {
        viewModelLibrary.delete(libraryModel);
    }

    @Override
    public void onListenerUpdate(LibraryModel libraryModel) {
        Intent intent = new Intent(getActivity(), EditAddActivity.class);
        intent.putExtra(EditAddActivity.ID, libraryModel.getId());
        intent.putExtra("getBook", libraryModel.getBook());
        intent.putExtra("getName", libraryModel.getWriter());
        intent.putExtra("getDesc", libraryModel.getDesc());
        intent.putExtra("getPages", libraryModel.getPages());
        intent.putExtra("getPrice", libraryModel.getPrice());
        intent.putExtra("getUrl", libraryModel.getUrl());
        startActivityForResult(intent, UPDATE_REQUEST);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fb:
                Intent intent = new Intent(getActivity(), EditAddActivity.class);
                startActivityForResult(intent, ADD_REQUEST);
                break;
        }
    }

    private void findViews(View view) {
        recyclerView = view.findViewById(R.id.rv_library);
        fb = view.findViewById(R.id.fb);
        shimmerLayout = view.findViewById(R.id.shimmer_library);
    }

}