package com.example.schedule.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedule.Model.Group;
import com.example.schedule.R;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private Context mContext;
    private ArrayList<Group> mList;

    public GroupAdapter(Context mContext, ArrayList<Group> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false);
        GroupViewHolder groupViewHolder = new GroupViewHolder(view);
        return groupViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupViewHolder holder, int position) {
        holder.nameGroup.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {

        TextView nameGroup;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);

            nameGroup = itemView.findViewById(R.id.nameGroup);

        }
    }

    public void updateList(ArrayList<Group> updatedList) {
        mList = updatedList;
        notifyDataSetChanged();
    }

    public Group getGroupAt(int position) {
        return mList.get(position);
    }
}
