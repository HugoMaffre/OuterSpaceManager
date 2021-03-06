package maffre.com.outerspacemanager.outerspacemanager.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import maffre.com.outerspacemanager.outerspacemanager.R;
import maffre.com.outerspacemanager.outerspacemanager.models.User;
import maffre.com.outerspacemanager.outerspacemanager.network.RequestsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static maffre.com.outerspacemanager.outerspacemanager.activities.SignUpActivity.USER_DATA;





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
    private Button research;
    private Button fleet;


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
        RequestsInterface service = retrofit.create(RequestsInterface.class);
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
        fleet = (Button) findViewById(R.id.fleet);
        points = (TextView) findViewById(R.id.points);
        gas = (TextView) findViewById(R.id.gaz);
        mineraux = (TextView) findViewById(R.id.mineraux);
        deconnect = (Button) findViewById(R.id.deconnect);
        research = (Button) findViewById(R.id.research);
        buildings = (Button) findViewById(R.id.buildings);
        galaxie = (Button) findViewById(R.id.galaxie);
        chantierSpatial = (Button) findViewById(R.id.chantierSpatial);
        deconnect.setOnClickListener(this);
        research.setOnClickListener(this);
        buildings.setOnClickListener(this);
        galaxie.setOnClickListener(this);
        chantierSpatial.setOnClickListener(this);
        fleet.setOnClickListener(this);


        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");


        //appel de l'interface create
        RequestsInterface service = retrofit.create(RequestsInterface.class);
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
                SharedPreferences users = getSharedPreferences(USER_DATA, 0);
                SharedPreferences.Editor editor = users.edit();
                editor.putString("accessToken", "");
                Intent decoIntent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(decoIntent);

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


            case R.id.fleet:

                Intent fleetIntent = new Intent(getApplicationContext(), FleetActivity.class);
                startActivity(fleetIntent);
                break;

            case R.id.research:

                Intent researchIntent = new Intent(getApplicationContext(), ResearchActivity.class);
                startActivity(researchIntent);
                break;
        }
    }
}
