package maffre.com.outerspacemanager.outerspacemanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import maffre.com.outerspacemanager.outerspacemanager.Building;
import maffre.com.outerspacemanager.outerspacemanager.R;

public class ChantierCustomCell extends ArrayAdapter<Ship> {

    private TextView shipName;
    private TextView cost;
    private TextView timeToBuild;


    private final Context applicationContext;
    private final ArrayList<Ship> ships;

    public ChantierCustomCell(Context applicationContext, ArrayList<Ship> ships) {

        super(applicationContext, R.layout.chantier_cell_layout, ships);
        this.applicationContext = applicationContext;
        this.ships = ships;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chantier_cell_layout, parent, false);


        shipName = (TextView) rowView.findViewById(R.id.shipName);
        cost = (TextView) rowView.findViewById(R.id.cost);
        timeToBuild = (TextView) rowView.findViewById(R.id.timeToBuild);


        shipName.setText(ships.get(position).getName());
        cost.setText("Cout en Cristal  : " + ships.get(position).getGasCost());
        timeToBuild.setText("Temps de construction : " + ships.get(position).getTimeToBuild() + " ms");


        return rowView;
    }

}