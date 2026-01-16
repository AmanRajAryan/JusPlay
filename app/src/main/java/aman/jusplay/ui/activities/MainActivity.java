package aman.jusplay.ui.activities;

import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Toast; // Added for testing

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import aman.jusplay.R;
import aman.jusplay.ui.fragments.SidebarFragment; // Import your Fragment

// 1. IMPLEMENT THE INTERFACE HERE
public class MainActivity extends AppCompatActivity implements SidebarFragment.SidebarListener {

    private ConstraintLayout rootLayout;
    private View sidebarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.root_layout);
        sidebarFragment = findViewById(R.id.fragment_sidebar);
    }

    private void animateUI() {
        AutoTransition transition = new AutoTransition();
        transition.setDuration(400);
        TransitionManager.beginDelayedTransition(rootLayout, transition);
    }

    // --- SIDEBAR LISTENER METHODS (Required to stop the crash) ---

    @Override
    public void onNavigationItemSelected(String item) {
        // This is called when you click a sidebar item (e.g., "Songs")
        // For now, let's just show a toast to prove it works
        Toast.makeText(this, "Clicked: " + item, Toast.LENGTH_SHORT).show();
        
        // TODO: Later we will swap the content fragment here
    }

    @Override
    public void onModeSwitched(boolean isVideoMode) {
        // This is called when you click the Audio/Video toggle
        Toast.makeText(this, "Video Mode: " + isVideoMode, Toast.LENGTH_SHORT).show();
        
        // TODO: Later we will filter the list based on this
    }
}
