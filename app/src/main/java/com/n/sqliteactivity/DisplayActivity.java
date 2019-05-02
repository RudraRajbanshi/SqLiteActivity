package com.n.sqliteactivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.MyHelper;
import model.Word;

public class DisplayActivity extends AppCompatActivity {
    private ListView lstWords;
    HashMap<String,String> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        lstWords = findViewById(R.id.lstWords);
        loadWord();

        lstWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                String meaning = hashMap.get(key);


                Intent intent = new Intent(DisplayActivity.this,MeaningActivity.class);
                intent.putExtra("meaning",meaning);
                startActivity(intent);
            }
        });
    }
    private void loadWord(){
        final MyHelper myHelper = new MyHelper(this);
        final SQLiteDatabase sqLiteDatabase = myHelper.getWritableDatabase();

        List<Word> wordList = new ArrayList<>();
        wordList = myHelper.GetAllWords(sqLiteDatabase);


        for (int i=0;i<wordList.size();i++){
            hashMap.put(wordList.get(i).getWord(),wordList.get(i).getMeaning());
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,new ArrayList<String>(hashMap.keySet())
        );
        lstWords.setAdapter(stringArrayAdapter);
    }
}
