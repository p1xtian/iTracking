package local.itracking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import local.itracking.configs.Globals;
import local.itracking.interfaces.InterfaceRetrofit;
import local.itracking.models.Email;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
    }

    public void RecoveryPassword(View view) {

        Email emailToSend = new Email("Recuperacion de contraseña",
                "Usuario: " +"cmercado@ipsec.com.pe " + "Passoword: " + "123456","cmercado@ipsec.com.pe");

        try {
            SendEmail(emailToSend);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void SendEmail(Email email) throws InterruptedException {

        Globals globals = new Globals();
        try
        {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(globals.emailUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            InterfaceRetrofit services = retrofit.create(InterfaceRetrofit.class);

            Gson gson = new Gson();
            String json = gson.toJson(email);
            services.SendEmail(json).enqueue(new retrofit2.Callback<Void>() {


                @Override
                public void onResponse(Call call, Response response) {
                    Log.d("=EMAIL=>", "OK");
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    Log.d("=EMAIL=>", t.toString());
                }
            } );



        }
        catch (Exception ex)
        {
            Log.d("=iTracking=>",ex.toString());
        }


        Toast.makeText(getApplicationContext(),
                "Email Sent" ,
                Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
