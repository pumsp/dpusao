package com.gcm.pumsper.dpusao;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import org.json.JSONArray;

import java.util.ArrayList;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title,detail;
        private ImageView image,imageData;
        private WebView detail_web;
        private View lin_layout;


        public ViewHolder(View v,String type) {
            super(v);
            // เพิ่มเช็ค type

            if(type.equals("news")){
                title   = (TextView) v.findViewById(R.id.txt_title);
                detail  = (TextView) v.findViewById(R.id.txt_detail);
                image   = (ImageView) v.findViewById(R.id.imageView);
            }
            else if(type.equals("data")){
                title   = (TextView) v.findViewById(R.id.txt_title_data);
                detail_web  = (WebView) v.findViewById(R.id.txt_detail_data);
                imageData = (ImageView) v.findViewById(R.id.image_data);


                //////////////
                lin_layout = v.findViewById(R.id.lin1);

            }

        }


    }

    private JSONArray jarray ;
    private String type;
    private FragmentManager fa;


    public recycleAdapter(JSONArray jarray,String type,FragmentManager fa){
        this.jarray = jarray;
        this.type = type;
        this.fa = fa;
    }

    private String ip = "http://ceproject2.dpu.ac.th/sao/";
    private Context mcontext ;
    @Override
    public recycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false);
        if(type.equals("data")){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.data_card, parent, false);
        }
        ViewHolder vh = new ViewHolder(v,type);
        mcontext = v.getContext();

        Log.w("create","cera");
        return vh;
    }

    @Override
    public void onBindViewHolder(recycleAdapter.ViewHolder holder, final int position) {
        try {
            Log.w("position","---------------------------------------"+position+"-----------------------------------");
            if(type.equals("news")){
                Log.w("detail", jarray.getJSONObject(position).getString("topic"));
                Log.w("detail", jarray.getJSONObject(position).getString("detail1"));
                holder.title.setText(jarray.getJSONObject(position).getString("topic"));
                holder.detail.setText(jarray.getJSONObject(position).getString("detail1"));
                StringBuilder pic_link = new StringBuilder(ip+"pages/"+jarray.getJSONObject(position).getString("pic"));

                Log.w("detail",pic_link.toString());

                Picasso.with(mcontext)
                        .load(pic_link.toString())
                        .placeholder(R.drawable.progress_animation)
                        .error(R.drawable.ic_error)
                        .fit()
                        .centerInside()
                        .into(holder.image);
            }
            else if(type.equals("data")){

//                Log.w("array_data",position+" ===> "+jarray.getJSONObject(position));

                holder.title.setText(jarray.getJSONObject(position).getString("name"));

                if(jarray.getJSONObject(position).getString("detail").length() != 0){


                    Log.w("webView_height", "" + holder.detail_web.getHeight());
                    if(holder.detail_web.getHeight() == 0){
                        holder.detail_web.loadData(jarray.getJSONObject(position).getString("detail"), "text/html; charset=utf-8", null);
                    }
                    else{
                        Log.w("webView_height", "" + holder.detail_web.getHeight());
                        holder.detail_web.loadUrl("about:blank");
                        holder.detail_web.loadData(jarray.getJSONObject(position).getString("detail"), "text/html; charset=utf-8", null);
                    }

                    holder.detail_web.setVisibility(View.VISIBLE);

                }
                else{
                    holder.detail_web.setVisibility(View.GONE);
                }

                JSONArray jarr = new JSONArray(jarray.getJSONObject(position).getString("pic"));
                if(jarr.length() == 0) {
                    holder.lin_layout.setVisibility(View.GONE);
                }
                else{

                    ArrayList<String> list = new ArrayList<>();
                    for(int i = 0; i < jarr.length();i++){
                        String pic_link = ip+"pages/"+jarr.getString(i);
                        list.add(pic_link);
//                        Log.w("list_for", list.get(i));
                    }

                    Log.w("picasso",list.get(0));
                    Picasso.with(mcontext)
                            .load(list.get(0))
                            .placeholder(R.drawable.progress_animation)
                            .error(R.drawable.ic_error)
                            .fit()
                            .centerInside()
                            .into(holder.imageData);
                    holder.lin_layout.setVisibility(View.VISIBLE);


                }


            }
        }catch (Exception e){
            e.printStackTrace();
            //bye
        }

    }

    @Override
    public int getItemCount() {
        return jarray.length();
    }

}
