package com.example.smartbingo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ManageVehiclesFragment extends Fragment {

    private RecyclerView recyclerView;
    private VehicleAdapter adapter;
    private List<Vehicle> vehicleList;
    private EditText vehicleNameInput;
    private Button addVehicleButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_vehicles, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        vehicleNameInput = view.findViewById(R.id.edit_vehicle_name);
        addVehicleButton = view.findViewById(R.id.btn_add_vehicle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        vehicleList = new ArrayList<>();

        adapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(adapter);

        addVehicleButton.setOnClickListener(v -> {
            String vehicleName = vehicleNameInput.getText().toString().trim();
            if (!vehicleName.isEmpty()) {
                vehicleList.add(new Vehicle(vehicleName));
                adapter.notifyDataSetChanged();
                vehicleNameInput.setText("");
            } else {
                Toast.makeText(getContext(), "Enter vehicle name", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
