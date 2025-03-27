package com.example.smartbingo;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class AdminDashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNavigationView;
    private NavigationView sideNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNavigationView = findViewById(R.id.admin_bottom_navigation);
        sideNavigationView = findViewById(R.id.admin_nav_view);

        // Set default fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.admin_fragment_container, new HomeFragment()).commit();

        // Handle Bottom Navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_admin_home) {
                selectedFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_admin_schedules) {
                selectedFragment = new ScheduleFragment();
            } else if (item.getItemId() == R.id.nav_admin_feedback) {
                selectedFragment = new Feedback1Fragment();
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.admin_fragment_container, selectedFragment).commit();

            return true;
        });

        // Handle Side Navigation
        sideNavigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_manage_drivers) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.admin_fragment_container, new ManageDriversFragment()).commit();
            } else if (item.getItemId() == R.id.nav_manage_vehicles) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.admin_fragment_container, new ManageVehiclesFragment()).commit();
            } else if (item.getItemId() == R.id.nav_logout) {
                startActivity(new Intent(AdminDashboardActivity.this, LoginActivity.class));
                finish();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Toggle for Side Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
}
