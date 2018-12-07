package local.itracking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import local.itracking.configs.Globals;
import local.itracking.helpers.HelperBase64;
import local.itracking.helpers.HelperLogSecurity;
import local.itracking.helpers.HelperSQLite;
import local.itracking.interfaces.InterfaceRetrofit;
import local.itracking.models.Client;
import local.itracking.models.Email;
import local.itracking.models.Order;
import local.itracking.models.User;
import local.itracking.repositories.RepositoryLogsSecurity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        //Anokis
        MainFragment.OnFragmentInteractionListener,
        UsersFragment.OnFragmentInteractionListener,
        ClientsFragment.OnFragmentInteractionListener,
        OrdersFragment.OnFragmentInteractionListener,
        ReportsFragment.OnFragmentInteractionListener,
        DebugFragment.OnFragmentInteractionListener,
        RegisterUsersFragment.OnFragmentInteractionListener,
        UpdateUsersFragment.OnFragmentInteractionListener,
        RegisterClientsFragment.OnFragmentInteractionListener,
        UpdateClientsFragment.OnFragmentInteractionListener,
        RegisterOrdersFragment.OnFragmentInteractionListener,
        UpdateOrdersFragment.OnFragmentInteractionListener,
        MapsFragment.OnFragmentInteractionListener,
        RegisterDeliveryFragment.OnFragmentInteractionListener


