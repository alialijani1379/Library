package com.example.library.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.ListenerDeleteLibrary;
import com.example.library.Interface.ListenerLibrary;
import com.example.library.Interface.ListenerUpdateLibrary;
import com.example.library.R;
import com.example.library.databinding.ItemLibraryBinding;
import com.example.library.model.LibraryModel;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder> {

    Context context;
    List<LibraryModel> libraryModels;
    ListenerLibrary listenerLibrary;
    ListenerDeleteLibrary listenerDelete;
    ListenerUpdateLibrary listenerUpdate;
    LayoutInflater layoutInflater;

    public LibraryAdapter(Context context, List<LibraryModel> libraryModels, ListenerLibrary listenerLibrary, ListenerDeleteLibrary listenerDelete, ListenerUpdateLibrary listenerUpdate) {
        this.context = context;
        this.listenerLibrary = listenerLibrary;
        this.libraryModels = libraryModels;
        this.listenerDelete = listenerDelete;
        this.listenerUpdate = listenerUpdate;
    }

    @NonNull
    @Override
    public LibraryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context);
        }
        ItemLibraryBinding libraryBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_library, parent, false);
        return new LibraryViewHolder(libraryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryViewHolder holder, int position) {
        LibraryModel libraryModel = libraryModels.get(position);
        holder.itemLibraryBinding.setLibrary(libraryModel);
        holder.itemView.setOnClickListener(v -> listenerLibrary.Listener(libraryModel,holder.itemLibraryBinding.imgItem));
        holder.setDelete();
        holder.setUpdate();
    }

    public void setLibrary(List<LibraryModel> library) {
        this.libraryModels = library;
        notifyDataSetChanged();
    }

    public LibraryModel getLibraryAt(int position) {
        return libraryModels.get(position);
    }

    @Override
    public int getItemCount() {
        return libraryModels.size();
    }

    class LibraryViewHolder extends RecyclerView.ViewHolder {
        ItemLibraryBinding itemLibraryBinding;
        private CardView cvRemove, cvUpdate;

        public LibraryViewHolder(ItemLibraryBinding itemLibraryBinding) {
            super(itemLibraryBinding.getRoot());
            this.itemLibraryBinding = itemLibraryBinding;
            cvRemove = itemView.findViewById(R.id.cv_remove);
            cvUpdate = itemView.findViewById(R.id.cv_update);

        }

        public void setDelete() {
            cvRemove.setOnClickListener(v -> {
                listenerDelete.onListenerDelete(libraryModels.get(getAdapterPosition()));
                notifyItemRemoved(getAdapterPosition());
            });
        }

        public void setUpdate() {
            cvUpdate.setOnClickListener(v -> listenerUpdate.onListenerUpdate(libraryModels.get(getAdapterPosition())));
        }
    }
}
