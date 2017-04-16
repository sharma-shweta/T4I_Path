package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.path.models.Classroom;
import com.android.path.models.ClassroomGrades;
import com.android.path.models.SubjectAnalytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SubjectAnalyticsAdapter extends RecyclerView.Adapter<SubjectAnalyticsAdapter.BusHolder> {

    private List<SubjectAnalytics> subjectsData;

    public class BusHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView subjTitle, subjGoal, subjAchieved;
        public RecyclerView gradeLevelRV;
        public Button subjGPA;
        public ClassGradesAdapter clsGradesAdapter;
        public ArrayList<ClassroomGrades> gradeLevels = new ArrayList<ClassroomGrades>();

        public BusHolder(View view) {
            super(view);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            subjTitle = (TextView) view.findViewById(R.id.subjTitle);
            subjGoal = (TextView) view.findViewById(R.id.subjGoal);
            subjAchieved = (TextView) view.findViewById(R.id.subjAchieved);

            gradeLevelRV = (RecyclerView) view.findViewById(R.id.gradeLevelRV);
            RecyclerView.LayoutManager mLayoutManager = new
                    LinearLayoutManager(view.getContext().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
            gradeLevelRV.setLayoutManager(mLayoutManager);
            gradeLevelRV.setItemAnimator(new DefaultItemAnimator());

            clsGradesAdapter = new ClassGradesAdapter(gradeLevels);
            gradeLevelRV.setAdapter(clsGradesAdapter);
        }

        @Override
        public void onClick(View view) {
//            Context context = view.getContext();
//            Intent intent = new Intent(context, EditBusDetailsActivity.class);
//            intent.putExtra(context.getString(R.string.busKey), busList.get(getAdapterPosition()));
//            context.startActivity(intent);
        }
    }

    public SubjectAnalyticsAdapter(List<SubjectAnalytics> subjectsData) {
        this.subjectsData = subjectsData;
    }

    @Override
    public BusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_analytics_card, parent, false);
        return new BusHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BusHolder holder, final int position) {
        SubjectAnalytics subjectAnalytics = subjectsData.get(position);
        holder.subjAchieved.setText(subjectAnalytics.achieved.toString() + "%");
        holder.subjGoal.setText("Goal - " + subjectAnalytics.goal.toString());
        holder.subjTitle.setText(subjectAnalytics.name);

        holder.gradeLevels.clear();
        for(Double grade: subjectAnalytics.gpaStudentRatioMap.keySet()) {
            holder.gradeLevels.add(new ClassroomGrades(grade, subjectAnalytics.gpaStudentRatioMap.get(grade)));
        }
        Collections.sort(holder.gradeLevels);
        holder.clsGradesAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return subjectsData.size();
    }

}