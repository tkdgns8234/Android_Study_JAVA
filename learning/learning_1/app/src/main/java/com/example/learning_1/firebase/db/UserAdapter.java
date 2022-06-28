package com.example.learning_1.firebase.db;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learning_1.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private ArrayList<User> mArrayList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }

    public interface OnItemClickListener {
        void OnItemClick(View v, int viewPos);
    }

    public UserAdapter(ArrayList<User> mArrayList, Context mContext) {
        this.mArrayList = mArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.layout_firebase_dbitem, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.tv_fbdb_id.setText(mArrayList.get(position).getId());
        holder.tv_fbdb_age.setText(String.valueOf(mArrayList.get(position).getAge()));
        holder.tv_fbdb_gender.setText(mArrayList.get(position).getGender());
        holder.tv_fbdb_name.setText(mArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mArrayList == null ? 0 : mArrayList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tv_fbdb_id;
        TextView tv_fbdb_age;
        TextView tv_fbdb_gender;
        TextView tv_fbdb_name;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(listener);
            tv_fbdb_id = itemView.findViewById(R.id.tv_fbdb_id);
            tv_fbdb_age = itemView.findViewById(R.id.tv_fbdb_age);
            tv_fbdb_gender = itemView.findViewById(R.id.tv_fbdb_gender);
            tv_fbdb_name = itemView.findViewById(R.id.tv_fbdb_name);
        }

        private View.OnLongClickListener listener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.e("UserAdapter", "onLongClick()");
                mOnItemClickListener.OnItemClick(v, getAdapterPosition());
                return false;
            }
        };
    }
}
