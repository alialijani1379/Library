package com.example.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.ListenerLibrary;
import com.example.library.R;
import com.example.library.databinding.ItemSearchBinding;
import com.example.library.model.LibraryModel;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private Context context;
    private List<LibraryModel> libraryModels;
    private ListenerLibrary listenerLibrary;
    private LayoutInflater layoutInflater;

    public SearchAdapter(Context context, List<LibraryModel> libraryModels, ListenerLibrary listenerLibrary) {
        this.context = context;
        this.libraryModels = libraryModels;
        this.listenerLibrary = listenerLibrary;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context);
        }
        ItemSearchBinding searchBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_search, parent, false);
        return new SearchViewHolder(searchBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        LibraryModel libraryModel = libraryModels.get(position);
        holder.itemSearchBinding.setSearch(libraryModel);
        holder.itemView.setOnClickListener(v -> listenerLibrary.Listener(libraryModel, holder.itemSearchBinding.imgSearch));
    }

    public void setLibrary(List<LibraryModel> library) {
        this.libraryModels = library;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return libraryModels.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ItemSearchBinding itemSearchBinding;

        public SearchViewHolder(ItemSearchBinding itemSearchBinding) {
            super(itemSearchBinding.getRoot());
            this.itemSearchBinding = itemSearchBinding;
        }
    }

    public void filterList(List<LibraryModel> filterList) {
        libraryModels = filterList;
        notifyDataSetChanged();
    }
}
