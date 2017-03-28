package maffre.com.outerspacemanager.outerspacemanager.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import maffre.com.outerspacemanager.outerspacemanager.R;
import maffre.com.outerspacemanager.outerspacemanager.models.Ships;
import maffre.com.outerspacemanager.outerspacemanager.network.RequestsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static maffre.com.outerspacemanager.outerspacemanager.activities.SignUpActivity.USER_DATA;

/**
 * Created by mac2 on 20/03/2017.
 */

public class FleetActivity extends AppCompatActivity {


    private ListView fleetList;
    //retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fleet_layout);

        mProgressDialog = ProgressDialog.show(this, "",
                "Loading ships", true);

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");


        fleetList = (ListView) findViewById(R.id.fleetList);

        //appel de l'interface create
        RequestsInterface service = retrofit.create(RequestsInterface.class);
        Call<Ships> request = service.getFleet(currentAccessToken);


        request.enqueue(new Callback<Ships>() {
            @Override
            public void onResponse(Call<Ships> call, Response<Ships> response) {

                ArrayList<String> ships = new ArrayList<String>();
                for (int i = 0; i < response.body().arraySize(); i++) {
                    ships.add(response.body().getShips().get(i).getName()+"\n Nombre :"+response.body().getShips().get(i).getAmount());
                }
                fleetList.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, ships));
                mProgressDialog.dismiss();
                if(ships.size() == 0){
                    Context context = getApplicationContext();
                    CharSequence text = "Pas encore de vaisseaux construits";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<Ships> call, Throwable t) {

            }
        });


    }


}
