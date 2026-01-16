package aman.jusplay.ui.adapters;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import aman.jusplay.R;
import aman.jusplay.data.model.SidebarItem;

public class SidebarAdapter extends RecyclerView.Adapter<SidebarAdapter.ViewHolder> {

    private List<SidebarItem> items;
    private final OnItemClickListener listener;
    private int selectedPosition = 0;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public SidebarAdapter(List<SidebarItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sidebar_nav, parent, false);
        return new ViewHolder(view);
    }

        @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SidebarItem item = items.get(position);
        holder.navText.setText(item.getTitle());
        holder.navIcon.setImageResource(item.getIconResId());

        // SELECTION LOGIC
        if (selectedPosition == position) {
            // ACTIVE STATE
            holder.navText.setTextColor(Color.WHITE);
            holder.navText.setTypeface(null, Typeface.BOLD);
            holder.navText.setAlpha(1.0f);
            
            holder.navIcon.setColorFilter(Color.WHITE); 
            holder.navIcon.setAlpha(1.0f);

            // Show the Pill Background (Dark Grey/Accent)
            holder.iconContainer.setVisibility(View.VISIBLE);
            // You can change this color to match your app theme (e.g., #333333 or Blue)
            holder.iconContainer.setBackgroundResource(R.drawable.bg_nav_pill);
            holder.iconContainer.getBackground().setTint(Color.parseColor("#33FFFFFF")); 

        } else {
            // INACTIVE STATE
            holder.navText.setTextColor(Color.LTGRAY);
            holder.navText.setTypeface(null, Typeface.NORMAL);
            holder.navText.setAlpha(0.6f);
            
            holder.navIcon.setColorFilter(Color.LTGRAY);
            holder.navIcon.setAlpha(0.6f);

            // Hide the Pill Background (Make it transparent)
            holder.iconContainer.setBackground(null);
        }

        holder.itemView.setOnClickListener(v -> {
            int previousItem = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(previousItem);
            notifyItemChanged(selectedPosition);
            listener.onItemClick(item.getTitle());
        });
    }

    
    


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView navText;
        ImageView navIcon;
        View iconContainer; // NEW

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            navText = itemView.findViewById(R.id.nav_text);
            navIcon = itemView.findViewById(R.id.nav_icon);
            iconContainer = itemView.findViewById(R.id.icon_container); // Bind the container
        }
    }
}
