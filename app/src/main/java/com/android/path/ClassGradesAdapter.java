package com.android.path;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.path.models.ClassroomGrades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassGradesAdapter extends RecyclerView.Adapter<ClassGradesAdapter.BusHolder> {
    private List<ClassroomGrades> classroomGradesList;

    public class BusHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button gradeLevel;

        public BusHolder(View view) {
            super(view);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            gradeLevel = (Button) view.findViewById(R.id.grade_level_btn);
        }

        @Override
        public void onClick(View view) {
//            Context context = view.getContext();
//            Intent intent = new Intent(context, ?.class);
//            intent.putExtra(context.getString(R.string.busKey), busList.get(getAdapterPosition()));
//            context.startActivity(intent);
        }
    }

    public ClassGradesAdapter(List<ClassroomGrades> classroomGradesList) {
        this.classroomGradesList = classroomGradesList;
    }

    @Override
    public BusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grade_level_button, parent, false);
        return new ClassGradesAdapter.BusHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BusHolder holder, final int position) {
        ClassroomGrades clsGrade = classroomGradesList.get(position);
        holder.gradeLevel.setText(clsGrade.gradeLevel.toString());
        //TODO - set button color based on % of students
    }

    @Override
    public int getItemCount() {
        return classroomGradesList.size();
    }
}
