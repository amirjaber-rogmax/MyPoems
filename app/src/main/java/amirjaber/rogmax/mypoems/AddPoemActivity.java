package amirjaber.rogmax.mypoems;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import amirjaber.rogmax.mypoems.facades.MainFacade;
import amirjaber.rogmax.mypoems.models.PoemValue;
import amirjaber.rogmax.mypoems.retrofit.APIUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPoemActivity extends AppCompatActivity {

    private EditText poemTitle, poemText;
    private ProgressDialog progressDialog;
    private MainFacade controller;
    String name, poem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poem);

        init();
    }

    private void init() {
        poemTitle = findViewById(R.id.type_title);
        poemText = findViewById(R.id.type_poem);
        controller = APIUtils.getFacadeService();

    }

    public void savePoem(View view) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Adding....");
        progressDialog.show();

        name = poemTitle.getText().toString();
        poem = poemText.getText().toString();

        Call<PoemValue> call = controller.insert(name, poem);
        call.enqueue(new Callback<PoemValue>() {
            @Override
            public void onResponse(@NonNull Call<PoemValue> call, @NonNull Response<PoemValue> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();
                progressDialog.dismiss();

                if (value.equals("1")) {
                    Toast.makeText(AddPoemActivity.this, message, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddPoemActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(AddPoemActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<PoemValue> call, @NonNull Throwable t) {
                t.printStackTrace();
                Toast.makeText(AddPoemActivity.this, "an error occurred", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
