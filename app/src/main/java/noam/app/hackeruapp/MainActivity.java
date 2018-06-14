package noam.app.hackeruapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind recyclerView and set Design
        RecyclerView recycViewData = findViewById(R.id.recycViewData);
        recycViewData.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycViewData.setLayoutManager(mLayoutManager);
        recycViewData.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // sets the RecyclerView Data
        new InitListData(recycViewData);
    }
}
