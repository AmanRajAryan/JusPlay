package aman.jusplay.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import aman.jusplay.R;
import aman.jusplay.ui.adapters.SidebarAdapter;
import aman.jusplay.data.model.SidebarItem;

public class SidebarFragment extends Fragment {

    public interface SidebarListener {
        void onNavigationItemSelected(String item);
        void onModeSwitched(boolean isVideoMode);
    }

    private SidebarListener listener;
    private RecyclerView recyclerView;
    private SidebarAdapter adapter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SidebarListener) {
            listener = (SidebarListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SidebarListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sidebar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        recyclerView = view.findViewById(R.id.rv_sidebar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Disable default animator to prevent blinking glitch
        recyclerView.setItemAnimator(null);

        List<SidebarItem> menuItems = new ArrayList<>();
        // Using your existing icons
        menuItems.add(new SidebarItem("Quick Picks", R.drawable.ic_music_note)); 
        menuItems.add(new SidebarItem("Songs", R.drawable.ic_music_note));
        menuItems.add(new SidebarItem("Artists", R.drawable.ic_artist));       
        menuItems.add(new SidebarItem("Albums", R.drawable.ic_album));         
        menuItems.add(new SidebarItem("Playlists", R.drawable.ic_playlist));   

        adapter = new SidebarAdapter(menuItems, item -> {
            if (listener != null) listener.onNavigationItemSelected(item);
        });
        
        recyclerView.setAdapter(adapter);
    }
}
