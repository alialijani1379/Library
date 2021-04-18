package com.example.library.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.library.model.LibraryModel;
import com.example.library.repository.LibraryRepository;

import java.util.List;

public class ViewModelLibrary extends AndroidViewModel {
    private LibraryRepository libraryRepository;
    private LiveData<List<LibraryModel>> allLibrary;

    public ViewModelLibrary(@NonNull Application application) {
        super(application);
        libraryRepository = new LibraryRepository(application);
        allLibrary = libraryRepository.getAllLibrary();
    }

    public void insert(LibraryModel libraryModel){
        libraryRepository.insert(libraryModel);
    }

    public void update(LibraryModel libraryModel){
        libraryRepository.update(libraryModel);
    }

    public void delete(LibraryModel libraryModel){
        libraryRepository.delete(libraryModel);
    }

    public LiveData<List<LibraryModel>> getAllLibrary() {
        return allLibrary;
    }

}
