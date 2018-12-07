package local.itracking;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import local.itracking.adapters.RecyclerAdapterUsers;
import local.itracking.configs.Globals;
import local.itracking.helpers.HelperSQLite;
import local.itracking.interfaces.InterfaceRetrofit;
import local.itracking.models.Email;
import local.itracking.models.User;
import local.itracking.repositories.RepositoryLogsSecurity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


//        ImageView viewUserPhoto =  findViewById(R.id.iViewUserPhoto);
        //    viewUserPhoto.setImageBitmap(users.get(0).getPhoto());

    }
    public void buscar(View view) throws InterruptedException {

        MyFirebaseInstanceIDService firebase = new MyFirebaseInstanceIDService();
        firebase.onTokenRefresh();


        }

}
