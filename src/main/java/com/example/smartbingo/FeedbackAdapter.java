package com.example.smartbingo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder> {

    private List<String> feedbackList;

    public FeedbackAdapter(List<String> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feedback, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.feedbackText.setText(feedbackList.get(position));
        holder.userName.setText("User " + (position + 1)); // Dummy usernames
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, feedbackText;

        ViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            feedbackText = itemView.findViewById(R.id.feedback_text);
        }
    }
}
