
package maffre.com.outerspacemanager.outerspacemanager.adapters;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;

        import maffre.com.outerspacemanager.outerspacemanager.R;
        import maffre.com.outerspacemanager.outerspacemanager.models.Research;

public class SearchCustomAdapter extends ArrayAdapter<Research> {

        private TextView searchName;
        private TextView searchLevel;
        private TextView searchMetalCost;
        private TextView searchCristalCost;
        private TextView searchOnConstruct;
        private TextView searchConstructTime;

        private final Context applicationContext;
        private final ArrayList<Research> searches;

        public SearchCustomAdapter(Context applicationContext, ArrayList<Research> researches) {

            super(applicationContext, R.layout.search_cell, researches);
            this.applicationContext = applicationContext;
            this.searches = researches;

        }





        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.search_cell, parent, false);




            searchOnConstruct = (TextView) rowView.findViewById(R.id.researchOnConstruct);
            searchCristalCost = (TextView) rowView.findViewById(R.id.researchCristalCost);
            searchName = (TextView) rowView.findViewById(R.id.researchName);
            searchLevel = (TextView) rowView.findViewById(R.id.researchLevel);
            searchMetalCost = (TextView) rowView.findViewById(R.id.researchMetalCost);
            searchConstructTime = (TextView) rowView.findViewById(R.id.researchConstructTime);



            searchName.setText(searches.get(position).getName());
            searchLevel.setText("Niveau  : "+searches.get(position).getLevel());
            searchCristalCost.setText("Cout en Cristal : "+searches.get(position).getGasCostLevel() + (searches.get(position).getGasCostByLevel() * searches.get(position).getLevel()));
            searchMetalCost.setText("Cout en Metal : "+searches.get(position).getMineralCostLevel() + (searches.get(position).getMineralCostByLevel() * searches.get(position).getLevel()));
            searchOnConstruct.setText("Temps de construction : "+searches.get(position).getTimeToBuildLevel0() + (searches.get(position).getTimeToBuildByLevel() * searches.get(position).getLevel()) +" ms");


            if (searches.get(position).isBuilding() == true) {
                searchConstructTime.setText("En recherche");
            } else {
                searchConstructTime.setText("");
            }


            return rowView;
        }

    }
