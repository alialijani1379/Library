package com.example.library.Interface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.library.model.LibraryModel;

import java.util.List;

@Dao
public interface LibraryDao {

    @Query("SELECT * FROM Library")
    LiveData<List<LibraryModel>> getAllBook();

    @Insert
    void insert(LibraryModel libraryModel);

    @Update
    void update(LibraryModel libraryModel);

    @Delete
    void delete(LibraryModel libraryModel);

}
