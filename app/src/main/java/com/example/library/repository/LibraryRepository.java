package com.example.library.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.library.Interface.LibraryDao;
import com.example.library.model.LibraryModel;
import com.example.library.room.LibraryRoomDataBase;

import java.util.List;

public class LibraryRepository {
    private LibraryDao libraryDao;
    private LibraryRoomDataBase libraryRoomDataBase;
    private LiveData<List<LibraryModel>> allLibrary;

    public LibraryRepository(Application application) {
        libraryRoomDataBase = LibraryRoomDataBase.getInstance(application);
        libraryDao = libraryRoomDataBase.libraryDao();
        allLibrary = libraryDao.getAllBook();
    }

    public void insert(LibraryModel libraryModel) {
        new insertAsyncTask(libraryDao).execute(libraryModel);
    }

    public void update(LibraryModel libraryModel) {
        new updateAsyncTask(libraryDao).execute(libraryModel);
    }

    public void delete(LibraryModel libraryModel) {
        new deleteAsyncTask(libraryDao).execute(libraryModel);
    }

    public LiveData<List<LibraryModel>> getAllLibrary() {
        return allLibrary;
    }

    //<editor-fold desc="--Insert--">
    private static class insertAsyncTask extends AsyncTask<LibraryModel, Void, Void> {
        private LibraryDao libraryDao;

        public insertAsyncTask(LibraryDao libraryDao) {
            this.libraryDao = libraryDao;
        }

        @Override
        protected Void doInBackground(LibraryModel... libraryModels) {
            libraryDao.insert(libraryModels[0]);
            return null;
        }
    }

    //</editor-fold>
    //<editor-fold desc="--Update--">
    private static class updateAsyncTask extends AsyncTask<LibraryModel, Void, Void> {
        private LibraryDao libraryDao;

        public updateAsyncTask(LibraryDao libraryDao) {
            this.libraryDao = libraryDao;
        }

        @Override
        protected Void doInBackground(LibraryModel... libraryModels) {
            libraryDao.update(libraryModels[0]);
            return null;
        }
    }

    //</editor-fold>
    //<editor-fold desc="--Delete--">
    private static class deleteAsyncTask extends AsyncTask<LibraryModel, Void, Void> {
        private LibraryDao libraryDao;

        public deleteAsyncTask(LibraryDao libraryDao) {
            this.libraryDao = libraryDao;
        }

        @Override
        protected Void doInBackground(LibraryModel... libraryModels) {
            libraryDao.delete(libraryModels[0]);
            return null;
        }
    }
    //</editor-fold>

}
