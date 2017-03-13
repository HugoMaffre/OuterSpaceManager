package maffre.com.outerspacemanager.outerspacemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maffreh on 06/03/2017.
 */

public class SignUpActivity extends Activity implements View.OnClickListener{

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private EditText password_field;
    private EditText id_field;
    private Button connection_button;
    private Button inscription_button;
    private TextView fail_txt;
    public static final String USER_DATA = "MyUserDatas";
    Gson gson = new Gson();


    //retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");




        //récupération des éléments du layout
        password_field = (EditText) findViewById(R.id.password_field);
        id_field = (EditText) findViewById(R.id.id_field);
        connection_button = (Button) findViewById(R.id.connection_button);
        inscription_button = (Button) findViewById(R.id.inscription_button);
        fail_txt = (TextView) findViewById(R.id.fail_txt);
        connection_button.setOnClickListener(this);
        inscription_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.inscription_button:


                //appel de l'interface create
                SignUpInterface service = retrofit.create(SignUpInterface.class);
                Call<User> request= service.createUser(new User(id_field.getText().toString(), password_field.getText().toString()));


                request.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {


                        if (response.code() == 200) {
                            SharedPreferences settings = getSharedPreferences(USER_DATA, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putString("accessToken", response.body().getAccessToken());
                            editor.putLong("expiration", response.body().getExpiration());

                            // Commit the edits!
                            editor.commit();
                            Intent myIntent = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(myIntent);
                        } else {
                            Context context = getApplicationContext();
                            CharSequence text = "Error";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Context context = getApplicationContext();
                        CharSequence text = "Error";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });

                //fail_txt.;

                break;
            case R.id.connection_button:

                //appel de l'interface create
                SignUpInterface service2 = retrofit.create(SignUpInterface.class);
                Call<User> request2 = service2.connectUser(new User(id_field.getText().toString(), password_field.getText().toString()));


                request2.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        Intent myIntent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(myIntent);

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

                break;
        }
    }

}
