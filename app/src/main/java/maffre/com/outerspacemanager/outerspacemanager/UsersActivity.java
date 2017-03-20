package maffre.com.outerspacemanager.outerspacemanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
 * Created by mac2 on 14/03/2017.
 */

public class UsersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private ListView usersList;

    //retrofit
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://outer-space-manager.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_layout);

        mProgressDialog = ProgressDialog.show(this, "",
                "Loading users", true);

        // Current Access Token
        SharedPreferences users = getSharedPreferences(USER_DATA, 0);
        String currentAccessToken = users.getString("accessToken", "");


        usersList = (ListView) findViewById(R.id.usersList);
        usersList.setOnItemClickListener(this);

        //appel de l'interface create
        loginInterface service = retrofit.create(loginInterface.class);
        Call<Users> request = service.getUsers(currentAccessToken);


        request.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                ArrayList<String> usernames = new ArrayList<String>();

                for (int i = 0; i < response.body().arraySize(); i++) {
                    usernames.add("Username :"+response.body().getUsers().get(i).getUsername()+"\n"+response.body().getUsers().get(i).getPoints()+" points");
                }

                usersList.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, usernames));
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Context context = getApplicationContext();
                CharSequence text = "Error loading users";
                int duration = Toast.LENGTH_SHORT;
            }
        });



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        Intent attackIntent = new Intent(getApplicationContext(), AttackActivity.class);
        startActivity(attackIntent);

    }
}
