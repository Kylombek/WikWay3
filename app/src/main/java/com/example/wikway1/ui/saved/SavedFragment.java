package com.example.wikway1.ui.saved;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wikway1.App;
import com.example.wikway1.JobAd;
import com.example.wikway1.R;
import com.example.wikway1.ui.VacancyAdapter;
import com.example.wikway1.ui.home.GalleryActivity;
import com.example.wikway1.ui.home.HomeFragment;
import com.example.wikway1.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class SavedFragment extends Fragment {
    private final String TAG = "SavedFragment";
    private App.OnDataReadyListener onDataReadyListener;
    private RecyclerView recyclerView;
    private ArrayList<JobAd> jobFavs = new ArrayList<>();
    VacancyAdapter adapter;
    private static SavedFragment instance;

    public static SavedFragment getInstance(){
        if(instance==null)
            instance = new SavedFragment();
        return instance;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_saved, container, false);
        initRecyclerView(view);
        getJobs();
        return view;
    }

    private void initRecyclerView(View view){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        recyclerView = view.findViewById(R.id.recycler_view_saved);
        adapter = new VacancyAdapter(getContext());
        initDB();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                Intent intent = new Intent(getContext(), GalleryActivity.class);
                intent.putExtra("image_url", adapter.jobs.get(pos).getImageLink());
                intent.putExtra("image_name", adapter.jobs.get(pos).getTitle());
                startActivity(intent);
            }

            @Override
            public void onItemLongCLick(int pos) {
                Toast.makeText(getContext(), "on long click: " + adapter.jobs.get(pos).getTitle(), Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getJobs() {
        onDataReadyListener = new App.OnDataReadyListener() {
            @Override
            public void setData(ArrayList<JobAd> jobs) {
                adapter.setJobs(jobFavs);
            }
        };
        App.setOnDataReadyListener(onDataReadyListener);
        App.parseJobs();
    }
    private void initDB(){
        jobFavs.clear();
        jobFavs.addAll(App.favoriteDbHelper.getAllFavorite()); // init job list
    }




}