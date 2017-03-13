package maffre.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

import static maffre.com.outerspacemanager.outerspacemanager.SignUpActivity.USER_DATA;

public class BuildingsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private ListView buildingsList;
    //retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");


        buildingsList = (ListView) findViewById(R.id.buildingsList);
        buildingsList.setOnItemClickListener(this);

        //appel de l'interface create
        loginInterface service = retrofit.create(loginInterface.class);
        Call<Buildings> request = service.getBuildings(currentAccessToken);


        request.enqueue(new Callback<Buildings>() {
            @Override
            public void onResponse(Call<Buildings> call, Response<Buildings> response) {

                ArrayList<String> names = new ArrayList<String>();

                for (int i = 0; i < response.body().arraySize(); i++) {
                    names.add(response.body().getBuildings().get(i).getName());
                }

                buildingsList.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names));


            }

            @Override
            public void onFailure(Call<Buildings> call, Throwable t) {

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        final String currentAccessToken = users.getString("accessToken", "");

        new AlertDialog.Builder(this)
                .setTitle("Nouveau batiment")
                .setMessage("Etes vous sur de vouloir construire un nouveau batiment ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        loginInterface service = retrofit.create(loginInterface.class);
                        Call<Building> request= service.createBuilding(currentAccessToken, position);


                        request.enqueue(new Callback<Building>() {
                            @Override
                            public void onResponse(Call<Building> call, Response<Building> response) {




                                if (response.code() == 200) {

                                    Context context = getApplicationContext();
                                    CharSequence text = "Batiment augmenté";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();

                                } else {
                                    Context context = getApplicationContext();
                                    CharSequence text = "Error";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }




                            }

                            @Override
                            public void onFailure(Call<Building> call, Throwable t) {

                            }
                        });
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }



}