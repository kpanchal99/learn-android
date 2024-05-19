package com.example.sem2project;

import android.content.Intent;
import android.content.res.Resources;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class HomePage extends AppCompatActivity implements SelectListener {


    RecyclerView recyclerView;
    List<MyModel> myModelList;
    CustomListAdapter customListAdapter;

    SearchView searchView;

    HashMap<String, String> activitiesMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        searchView = findViewById(R.id.search_view);
        displayItems();

        getActivityList();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private void getActivityList() {
        // Get the string array from resources
        String[] activitiesArray = getResources().getStringArray(R.array.activities);

        // Initialize a HashMap to store the key-value pairs
        activitiesMap = new HashMap<>();

        // Parse the string array to fill the HashMap
        for (String activity : activitiesArray) {
            String[] keyValue = activity.split(",");
            if (keyValue.length == 2) {
                activitiesMap.put(keyValue[0], keyValue[1]);
            }
        }

        // Now you can use the HashMap as needed
        // For example, printing the values
//        for (Map.Entry<String, String> entry : activitiesMap.entrySet()) {
//            Log.d("ActivityMap", "Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }
        // Get the values from the HashMap
//        List<String> activityValues = new ArrayList<>(activitiesMap.values());
//
//        // Create an ArrayAdapter using the values list
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, activityValues);
//
//        // Assuming you have a ListView with id 'listView'
//        ListView listView = findViewById(R.id.listView);
//        listView.setAdapter(arrayAdapter);
    }

    private void filter(String newText) {
        List<MyModel> filterList = new ArrayList<>();
        for(MyModel item : myModelList){
            if(item.getTitle().toLowerCase().contains(newText.toLowerCase())){
                 filterList.add(item);
            }
        }

        //send to adapter
        customListAdapter.filterList(filterList);
    }

    private void displayItems(){
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        myModelList = new ArrayList<>();

        myModelList.add(new MyModel("Change Wallpaper",SetWallpaper.class));
//        myModelList.add(new MyModel("ToastExample","toastExample"));
        myModelList.add(new MyModel("Calculator",Calculator.class));
        myModelList.add(new MyModel("Implicit Intent",ImplicitIntent.class));
        myModelList.add(new MyModel("Multiple Intent",MultipleIntentInOne.class));
        myModelList.add(new MyModel("Email Intent",EmailIntent.class));
        myModelList.add(new MyModel("Explicit Intent",ExplicitIntentExample.class));
        myModelList.add(new MyModel("AlertDialog Box",AlertDialogBox.class));
        myModelList.add(new MyModel("SQLLiteDB - CRUD",SQLLite_CRUD.class));
        myModelList.add(new MyModel("UI Controls",UI_Controls.class));
        myModelList.add(new MyModel("Menu",MenuExample.class));
//        myModelList.add(new MyModel("Internationalization and Localise","12"));
//        myModelList.add(new MyModel("firebaseExample","firebaseExample"));

        customListAdapter = new CustomListAdapter(this, myModelList, this);
        recyclerView.setAdapter(customListAdapter);
    }

    @Override
    public void onItemClicked(MyModel myModel) {
        Toast.makeText(this, myModel.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(getApplicationContext(), myModel.getActivityName());
        startActivity(intent);
    }
}
