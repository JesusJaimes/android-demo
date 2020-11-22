package com.sushi.androidemo.View.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.sushi.androidemo.R;
import com.sushi.androidemo.View.Adapters.JokesListAdapter;
import com.sushi.androidemo.ViewModel.JokeViewModel;

import java.util.ArrayList;

public class JokesListActivity extends AppCompatActivity {

    Button buttonGoBack;
    RecyclerView jokesList;
    JokeViewModel jokeViewModel;
    JokesListAdapter jokesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_list);
        setUpButtons();
        initJokeList();
        initSavedJokesViewModel();
    }

    public void setUpButtons() {
        buttonGoBack = findViewById(R.id.goBack);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                openJokeActivity();
            }
        });
    }

    public void initJokeList(){
        jokesList = findViewById(R.id.jokes_list);
        jokesListAdapter = new JokesListAdapter(new JokesListAdapter.JokeDiff());
        jokesList.setAdapter(jokesListAdapter);
        jokesList.setLayoutManager(new LinearLayoutManager(this));

    }

    public void initSavedJokesViewModel(){
        Intent intent = getIntent();
        jokeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(JokeViewModel.class);
        jokeViewModel.getAllJokes().observe(this, jokes -> {
            jokesListAdapter.submitList(jokes);
        });
    }

    public void openJokeActivity(){
        onBackPressed();
    }
}