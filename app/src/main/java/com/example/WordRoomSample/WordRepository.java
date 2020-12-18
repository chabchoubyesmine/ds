package com.example.WordRoomSample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;



import java.util.List;

public class WordRepository {

    private com.example.WordRoomSample.WordDao mWordDao;
    private LiveData<List<com.example.WordRoomSample.Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    LiveData<List<com.example.WordRoomSample.Word>> getAllWords() {
        return mAllWords;
    }

    public void insert (com.example.WordRoomSample.Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<com.example.WordRoomSample.Word, Void, Void> {

        private com.example.WordRoomSample.WordDao mAsyncTaskDao;

        insertAsyncTask(com.example.WordRoomSample.WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final com.example.WordRoomSample.Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }

    }
    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mWordDao).execute();
    }
    private static class deleteWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao mAsyncTaskDao;

        deleteWordAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }
    public void deleteWord(Word word)  {
        new deleteWordAsyncTask(mWordDao).execute(word);
    }
}