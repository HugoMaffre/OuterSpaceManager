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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.lang.String.valueOf;
import static maffre.com.outerspacemanager.outerspacemanager.SignUpActivity.USER_DATA;

/**
 * Created by mac2 on 14/03/2017.
 */

public class ChantierActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView shipsList;
    private EditText amountText;


    //retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chantier_layout);



        mProgressDialog = ProgressDialog.show(this, "",
                "Loading ships", true);

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");


        shipsList = (ListView) findViewById(R.id.shipsList);
        shipsList.setOnItemClickListener(this);
        amountText = (EditText) findViewById(R.id.amount);



        //appel de l'interface create
        loginInterface service = retrofit.create(loginInterface.class);
        Call<Ships> request = service.getShips(currentAccessToken);


        request.enqueue(new Callback<Ships>() {
            @Override
            public void onResponse(Call<Ships> call, Response<Ships> response) {

                ArrayList<Ship> ships = response.body().getShips();
                shipsList.setAdapter(new ChantierCustomCell(getApplicationContext(), ships));
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Ships> call, Throwable t) {

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        final String currentAccessToken = users.getString("accessToken", "");

        //get amount
        final int amount = Integer.valueOf(amountText.getText().toString());



        new AlertDialog.Builder(this)
                .setTitle("Nouveau vaisseau")
                .setMessage("Etes vous sur de vouloir construire un nouveau vaisseau ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        loginInterface service = retrofit.create(loginInterface.class);
                        Call<Ship> request= service.createShip(currentAccessToken, position, new shipQueryObject(amount));


                        request.enqueue(new Callback<Ship>() {
                            @Override
                            public void onResponse(Call<Ship> call, Response<Ship> response) {




                                if (response.code() == 200) {

                                    Context context = getApplicationContext();
                                    CharSequence text = "Vaisseau créé";
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
                            public void onFailure(Call<Ship> call, Throwable t) {

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
