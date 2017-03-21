package maffre.com.outerspacemanager.outerspacemanager;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static maffre.com.outerspacemanager.outerspacemanager.SignUpActivity.USER_DATA;

public class BuildingsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private ListView buildingsList;
    //retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buildings);

        mProgressDialog = ProgressDialog.show(this, "",
                "Loading buildings", true);

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");

        buildingsList = (ListView) findViewById(R.id.buildingsList);
        buildingsList.setOnItemClickListener(this);
        final BuildingDataSource buildingDataSource = new BuildingDataSource(getApplicationContext());

        //appel de l'interface create
        loginInterface service = retrofit.create(loginInterface.class);
        Call<Buildings> request = service.getBuildings(currentAccessToken);


        request.enqueue(new Callback<Buildings>() {
            @Override
            public void onResponse(Call<Buildings> call, Response<Buildings> response) {

                ArrayList<Building> buildings = response.body().getBuildings();
                buildingDataSource.open();
                buildingsList.setAdapter(new CustomAdapter(BuildingsActivity.this, buildings, buildingDataSource.getBuildings()));
                buildingDataSource.close();
                mProgressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Buildings> call, Throwable t) {

            }
        });


    }

    @Override
    public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {

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

                                 Building buildingRow = ((CustomAdapter)parent.getAdapter()).getItem(position);

                                if (response.code() == 200) {
                                    //ajout en base de données
                                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                                    {
                                        if (ActivityCompat.shouldShowRequestPermissionRationale(BuildingsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                                        } else {
                                            ActivityCompat.requestPermissions(BuildingsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                                        }
                                    }else {

                                        final BuildingDataSource buildingDataSource = new BuildingDataSource(getApplicationContext());
                                        buildingDataSource.open();
                                        buildingDataSource.createBuilding(buildingRow.getName(),buildingRow.getLevel(), buildingRow.getTimeToBuildByLevel(), buildingRow.getTimeToBuildLevel0(), System.currentTimeMillis(), position);
                                        buildingDataSource.close();

                                    }
                                    Context context = getApplicationContext();
                                    CharSequence text = "Batiment augmenté";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();

                                } else {
                                    Context context = getApplicationContext();
                                    CharSequence text = "Déjà en construction";
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