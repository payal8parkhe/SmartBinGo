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

public class ManageDriversFragment extends Fragment {

    private RecyclerView recyclerView;
    private DriverAdapter adapter;
    private List<Driver> driverList;
    private EditText driverNameInput;
    private Button addDriverButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_drivers, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        driverNameInput = view.findViewById(R.id.edit_driver_name);
        addDriverButton = view.findViewById(R.id.btn_add_driver);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        driverList = new ArrayList<>();

        adapter = new DriverAdapter(driverList);
        recyclerView.setAdapter(adapter);

        addDriverButton.setOnClickListener(v -> {
            String driverName = driverNameInput.getText().toString().trim();
            if (!driverName.isEmpty()) {
                driverList.add(new Driver(driverName));
                adapter.notifyDataSetChanged();
                driverNameInput.setText("");
            } else {
                Toast.makeText(getContext(), "Enter driver name", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
