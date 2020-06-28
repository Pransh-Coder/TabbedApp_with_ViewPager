package com.example.edvizotask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterItems extends RecyclerView.Adapter<RecyclerAdapterItems.ViewHolder> {

    Context context;
    List<DataPojo> dataPojoList=new ArrayList<>();

    public RecyclerAdapterItems(Context context, List<DataPojo> dataPojoList) {
        this.context = context;
        this.dataPojoList = dataPojoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.score.setText(dataPojoList.get(position).getTestname());
        holder.obj.setText(dataPojoList.get(position).getTesttype());
        holder.date.setText(dataPojoList.get(position).getDate());
        holder.testPatternText.setText(dataPojoList.get(position).getTestpattern());
        holder.maximumMarksText.setText(dataPojoList.get(position).getMaxMarks());
        holder.marksScoredText.setText(dataPojoList.get(position).getMarksScored());

    }

    @Override
    public int getItemCount() {
        return dataPojoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView score,obj,date,testType,testPattern,maxMarks,marksScored,testPatternText,maximumMarksText,marksScoredText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            score = itemView.findViewById(R.id.score);
            obj = itemView.findViewById(R.id.obj);
            date = itemView.findViewById(R.id.date);
            testType = itemView.findViewById(R.id.testType);
            testPattern = itemView.findViewById(R.id.testPattern);
            maxMarks = itemView.findViewById(R.id.max_marks);
            marksScored = itemView.findViewById(R.id.marksScored);
            testPatternText = itemView.findViewById(R.id.testPatternText);
            maximumMarksText = itemView.findViewById(R.id.maxMarkstext);
            marksScoredText = itemView.findViewById(R.id.marksScoredtext);

        }
    }
}
