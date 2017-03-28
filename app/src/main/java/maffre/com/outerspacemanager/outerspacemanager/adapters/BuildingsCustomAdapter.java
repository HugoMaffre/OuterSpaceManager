package maffre.com.outerspacemanager.outerspacemanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import maffre.com.outerspacemanager.outerspacemanager.R;
import maffre.com.outerspacemanager.outerspacemanager.models.Building;
import maffre.com.outerspacemanager.outerspacemanager.models.BuildingDB;

import static java.lang.String.valueOf;

/**
 * Created by mac2 on 14/03/2017.
 */

public class BuildingsCustomAdapter extends ArrayAdapter<Building> {

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

    public BuildingsCustomAdapter(Context applicationContext, ArrayList<Building> buildings, HashMap<Integer, BuildingDB> buildingsDb) {

        super(applicationContext, R.layout.custom_view_cell, buildings);
        this.applicationContext = applicationContext;
        this.buildings = buildings;
        this.buildingsDb = buildingsDb;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_view_cell, parent, false);





        buildingCristalCost = (TextView) rowView.findViewById(R.id.buildingCristalCost);
        buildingName = (TextView) rowView.findViewById(R.id.buildingName);
        buildingLevel = (TextView) rowView.findViewById(R.id.buildingLevel);
        buildingMetalCost = (TextView) rowView.findViewById(R.id.buildingMetalCost);
        buildingConstructTime = (TextView) rowView.findViewById(R.id.buildingConstructTime);
        imageView = (ImageView) rowView.findViewById(R.id.imageView);
        buildingOnConstruct = (TextView) rowView.findViewById(R.id.buildingOnConstruct);
        progressBar = (ProgressBar) rowView.findViewById(R.id.progressBar);


        Building currentBuilding = getItem(position);
        buildingName.setText(currentBuilding.getName());
        buildingLevel.setText("Niveau  : "+currentBuilding.getLevel());
        buildingCristalCost.setText("Cout en Cristal : "+currentBuilding.getGasCostLevel() + (currentBuilding.getGasCostByLevel() * currentBuilding.getLevel()));
        buildingMetalCost.setText("Cout en Metal : "+currentBuilding.getMineralCostLevel() + (currentBuilding.getMineralCostByLevel() * currentBuilding.getLevel()));


        //si le batiment est en construction
        if (buildings.get(position).isBuilding()) {

            if (buildingsDb.containsKey(currentBuilding.getId())){


                long tempsEcoule = System.currentTimeMillis() - buildingsDb.get(currentBuilding.getId()).getCurrentDate();
                long tempsTotal = buildingsDb.get(currentBuilding.getId()).getTimeToBuildLevel0() +
                        (buildingsDb.get(currentBuilding.getId()).getTimeToBuildByLevel() * buildingsDb.get(currentBuilding.getId()).getLevel()) * 1000;
                
                float pourcent = (float)tempsEcoule/tempsTotal*100;
                int pourcentRound = Math.round(pourcent);
                long tempsRestant = tempsTotal - tempsEcoule;

                buildingConstructTime.setVisibility(rowView.GONE);
                buildingOnConstruct.setText("En construction :\n"+ tempsRestant/60000 +" min restantes");
                progressBar.setProgress(pourcentRound);
            }

            //sinon
        } else {
            buildingOnConstruct.setVisibility(rowView.GONE);
            long timeToBuild = buildings.get(position).getTimeToBuildLevel0()+(buildings.get(position).getTimeToBuildByLevel()*buildings.get(position).getLevel());
            buildingConstructTime.setText("Amélioration disponible !");
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
