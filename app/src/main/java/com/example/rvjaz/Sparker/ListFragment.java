package com.example.rvjaz.Sparker;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.interfaces.DynamicImageLoader;
import com.amigold.fundapter.extractors.StringExtractor;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.example.rvjaz.Sparker.Interfaces.Serializer;
import com.example.rvjaz.Sparker.Interfaces.UILConfig;

import java.util.ArrayList;


public class ListFragment extends Fragment implements AsyncResponse,AdapterView.OnItemClickListener{

    public static final String PREFS = "prefFile";
    private ArrayList<Serializer> productList;
    private ListView lv;
    FunDapter<Serializer> adapter;
    public String FinalData;
    public ArrayList<String> list;
    public BindDictionary dic;
    public String url = "http://18.221.188.117/mediapark/list.php";

    View view;


    public ListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.xml_layout, container, false);
        SharedPreferences preferences = ListFragment.this.getActivity().getSharedPreferences(PREFS, 0);
        ImageLoader.getInstance().init(UILConfig.config(ListFragment.this.getActivity()));
        PostResponseAsyncTask taskRead = new PostResponseAsyncTask(ListFragment.this.getActivity(), this);
        taskRead.execute(url);


        return view;
    }

    @Override
    public void processFinish(String s) {


            productList = new JsonConverter<Serializer>().toArrayList(s, Serializer.class);

        if (productList != null){
            dic = new BindDictionary();

            dic.addStringField(R.id.plateNumber, new StringExtractor<Serializer>() {
                @Override
                public String getStringValue(Serializer item, int position) {
                    return item.Platenumber;
                }
            });
            dic.addStringField(R.id.Name, new StringExtractor<Serializer>() {
                @Override
                public String getStringValue(Serializer item, int position) {
                    return item.Title;
                }
            });
            dic.addStringField(R.id.battery, new StringExtractor<Serializer>() {
                @Override
                public String getStringValue(Serializer item, int position) {
                    return item.Battery;
                }
            });
            dic.addStringField(R.id.Location, new StringExtractor<Serializer>() {
                @Override
                public String getStringValue(Serializer item, int position) {
                    return item.Adress;
                }
            });

            dic.addDynamicImageField(R.id.ivImage, new StringExtractor<Serializer>() {
                @Override
                public String getStringValue(Serializer item, int position) {
                    return item.Photo;
                }
            }, new DynamicImageLoader() {
                @Override
                public void loadImage(String url, ImageView img) {
                    //Set image
                    ImageLoader.getInstance().displayImage(url, img);
                }
            });


        }

        else
        {
            Toast.makeText(ListFragment.this.getActivity(), "Empty", Toast.LENGTH_LONG).show();

        }

        adapter = new FunDapter<>(ListFragment.this.getActivity(), productList, R.layout.product_row, dic);
        lv = view.findViewById(R.id.lvProduct);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Serializer selectedProduct = productList.get(position);


  }




}
