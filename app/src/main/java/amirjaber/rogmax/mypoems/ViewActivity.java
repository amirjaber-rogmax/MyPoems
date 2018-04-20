package amirjaber.rogmax.mypoems;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import amirjaber.rogmax.mypoems.adapters.RecyclerViewAdapter;
import amirjaber.rogmax.mypoems.facades.MainFacade;
import amirjaber.rogmax.mypoems.models.PoemModel;
import amirjaber.rogmax.mypoems.models.PoemValue;
import amirjaber.rogmax.mypoems.retrofit.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {

    // Vars
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private List<PoemModel> poemModelList;
    private RecyclerViewAdapter recyclerViewAdapter;
    private MainFacade controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPoemList();
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        poemModelList = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(this, poemModelList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);

        controller = APIUtils.getFacadeService();

        getPoemList();
    }

    private void getPoemList() {
        Call<PoemValue> call = controller.view();

        call.enqueue(new Callback<PoemValue>() {
            @Override
            public void onResponse(@NonNull Call<PoemValue> call, @NonNull Response<PoemValue> response) {
                String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);

                if (value.equals("1")) {
                    poemModelList = response.body().getResult();
                    recyclerViewAdapter = new RecyclerViewAdapter(ViewActivity.this, poemModelList);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }

            }

            @Override
            public void onFailure(@NonNull Call<PoemValue> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void goBack(View view) {
        Intent goBack = new Intent(ViewActivity.this, MainActivity.class);
        startActivity(goBack);
    }

}
