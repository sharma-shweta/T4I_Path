package com.android.path;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.path.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;

public class JourneyActivity extends AppCompatActivity {

    private class Group {
        Double groupRating;
        Double avgGrowthPerc;
        Double minRating;
        Double maxRating;
        HashMap<String, Pair<Double, Double>> studentRatings = new HashMap<>(); // Student, rating, goal
    }

    private ArrayList<JourneyActivity.Group> groups = new ArrayList<>();
    private ListView bodyListView;

    private void fillInData() {
        JourneyActivity.Group g1 = new JourneyActivity.Group();
        g1.groupRating = 1.5;
        g1.avgGrowthPerc = 12.0;
        g1.minRating = 1.5;
        g1.maxRating = 4.0;
        g1.studentRatings.put("Sandra", Pair.create(0.0, 2.0));
        g1.studentRatings.put("Sandra", Pair.create(2.5, 2.5));
        g1.studentRatings.put("Sandra", Pair.create(2.0, 2.5));
        groups.add(g1);

        for (int i = 0; i < 10; i++) {
            JourneyActivity.Group g2 = new JourneyActivity.Group();
            double j = 1.4 * i;
            g2.groupRating = Double.valueOf(Math.round(j));
            g2.avgGrowthPerc = 12.0;
            g2.minRating = 1.5;
            g2.maxRating = 4.0;
            g2.studentRatings.put("Prem", Pair.create(0.0, 2.0));
            g2.studentRatings.put("Atul", Pair.create(2.5, 2.5));
            g2.studentRatings.put("Sonal", Pair.create(2.0, 2.5));
            groups.add(g2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fill in data structure
        fillInData();
        ArrayList<String> ratingGroups = new ArrayList<>();
        for (JourneyActivity.Group g : groups) {
            ratingGroups.add(Double.toString(g.groupRating));
        }

        // content and view
        setContentView(R.layout.activity_journey);

        RecyclerView headerRView = (RecyclerView) findViewById(R.id.headerRecycleView);
        headerRView.setHasFixedSize(true);
        headerRView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        headerRView.setAdapter(new HeaderButtonRecycleViewAdapter(ratingGroups));
        bodyListView = (ListView)findViewById(R.id.bodyListView);
        headerRView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, headerRView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                        Log.i("Cloeck", "Touch ->> " + position);
                        bodyListView.smoothScrollToPosition(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );

        // list view
        CustomAdapter adapter = new CustomAdapter(this, this.groups);
        bodyListView.setAdapter(adapter);


    }

    class CustomAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Group> data;

        public CustomAdapter(Context context,ArrayList<Group> data){
            this.context = context;
            this.data = data;
        }

        @Override
        public int getCount(){
            return data.size();
        }

        @Override
        public Object getItem(int position){
            return data.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
                LinearLayout cardLinearLayout = (LinearLayout) convertView.findViewById(R.id.cardLinearLayout);
                for(Group g: groups) {
                    RelativeLayout cardLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.journey_seekbar, null);
                    SeekBar seekbar = (SeekBar) cardLayout.findViewById(R.id.seekBar1);
                    seekbar.setMax(10);
                    seekbar.setProgress(3);
                    cardLinearLayout.addView(cardLayout);
                }

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

//            holder.imgIcon.setImageResource((Integer) data.get(position).get("Image"));
//            holder.txtFirstLastName.setText(data.get(position).get("FirstLastName").toString());
            return convertView;
        }

        class ViewHolder{
            ImageView imgIcon;
            TextView txtFirstLastName;
        }
    }

}