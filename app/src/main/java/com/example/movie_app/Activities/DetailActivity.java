package com.example.movie_app.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movie_app.Adapter.ActorListAdapeter;
import com.example.movie_app.Adapter.CategoryEachFilmListAdapter;
import com.example.movie_app.Domain.Filmtemp;
import com.example.movie_app.R;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
private RequestQueue mRequestQueue;
private StringRequest mStringRequest;
private ProgressBar progressBar;
private TextView titleTxt,movieRateText,movieTimeTxt,movieSummeryInfo,movieActorInfo;
private  int idFilm;
private ImageView pic2, backImg;
private RecyclerView.Adapter adapterActorList, adapterCategory;
private RecyclerView recyclerViewActor, recyclerViewCategory;
private NestedScrollView scrolview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        idFilm=getIntent().getIntExtra("id",0);
        initView();
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue= Volley.newRequestQueue(this);
        progressBar.setVisibility(View.VISIBLE);
        scrolview.setVisibility(View.GONE);

        mStringRequest= new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            Gson gson=new Gson();
            scrolview.setVisibility(View.VISIBLE);

            Filmtemp item=gson.fromJson(response, Filmtemp.class);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(pic2);
            titleTxt.setText(item.getTitle());
            movieRateText.setText(item.getImdbRating());
            movieTimeTxt.setText(item.getRuntime());
            movieSummeryInfo.setText(item.getPlot());
            movieActorInfo.setText(item.getActors());
            if (item.getImages() !=null){
                adapterActorList=new ActorListAdapeter(item.getImages());
                recyclerViewActor.setAdapter(adapterActorList);
            }
            if(item.getGenres() !=null){
                adapterCategory= new CategoryEachFilmListAdapter(item.getGenres());
                recyclerViewCategory.setAdapter(adapterCategory);
            }
        }, error -> progressBar.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        titleTxt=findViewById(R.id.moviesnametxt);
        progressBar=findViewById(R.id.progressBardetail);
        scrolview=findViewById(R.id.scrollView4);
        pic2=findViewById(R.id.picdetail);
        movieRateText=findViewById(R.id.moviestar);
        movieTimeTxt=findViewById(R.id.movietime);
        movieSummeryInfo=findViewById(R.id.moviesummer);
        movieActorInfo=findViewById(R.id.moviesactorinfo);
        backImg=findViewById(R.id.backdetail);
        recyclerViewCategory=findViewById(R.id.genresview);
        recyclerViewActor=findViewById(R.id.imagesRecyler);
        recyclerViewActor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

backImg.setOnClickListener(view -> finish());
    }
}