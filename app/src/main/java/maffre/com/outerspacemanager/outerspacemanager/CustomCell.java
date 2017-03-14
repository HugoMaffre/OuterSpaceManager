package maffre.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mac2 on 14/03/2017.
 */








public class CustomCell extends ArrayAdapter<Building> {

    private TextView buildingName;
    private TextView buildingLevel;
    private TextView buildingMetalCost;
    private TextView buildingCristalCost;
    private TextView buildingOnConstruct;
    private TextView buildingConstructTime;

    private final Context applicationContext;
    private final ArrayList<Building> buildings;

    public CustomCell(Context applicationContext, ArrayList<Building> buildings) {

        super(applicationContext, R.layout.custom_view_cell, buildings);
        this.applicationContext = applicationContext;
        this.buildings = buildings;

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


        buildingName.setText(buildings.get(position).getName());
        buildingLevel.setText("Niveau  : "+buildings.get(position).getLevel());
        buildingCristalCost.setText("Cout en Cristal : "+buildings.get(position).getGasCostLevel() + (buildings.get(position).getGasCostByLevel() * buildings.get(position).getLevel()));
        buildingMetalCost.setText("Cout en Metal : "+buildings.get(position).getMineralCostLevel() + (buildings.get(position).getMineralCostByLevel() * buildings.get(position).getLevel()));
        buildingOnConstruct.setText("Temps de construction : "+buildings.get(position).getTimeToBuildLevel0() + (buildings.get(position).getTimeToBuildByLevel() * buildings.get(position).getLevel()) +" ms");

        if (buildings.get(position).isBuilding() == true) {
            buildingConstructTime.setText("Batiment en construction");
        } else {
            buildingConstructTime.setText("");
        }

        return rowView;
    }

}
