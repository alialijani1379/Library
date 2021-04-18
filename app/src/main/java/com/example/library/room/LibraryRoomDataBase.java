package com.example.library.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.library.Interface.LibraryDao;
import com.example.library.R;
import com.example.library.model.LibraryModel;

@Database(entities = {LibraryModel.class}, version = 5)
public abstract class LibraryRoomDataBase extends RoomDatabase {

    private static LibraryRoomDataBase libraryRoomDataBase;
    public abstract LibraryDao libraryDao();

    public static LibraryRoomDataBase getInstance(Context context) {
        if (libraryRoomDataBase == null) {
            libraryRoomDataBase = Room.databaseBuilder(context.getApplicationContext()
                    , LibraryRoomDataBase.class
                    , "library_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return libraryRoomDataBase;
    }

    //<editor-fold desc="Fake Data">
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAsyncTask(libraryRoomDataBase).execute();
        }
    };

    private static class populateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LibraryDao libraryDao;

        public populateDbAsyncTask(LibraryRoomDataBase dataBase) {
            this.libraryDao = dataBase.libraryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            libraryDao.insert(new LibraryModel("hard days", "Mohammad Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://cdn2.penguin.com.au/covers/400/9781760897208.jpg?w=690&h=344", 104, 2000));
            libraryDao.insert(new LibraryModel("Klara and the Sun", "Reza Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images-na.ssl-images-amazon.com/images/I/71SMJPH9AgL.jpg", 256, 3000));
            libraryDao.insert(new LibraryModel("The Committed", "Ali Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images-na.ssl-images-amazon.com/images/I/51Naw9HPuvL._SY291_BO1,204,203,200_QL40_ML2_.jpg", 114, 4000));
            libraryDao.insert(new LibraryModel("Let Me Tell You What I Mean", "Reza Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F6%2F2020%2F12%2F16%2Flet-me-tell-you-1-2000.jpg", 365, 5000));
            libraryDao.insert(new LibraryModel("My Year Abroad: A Novel", "Ali Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images-na.ssl-images-amazon.com/images/I/91HFMgnZCbL.jpg", 23, 6000));
            libraryDao.insert(new LibraryModel("How Beautiful We Were", "mohammad Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images-na.ssl-images-amazon.com/images/I/8112-anzl0L.jpg", 69, 7000));
            libraryDao.insert(new LibraryModel("Concrete Rose", "Reza Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images-na.ssl-images-amazon.com/images/I/81rRRmZZvZL.jpg", 114, 8000));
            libraryDao.insert(new LibraryModel("The Push", "Reza Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://m.media-amazon.com/images/I/41ZnhAyIW5L.jpg", 98, 9000));
            libraryDao.insert(new LibraryModel("The Beauty of Living Twice", "Reza Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images.penguinrandomhouse.com/cover/9780525656760", 79, 10000));
            libraryDao.insert(new LibraryModel("The Gilded Ones", "Reza Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images-na.ssl-images-amazon.com/images/I/81b6ahl1u+L.jpg", 154, 11000));
            libraryDao.insert(new LibraryModel("Harlem Shuffle: A Novel", "Reza Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images-na.ssl-images-amazon.com/images/I/71DtX66O1KL.jpg", 256, 12000));
            libraryDao.insert(new LibraryModel("The President's Daughter", "Reza Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images-na.ssl-images-amazon.com/images/I/71nA30To2EL.jpg", 312, 13000));
            libraryDao.insert(new LibraryModel("The Lost Apothecary", "Reza Alijani",
                    "A formidable storyteller' JONATHAN FRANZEN Set in the fictional African village of Kosawa, How Beautiful We Were tells the story of a people living in fear amidst environmental degradation wrought by an American oil company. Pipeline spills have rendered farmlands infertile. Children are dying from drinking toxic water. Promises of clean-up and financial reparations are made – and ignored. The country’s government, led by a brazen dictator, exists to serve its own interest only. Left with few choices, the people of Kosawa decide to fight back. But their fight will come at a steep price . . . one which generation after generation will have to pay. Told through the perspective of a generation of children and the family of a girl named Thula, How Beautiful We Were is a masterful exploration of what happens when the reckless drive for profit, coupled with the ghost of colonialism, comes up against one community’s determination to hold onto its ancestral land and a young woman’s willingness to sacrifice everything for the sake of her people’s freedom",
                    "https://images-na.ssl-images-amazon.com/images/I/51iqRHTe96L._SX327_BO1,204,203,200_.jpg", 114, 14000));
            return null;
        }
    }
    //</editor-fold>
}
