package com.example.weedapiconsume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.weedapiconsume.adapters.FilmsAdapter;
import com.example.weedapiconsume.models.Films;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import kotlin.reflect.TypeOfKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
FilmsAdapter filmsAdapter;
List<Films>filmsList=new ArrayList<>();
String Url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //data();
        recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
//        filmsAdapter=new FilmsAdapter(this,filmsList);
//        recyclerView.setAdapter(filmsAdapter);

        OkHttpClient client=new OkHttpClient();

        Request request=new Request.Builder()
                .url("https://swapi.py4e.com/api/films/?format=json")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Toast.makeText(MainActivity.this, "No se pudo realizar la consulta", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json= Objects.requireNonNull(response.body()).string();
                JsonArray jsonArray;
                JsonObject jsonObject=new Gson().fromJson(json ,(Type) JsonObject.class);
                jsonArray=jsonObject.getAsJsonArray("results");
                Gson gson=new GsonBuilder().create();
                Type list=new TypeToken<List<Films>>(){}.getType();
                filmsList=gson.fromJson(jsonArray.toString(),list);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        filmsAdapter=new FilmsAdapter(getApplicationContext(),filmsList);
                        recyclerView.setAdapter(filmsAdapter);
                    }
                });
            }
        });
        update();
    }

//    public void data() {
//        Films films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(1);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//
//         films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(2);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//
//        films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(3);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//
//        films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(4);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//
//        films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(5);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//
//        films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(6);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//
//        films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(7);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//
//        films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(8);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//
//        films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(9);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//
//        films=new Films();
//        films.setTitle("title");
//        films.setEpisode_id(10);
//        films.setOpening_crawl("opening");
//        films.setDirector("director");
//        films.setProducer("producer");
//        films.setUrl("url");
//        films.setCreated("created");
//        films.setEdited("edited");
//        filmsList.add(films);
//    }
    public void update(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                filmsAdapter=new FilmsAdapter(getApplicationContext(),filmsList);
                filmsAdapter.notifyDataSetChanged();
            }
        });
    }
}