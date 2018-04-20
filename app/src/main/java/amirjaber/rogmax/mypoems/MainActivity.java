package amirjaber.rogmax.mypoems;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void addPoem(View view) {
        Intent goToAddPoemActivity = new Intent(MainActivity.this, AddPoemActivity.class);
        startActivity(goToAddPoemActivity);
        Log.d(TAG, "addPoem: poem has been added");
    }

    public void viewPoems(View view) {
        Intent goToViewActivity = new Intent(MainActivity.this, ViewActivity.class);
        startActivity(goToViewActivity);

    }

}
