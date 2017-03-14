package maffre.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static maffre.com.outerspacemanager.outerspacemanager.SignUpActivity.USER_DATA;





public class MenuActivity extends Activity implements View.OnClickListener {


    private SharedPreferences settings;
    private TextView points;
    private TextView gas;
    private TextView mineraux;
    private Button galaxie;
    private TextView username;
    private Button deconnect;
    private Button buildings;
    private Button chantierSpatial;


    //retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    @Override
    protected void onResume() {
        super.onResume();
        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");
        //appel de l'interface create
        loginInterface service = retrofit.create(loginInterface.class);
        Call<User> request= service.getUser(currentAccessToken);

        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                username.setText(response.body().getUsername());
                points.setText("Points :"+String.valueOf(response.body().getPoints()));
                gas.setText("Cristal :"+String.valueOf(response.body().getGas()));
                mineraux.setText("Metal :"+String.valueOf(response.body().getMinerals()));

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);




        username = (TextView) findViewById(R.id.username);
        points = (TextView) findViewById(R.id.points);
        gas = (TextView) findViewById(R.id.gaz);
        mineraux = (TextView) findViewById(R.id.mineraux);
        deconnect = (Button) findViewById(R.id.deconnect);
        buildings = (Button) findViewById(R.id.buildings);
        galaxie = (Button) findViewById(R.id.galaxie);
        chantierSpatial = (Button) findViewById(R.id.chantierSpatial);
        deconnect.setOnClickListener(this);
        buildings.setOnClickListener(this);
        galaxie.setOnClickListener(this);
        chantierSpatial.setOnClickListener(this);


        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");


        //appel de l'interface create
        loginInterface service = retrofit.create(loginInterface.class);
        Call<User> request= service.getUser(currentAccessToken);


        request.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                username.setText(response.body().getUsername());
                points.setText("Points :"+String.valueOf(response.body().getPoints()));
                gas.setText("Cristal :"+String.valueOf(response.body().getGas()));
                mineraux.setText("Metal :"+String.valueOf(response.body().getMinerals()));


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.deconnect:

                // Current Access Token
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("accessToken", "");
                break;

            case R.id.buildings:

                Intent myIntent = new Intent(getApplicationContext(), BuildingsActivity.class);
                startActivity(myIntent);
                break;

            case R.id.galaxie:

                Intent usersIntent = new Intent(getApplicationContext(), UsersActivity.class);
                startActivity(usersIntent);
                break;

            case R.id.chantierSpatial:

                Intent chantierIntent = new Intent(getApplicationContext(), ChantierActivity.class);
                startActivity(chantierIntent);
                break;
        }
    }
}