{

    //Anokis
    HelperLogSecurity helperLogSecurity;
    MainFragment mainFragment;
    UsersFragment usersFragment;
    ClientsFragment clientsFragment;
    OrdersFragment ordersFragment;
    ReportsFragment reportsFragment;
    DebugFragment debugFragment;
    RepositoryLogsSecurity repository;
    RegisterUsersFragment registerUsersFragment;
    UpdateUsersFragment updateUsersFragment;
    RegisterClientsFragment registerClientsFragment;
    UpdateClientsFragment updateClientsFragment;
    RegisterOrdersFragment registerOrdersFragment;
    UpdateOrdersFragment updateOrdersFragment;
    MapsFragment mapsFragment;
    RegisterDeliveryFragment registerDeliveryFragment;

    Globals globals = new Globals();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Report Bug", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Anokis
        mainFragment = new MainFragment();
        usersFragment = new UsersFragment();
        clientsFragment = new ClientsFragment();
        ordersFragment = new OrdersFragment();
        reportsFragment = new ReportsFragment();
        debugFragment = new DebugFragment();
        registerUsersFragment = new RegisterUsersFragment();
        updateUsersFragment = new UpdateUsersFragment();
        registerClientsFragment = new RegisterClientsFragment();
        updateClientsFragment = new UpdateClientsFragment();
        registerOrdersFragment = new RegisterOrdersFragment();
        updateOrdersFragment = new UpdateOrdersFragment();
        mapsFragment = new MapsFragment();
        registerDeliveryFragment = new RegisterDeliveryFragment();

         helperLogSecurity =
                new HelperLogSecurity(new HelperSQLite(
                        this,
                        "iTracking",
                        null,
                        1));

        Toast.makeText(getApplicationContext(),
                "Welcome, " + LoadPreferences("email") ,
                Toast.LENGTH_SHORT).show();

        Log.d("=iTracking=>",LoadPreferences("firstname"));
        Log.d("=iTracking=>",LoadPreferences("lastname"));
        Log.d("=iTracking=>",LoadPreferences("email"));
        Log.d("=iTracking=>",LoadPreferences("photo"));
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,mainFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        Log.d("==Main==>",LoadPreferences("photo"));
        TextView userLongName = (TextView) findViewById(R.id.userName);
        userLongName.setText(LoadPreferences("firstname") +" " + LoadPreferences("lastname"));
        TextView account = (TextView) findViewById(R.id.userAccount);
        account.setText(LoadPreferences("email"));

        Log.d("==PHOTO==>",LoadPreferences("photo"));
        byte[] byteCode = Base64.decode(LoadPreferences("photo"), Base64.DEFAULT);
        ImageView  userPhoto = (ImageView) findViewById(R.id.userPhoto);
        userPhoto.setImageBitmap(BitmapFactory.decodeByteArray(byteCode, 0, byteCode.length));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_users) {
            // Handle the camera action
            GotoUsersFragment();
        } else if (id == R.id.nav_clients) {
            // Handle the camera action
            GotoClientsFragment();
        } else if (id == R.id.nav_orders) {
            GotoOrdersFragment();

        } else if (id == R.id.nav_reports) {
            GotoReportsFragment();

        } else if (id == R.id.nav_debug) {
            GotoDebugFragment();

        }

        else if (id == R.id.nav_change_passsword) {
            GoToChangePasswordActivity();
        }

        else if (id == R.id.nav_log_out) {
            GoToLoginActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //Methods
    public String LoadPreferences(String key)
    {
        SharedPreferences preferences =
                getSharedPreferences("credentials",
                        this.MODE_PRIVATE);
        return preferences.getString(key,"No Data");
    }


    //GoTo
    public void GoToLoginActivity() {
        helperLogSecurity.InsertLog("0","User: ( Start Login Activity )");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),
                "Sign Out Done",
                Toast.LENGTH_SHORT).show();
    }

    public void GotoMainFragment() {

        helperLogSecurity.InsertLog("0","User: ( Start Main Activity )");
        Toast.makeText(getApplicationContext(),
                "Welcome, " + LoadPreferences("account") ,
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,mainFragment).commit();
        setTitle("iTracking");
    }

    public void GotoUsersFragment() {

        helperLogSecurity.InsertLog("0","User: ( Start Users Activity )");
        Toast.makeText(getApplicationContext(),
                "Users",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,usersFragment).commit();
        setTitle("Users");
    }

    public void GotoClientsFragment() {

        helperLogSecurity.InsertLog("0","User: ( Start Clients Activity )");
        Toast.makeText(getApplicationContext(),
                "Clients",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,clientsFragment).commit();
        setTitle("Clients");
    }

    public void GotoOrdersFragment() {

        helperLogSecurity.InsertLog("0","User: ( Start Orders Activity )");
        Toast.makeText(getApplicationContext(),
                "Orders",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,ordersFragment).commit();
        setTitle("Orders");
    }

    public void GotoReportsFragment() {

        helperLogSecurity.InsertLog("0","User: ( Start Reports Activity )");
        Toast.makeText(getApplicationContext(),
                "Reports",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,reportsFragment).commit();
        setTitle("Reports");
    }

    public void GotoDebugFragment() {

        helperLogSecurity.InsertLog("0","User: ( Start Debug Activity )");
        Toast.makeText(getApplicationContext(),
                "Debug",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,debugFragment).commit();
        setTitle("Debug");
    }

    public void GoToChangePasswordActivity() {

        helperLogSecurity.InsertLog("0","User: ( Start ChangePassword Activity )");
        Toast.makeText(getApplicationContext(),
                "Change Password",
                Toast.LENGTH_SHORT).show();
        View view = new View(this);
        GoToUpdateUserFragment(view);
        //getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,registerDeliveryFragment).commit();
    }

    public void GoToRegisterUserFragment() {

        helperLogSecurity.InsertLog("0","User: ( Start RegisterUserFragment)");
        Toast.makeText(getApplicationContext(),
                "Register User",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,registerUsersFragment).commit();
        setTitle("Register User");
    }

    public void GoToRegisterUserFragment(View view) {

        helperLogSecurity.InsertLog("0","User: ( Start RegisterUserFragment)");
        Toast.makeText(getApplicationContext(),
                "Register User",
                Toast.LENGTH_SHORT).show();

        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,registerUsersFragment).commit();
        setTitle("Register User");
    }

    public void GoToUpdateUserFragment(View view) {
        helperLogSecurity.InsertLog("0","User: ( Start UpdateUserFragment )");
        Toast.makeText(getApplicationContext(),
                "Update User",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,updateUsersFragment).commit();
        setTitle("Update User");
    }

    public void GoToRegisterClientFragment(View view) {
        helperLogSecurity.InsertLog("0","User: ( Start RegisterClientFragment )");
        Toast.makeText(getApplicationContext(),
                "Register Client",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,registerClientsFragment).commit();
        setTitle("Register Client");
    }

    public void GoToUpdateClientFragment(View view) {
        helperLogSecurity.InsertLog("0","User: ( Start UpdateClientFragment )");
        Toast.makeText(getApplicationContext(),
                "Update Client",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,updateClientsFragment).commit();
        setTitle("Update Client");
    }

    public void GoToRegisterOrderFragment(View view) {
        helperLogSecurity.InsertLog("0","User: ( Start RegisterOrderFragment )");
        Toast.makeText(getApplicationContext(),
                "Register Order",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,registerOrdersFragment).commit();
        setTitle("Register Order");
    }

    public void GoToUpdateOrderFragment(View view) {

        helperLogSecurity.InsertLog("0","User: ( Start UpdateOrderFragment )");
        Toast.makeText(getApplicationContext(),
                "Update Order",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,updateOrdersFragment).commit();
        setTitle("Update Order");
    }

    public void GoToMaps(View view) {
        helperLogSecurity.InsertLog("0","User: ( Start MapsActivity )");
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),
                "Select Address",
                Toast.LENGTH_SHORT).show();
    }

    public void GoToOrder(View view) {
        Toast.makeText(getApplicationContext(),
                "Order Select",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,registerDeliveryFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //Fragment User Methods

    public void RegisterUser(View view) throws InterruptedException {

        Integer errors = 0;

        try
        {
            EditText firstName = findViewById(R.id.FirstName);
            EditText lastName = findViewById(R.id.LastName);
            EditText dni = findViewById(R.id.DNICEX);
            final EditText email = findViewById(R.id.Email);
            EditText password = findViewById(R.id.Password);
            EditText password2 = findViewById(R.id.PasswordRepeat);
            EditText phone = findViewById(R.id.Phone);
            Spinner gender = findViewById(R.id.GenderSpinner);
            ImageView photo = (ImageView) findViewById(R.id.Photo);
            Spinner role = findViewById(R.id.RoleSpinner);

            final User user = new User(
                    0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "");


            //Asign Data and Validate

            if(firstName.getText().toString().length() < 1 )
            {
                firstName.setError("Too Short");
                errors++;
            }
            else
            {
                user.firstName = firstName.getText().toString();
            }

            if(lastName.getText().toString().length() < 1 )
            {
                lastName.setError("Too Short");
                errors++;
            }
            else
            {
                user.lastName = lastName.getText().toString();
            }

            if(dni.getText().toString().length() < 8 )
            {
                dni.setError("Minimum 8 characters");
                errors++;
            }
            else
            {
                user.dni = dni.getText().toString();
            }

            if(!email.getText().toString().contains("@"))
            {
                email.setError("Email invalid");
                errors++;
            }
            else
            {
                user.email = email.getText().toString();
            }


            if(password.getText().toString().length() < 6 )
            {
                password.setError("Minimum 6 characters");
                errors++;
            }
            else
            {
                user.password = password.getText().toString();
            }

            if(!password.getText().toString().equalsIgnoreCase(password2.getText().toString()))
            {
                password2.setError("Password does not match");

                errors++;
            }
            else
            {
                user.password = password.getText().toString();
            }

            if(phone.getText().toString().length() < 6 )
            {
                phone.setError("Minimum 6 characters");
                errors++;
            }
            else
            {
                user.phone = phone.getText().toString();
            }


            //Gender
            if(gender.getSelectedItem().toString().equalsIgnoreCase("Male"))
                user.gender = "1";
            else user.gender = "2";

            //Photo
            HelperBase64 helperBase64 = new HelperBase64();

            //Log.d("=iTracking=>",photo.getDrawable().toString());
            if(photo.getDrawable().toString().contains("VectorDrawable"))
            {
                errors++;
                Toast.makeText(getApplicationContext(),
                        "Photo is required",
                        Toast.LENGTH_SHORT).show();

            }
            else
            {
                user.photo = helperBase64.Encode(((BitmapDrawable)photo.getDrawable()).getBitmap());
            }

            //Role
            if(role.getSelectedItem().toString().equalsIgnoreCase("Admin"))
                user.roleId = "1";
            else if (role.getSelectedItem().toString().equalsIgnoreCase("User"))
                user.roleId = "2";
            else
                user.roleId = "3";

            user.status = "A"; // gender.getText().toString();

            if(errors > 0)
            {
                Toast.makeText(getApplicationContext(),
                        "Errors Found",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {

                helperLogSecurity.InsertLog("0","User: ( Start Debug Activity )");
                 firstName.setText("");
                 lastName.setText("");
                 dni.setText("");
                 email.setText("");
                 password.setText("");
                 password2.setText("");
                 phone.setText("");


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(globals.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                InterfaceRetrofit interfaceRetrofit = retrofit.create(InterfaceRetrofit.class);

                interfaceRetrofit.SaveUser(user).enqueue(new retrofit2.Callback<User>() {
                    @Override
                    public void onResponse(retrofit2.Call<User> call, retrofit2.Response<User> response) {

                        if (response.isSuccessful()) {
                            Log.d("==RESPONSE==>", response.body().toString());
                            Toast.makeText(getApplicationContext(),
                                    "Registrado",
                                    Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,mainFragment).commit();
                            setTitle("iTracking");


                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<User> call, Throwable t) {
                        Log.e("==ERROR==>", t.toString());
                        Toast.makeText(getApplicationContext(),
                                "Error Server",
                                Toast.LENGTH_SHORT).show();

                    }
                });

            }




        }
        catch (Exception ex)
        {
            Log.d("=iTRracking>",ex.toString());
        }


        Email emailToSend = new Email("Bienvenido a iTracking",
                "Bienvenido: " +"cmercado@ipsec.com.pe","cmercado@ipsec.com.pe");

        try {
            SendEmail(emailToSend);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public void UpdateUser(View view) {
        helperLogSecurity.InsertLog("0","User: ( Start UpdateUserAction )");
        Toast.makeText(getApplicationContext(),
                "" +
                        "Actualizado",
                Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,mainFragment).commit();
        setTitle("iTracking");
    }

    //Fragment Log Methods

    public void DeleteLog(View view) {

        try{
            HelperLogSecurity helperLogSecurity =
                    new HelperLogSecurity(new HelperSQLite(
                            this,
                            "iTracking",
                            null,
                            1));
            HelperSQLite helperSQLite = new HelperSQLite(this,"iTracking",null,1);
            repository = new RepositoryLogsSecurity();
            repository.Delete(helperSQLite);
            helperLogSecurity.InsertLog("0","User: ( Delete Log )");
            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,mainFragment).commit();
            setTitle("iTracking");

        }
        catch (Exception ex)
        {
            Log.d("=iTracking=>",ex.toString());
        }
        finally {

        }

    }

    public void RegisterDelivery(View view) {
        Toast.makeText(getApplicationContext(),
                "Updated",
                Toast.LENGTH_SHORT).show();
    }


    //Fragment Result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void RegisterClient(View view) {
        Integer errors = 0;
        Toast.makeText(getApplicationContext(),
                "Validando",
                Toast.LENGTH_SHORT).show();

        try
        {
            EditText ruc = findViewById(R.id.tViewRuc);
            EditText razon = findViewById(R.id.tViewRazon);
            EditText address = findViewById(R.id.tViewDireccion);
            EditText phone = findViewById(R.id.tViewPhone);

            Client client = new Client(
                    0,
                    "",
                    "",
                    "",
                    "");


            //Asign Data and Validate

            if(ruc.getText().toString().length() < 11 )
            {
                ruc.setError("Too Short");
                errors++;
            }
            else
            {
                client.RUC_DNI = ruc.getText().toString();
            }

            if(razon.getText().toString().length() < 1 )
            {
                razon.setError("Too Short");
                errors++;
            }
            else
            {
                client.RS_Name = razon.getText().toString();
            }

            if(address.getText().toString().length() < 1 )
            {
                address.setError("Too Short");
                errors++;
            }
            else
            {
                client.Address = address.getText().toString();
            }


            if(phone.getText().toString().length() < 6 )
            {
                phone.setError("Minimum 6 characters");
                errors++;
            }
            else
            {
                client.Phone = phone.getText().toString();
            }

            client.status = "A"; // gender.getText().toString();

            if(errors > 0)
            {
                Toast.makeText(getApplicationContext(),
                        "Errors Found",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {

                helperLogSecurity.InsertLog("0","User: ( Start Debug Activity )");

                /*
                ruc.setText("");
                razon.setText("");
                address.setText("");
                phone.setText("");*/

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(globals.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                InterfaceRetrofit interfaceRetrofit = retrofit.create(InterfaceRetrofit.class);

                interfaceRetrofit.SaveClient(client).enqueue(new retrofit2.Callback<Client>() {
                    @Override
                    public void onResponse(retrofit2.Call<Client> call, retrofit2.Response<Client> response) {

                        if (response.isSuccessful()) {
                            Log.d("==RESPONSE==>", response.body().toString());
                            Toast.makeText(getApplicationContext(),
                                    "Registrado",
                                    Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,mainFragment).commit();
                            setTitle("iTracking");

                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Client> call, Throwable t) {
                        Log.e("==ERROR==>", t.toString());
                        Toast.makeText(getApplicationContext(),
                                "Error Server",
                                Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }
        catch (Exception ex)
        {
            Log.d("=iTracking>",ex.toString());
        }

    }


    public void RegisterOrder(View view) {

        Integer errors = 0;
        Toast.makeText(getApplicationContext(),
                "Validando",
                Toast.LENGTH_SHORT).show();

        try
        {

            Spinner razon = findViewById(R.id.spiClient);
            EditText ruc = findViewById(R.id.vTxtRuc);
            EditText phone = findViewById(R.id.vTxtRuc);
            EditText source = findViewById(R.id.vTxtSource);
            EditText destination = findViewById(R.id.vTxtDestination);
            EditText date = findViewById(R.id.tViewDate);
            EditText boxes = findViewById(R.id.tViewBoxes);
            Order order  = new Order(
                    0,
                    "",
                    "",
                    "",
                    "",
                    "","","","","","","");


            //Asign Data and Validate



            if(source.getText().toString().length() < 1 )
            {
                source.setError("Too Short");
                errors++;
            }
            else
            {
                order.source = source.getText().toString();
            }

            if(destination.getText().toString().length() < 1 )
            {
                destination.setError("Too Short");
                errors++;
            }
            else
            {
                order.destination = destination.getText().toString();
            }

            if(phone.getText().toString().length() < 6 )
            {
                phone.setError("Too Short");
                errors++;
            }
            else
            {
                order.phone = phone.getText().toString();
            }

            if(date.getText().toString().length() < 10)           {
                date.setError("Minimum 10 characters");
                errors++;
            }
            else
            {
                order.date = date.getText().toString();
            }

            if(boxes.getText().toString().length() < 1)           {
                date.setError("Minimum 1 characters");
                errors++;
            }
            else
            {
                order.boxes = boxes.getText().toString();
            }






            order.status = "Registrado"; // gender.getText().toString();
            order.clientId = ruc.getText().toString();

            order.ClientName = razon.getSelectedItem().toString();

            if(errors > 0)
            {
                Toast.makeText(getApplicationContext(),
                        "Errors Found",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {

                helperLogSecurity.InsertLog("0","User: ( Start Debug Activity )");

                /*
                ruc.setText("");
                razon.setText("");
                address.setText("");
                phone.setText("");*/

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(globals.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                InterfaceRetrofit interfaceRetrofit = retrofit.create(InterfaceRetrofit.class);

                interfaceRetrofit.SaveOrder(order).enqueue(new retrofit2.Callback<Order>() {
                    @Override
                    public void onResponse(retrofit2.Call<Order> call, retrofit2.Response<Order> response) {

                        if (response.isSuccessful()) {
                            Log.d("==RESPONSE==>", response.body().toString());
                            Toast.makeText(getApplicationContext(),
                                    "Registrado",
                                    Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,mainFragment).commit();
                            setTitle("iTracking");

                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Order> call, Throwable t) {
                        Log.e("==ERROR==>", t.toString());
                        Toast.makeText(getApplicationContext(),
                                "Error Server",
                                Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }
        catch (Exception ex)
        {
            Log.d("=iTRracking>",ex.toString());
        }


    }

    public void UpdateOrder(View view) {
        Integer errors = 0;
        Toast.makeText(getApplicationContext(),
                "Update in process",
                Toast.LENGTH_SHORT).show();

        try
        {

            Spinner razon = findViewById(R.id.spiStatus);
            EditText order = findViewById(R.id.pTxtOrder);
            ImageView photo = findViewById(R.id.btnCam);
            ImageView validation = findViewById(R.id.btnMapsV);



            if(errors > 0)
            {
                Toast.makeText(getApplicationContext(),
                        "Errors Found",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {

                helperLogSecurity.InsertLog("0","User: ( Start Update Order Activity )");

                /*
                ruc.setText("");
                razon.setText("");
                address.setText("");
                phone.setText("");*/

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(globals.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                InterfaceRetrofit interfaceRetrofit = retrofit.create(InterfaceRetrofit.class);


                Order orderUpdated =  new Order(
                        Integer.parseInt(order.getText().toString()),
                        ""
                        ,""
                        ,""
                        ,""
                        ,""
                        ,""
                        ,""
                        ,""
                        ,razon.getSelectedItem().toString()
                        ,validation.toString()
                        ,photo.toString()


                        );

                //Photo
                HelperBase64 helperBase64 = new HelperBase64();

                //Log.d("=iTracking=>",photo.getDrawable().toString());
                if(photo.getDrawable().toString().contains("VectorDrawable"))
                {
                    errors++;
                    Toast.makeText(getApplicationContext(),
                            "Photo is required",
                            Toast.LENGTH_SHORT).show();

                }
                else
                {
                    orderUpdated.photo = helperBase64.Encode(((BitmapDrawable)photo.getDrawable()).getBitmap());
                }



                interfaceRetrofit.UpdateOrder(order.getText().toString(),orderUpdated).
                        enqueue(new retrofit2.Callback<Order>() {
                    @Override
                    public void onResponse(retrofit2.Call<Order> call, retrofit2.Response<Order> response) {

                        if (response.isSuccessful()) {
                            Log.d("==RESPONSE==>", response.body().toString());
                            Toast.makeText(getApplicationContext(),
                                    "Updated",
                                    Toast.LENGTH_SHORT).show();
                            getSupportFragmentManager().beginTransaction().replace(R.id.FragmentsContainer,mainFragment).commit();
                            setTitle("iTracking");

                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Order> call, Throwable t) {
                        Log.e("==ERROR==>", t.toString());
                        Toast.makeText(getApplicationContext(),
                                "Error Server",
                                Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }
        catch (Exception ex)
        {
            Log.d("=iTRracking>",ex.toString());
        }



    }

    public void GoToCall(View view) {

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

    }
}
