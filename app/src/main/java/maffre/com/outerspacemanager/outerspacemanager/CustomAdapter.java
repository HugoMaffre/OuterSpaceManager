package maffre.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.String.valueOf;

/**
 * Created by mac2 on 14/03/2017.
 */








public class CustomAdapter extends ArrayAdapter<Building> {

    private TextView buildingName;
    private TextView buildingLevel;
    private TextView buildingMetalCost;
    private TextView buildingCristalCost;
    private TextView buildingOnConstruct;
    private TextView buildingConstructTime;
    private ImageView imageView;
    private ProgressBar progressBar;

    private final Context applicationContext;
    private final ArrayList<Building> buildings;
    private final HashMap<Integer, BuildingDB> buildingsDb;

    public CustomAdapter(Context applicationContext, ArrayList<Building> buildings, HashMap<Integer, BuildingDB> buildingsDb) {

        super(applicationContext, R.layout.custom_view_cell, buildings);
        this.applicationContext = applicationContext;
        this.buildings = buildings;
        this.buildingsDb = buildingsDb;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_view_cell, parent, false);




        buildingOnConstruct = (TextView) rowView.findViewById(R.id.buildingOnConstruct);
        buildingCristalCost = (TextView) rowView.findViewById(R.id.buildingCristalCost);
        buildingName = (TextView) rowView.findViewById(R.id.buildingName);
        buildingLevel = (TextView) rowView.findViewById(R.id.buildingLevel);
        buildingMetalCost = (TextView) rowView.findViewById(R.id.buildingMetalCost);
        buildingConstructTime = (TextView) rowView.findViewById(R.id.buildingConstructTime);
        progressBar = (ProgressBar) rowView.findViewById(R.id.progressBar);
        imageView = (ImageView) rowView.findViewById(R.id.imageView);


        buildingName.setText(buildings.get(position).getName());
        buildingLevel.setText("Niveau  : "+buildings.get(position).getLevel());
        buildingCristalCost.setText("Cout en Cristal : "+buildings.get(position).getGasCostLevel() + (buildings.get(position).getGasCostByLevel() * buildings.get(position).getLevel()));
        buildingMetalCost.setText("Cout en Metal : "+buildings.get(position).getMineralCostLevel() + (buildings.get(position).getMineralCostByLevel() * buildings.get(position).getLevel()));


        //si le batiment est en construction
        if (buildings.get(position).isBuilding()) {

            if (buildingsDb.containsKey(buildings.get(position).getId())){
                long tempsEcoule = System.currentTimeMillis() - buildingsDb.get(buildings.get(position).getId()).getCurrentDate();
                long tempsTotal = buildings.get(buildings.get(position).getId()).getTimeToBuildLevel0() + (buildings.get(buildings.get(position).getId()).getTimeToBuildByLevel() * buildings.get(buildings.get(position).getId()).getLevel())*1000;
                long tempsRestant = tempsTotal - tempsEcoule;
                long pourcent = (tempsRestant/tempsTotal)*100;

                buildingConstructTime.setText(String.valueOf(tempsTotal));
                buildingOnConstruct.setText("En construction");
                progressBar.setProgress(50);
            }

            //sinon
        } else {
            buildingOnConstruct.setText("Pas en construction");
            buildingConstructTime.setText("Temps pour augmenter :"+buildings.get(position).getTimeToBuildLevel0()+(buildings.get(position).getTimeToBuildByLevel()*buildings.get(position).getLevel()) +" ms");
            progressBar.setVisibility(rowView.GONE);
        }



        //image
        String url = buildings.get(position).getImageUrl();

        Glide
            .with(applicationContext)
            .load(url)
            .centerCrop()
            .crossFade()
            .into(imageView);


        return rowView;
    }

}
