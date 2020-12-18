package com.example.WordRoomSample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


    public class WordViewModel extends AndroidViewModel {

        private WordRepository mRepository;

        private LiveData<List<com.example.WordRoomSample.Word>> mAllWords;

        public WordViewModel (Application application) {
            super(application);
            mRepository = new WordRepository(application);
            mAllWords = mRepository.getAllWords();
        }

        LiveData<List<com.example.WordRoomSample.Word>> getAllWords() { return mAllWords; }

        public void insert(com.example.WordRoomSample.Word word) { mRepository.insert(word); }
        public void deleteAll() {mRepository.deleteAll();}
        public void deleteWord(Word word) {mRepository.deleteWord(word);}
    }

