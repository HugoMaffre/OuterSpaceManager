package maffre.com.outerspacemanager.outerspacemanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static maffre.com.outerspacemanager.outerspacemanager.SignUpActivity.USER_DATA;

/**
 * Created by mac2 on 20/03/2017.
 */

public class ResearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private ListView researchesList;
    //retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.research_layout);

        mProgressDialog = ProgressDialog.show(this, "",
                "Loading researches", true);

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");

        researchesList = (ListView) findViewById(R.id.researchesList);
        researchesList.setOnItemClickListener(this);

        //appel de l'interface create
        loginInterface service = retrofit.create(loginInterface.class);
        Call<Researches> request = service.getResearches(currentAccessToken);


        request.enqueue(new Callback<Researches>() {
            @Override
            public void onResponse(Call<Researches> call, Response<Researches> response) {


                ArrayList<Research> researches = response.body().getResearches();
                researchesList.setAdapter(new SearchCustomCell(ResearchActivity.this, researches));
                mProgressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<Researches> call, Throwable t) {

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        final String currentAccessToken = users.getString("accessToken", "");

        new AlertDialog.Builder(this)
                .setTitle("Nouvelle recherche")
                .setMessage("Etes vous sur de vouloir lancer cette recherche ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        loginInterface service = retrofit.create(loginInterface.class);
                        Call<Research> request= service.createResearch(currentAccessToken, position);


                        request.enqueue(new Callback<Research>() {
                            @Override
                            public void onResponse(Call<Research> call, Response<Research> response) {




                                if (response.code() == 200) {

                                    Context context = getApplicationContext();
                                    CharSequence text = "Recherche augmentée";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();

                                } else {
                                    Context context = getApplicationContext();
                                    CharSequence text = "Déjà en recherche";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }




                            }

                            @Override
                            public void onFailure(Call<Research> call, Throwable t) {

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