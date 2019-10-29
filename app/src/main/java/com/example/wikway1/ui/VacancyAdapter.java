package com.example.wikway1.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wikway1.App;
import com.example.wikway1.JobAd;
import com.example.wikway1.R;
import com.example.wikway1.ui.home.GalleryActivity;
import com.example.wikway1.utils.OnItemClickListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class VacancyAdapter extends RecyclerView.Adapter<VacancyAdapter.VacancyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    public ArrayList<JobAd> jobs;


    private OnItemClickListener onItemClickListener;
    public VacancyAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public VacancyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.vacancy_item, parent, false);
        return new VacancyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VacancyViewHolder holder, final int position) {
       initBookmarks(holder,position);
        holder.setData(jobs.get(position));
        //IFAVORITE BOOKMARK TEST
        holder.iFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jobs.get(position).isFav()){ //TODO(1) Rewrite method if
                    holder.iFavorite.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                    jobs.get(position).setFav(false);
                    Log.d("VacancyAdapter","setFAv False");
                    App.favoriteDbHelper.deleteFavorite(jobs.get(position).getTitle());

                }
                else {
                    holder.iFavorite.setImageResource(R.drawable.ic_bookmark_black_24dp);
                    jobs.get(position).setFav(true);
                    Log.d("VacancyAdapter","setFAv true");
                    App.favoriteDbHelper.addFavorite(jobs.get(position));

                }
            }
        });

    }

    private void initBookmarks(VacancyViewHolder holder, int position){
        if (jobs.get(position).isFav()){
            holder.iFavorite.setImageResource(R.drawable.ic_bookmark_black_24dp);
        }
        else holder.iFavorite.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
    }

    @Override
    public int getItemCount() {
        if (jobs == null) return 0;
        return jobs.size();
    }

    public void setJobs(ArrayList<JobAd> jobs) {
        this.jobs = jobs;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }

    class VacancyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView jobTitleTextView;
        ImageView iFavorite;
        VacancyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.companyLogoInList);
            jobTitleTextView = itemView.findViewById(R.id.jobNameTextView);
            iFavorite = itemView.findViewById(R.id.ivFavorite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition());

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongCLick(getAdapterPosition());
                    return true;
                }
            });
        }

        void setData(JobAd job) {
            Glide.with(context)
                    .asBitmap()
                    .load(job.getImageLink())
                    .into(imageView);
            jobTitleTextView.setText(job.getTitle());
        }
    }
}
