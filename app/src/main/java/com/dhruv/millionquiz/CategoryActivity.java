package com.dhruv.millionquiz;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY_ID = "categoryId";
    public static final String EXTRA_CATEGORY_NAME = "categoryName";
    private long mLastClickTime = 0;

    private static RecyclerView recyclerView;
    private static List<Category> categories;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        myOnClickListener = new MyOnClickListener(this);
        recyclerView = findViewById(R.id.recycler_view_category);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        categories = dbHelper.getAllCategories();
        adapter = new recyclerAdapter(this, categories);
        recyclerView.setAdapter(adapter);
    }

    private class MyOnClickListener implements View.OnClickListener {
        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        final MediaPlayer mp = MediaPlayer.create(CategoryActivity.this, R.raw.btnlowsound);


        @Override
        public void onClick(View v) {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000)
                return;
            mLastClickTime = SystemClock.elapsedRealtime();

            //clickValidate();
            mp.start();
            startQuiz(v);
        }
    }

    private void startQuiz(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        String name = categories.get(position).getName();
        int id = categories.get(position).getId();

        Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, id);
        intent.putExtra(EXTRA_CATEGORY_NAME, name);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        final MediaPlayer mp = MediaPlayer.create(CategoryActivity.this, R.raw.btnlowsound);
        mp.start();
        super.onBackPressed();
    }
}


