package com.example.bangijan69.robbydwih_1202150024_modul6;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bangijan69 on 3/31/2018.
 */

public class gridadaptor extends BaseAdapter {
    private Context mContext;
   // private final String[] web;
   // private final int[] Imageid;
    ArrayList<String> judul = new ArrayList<>();
    ArrayList<String> post =new ArrayList<>();
    ArrayList<Uri> gambar = new ArrayList<>();
    List<ImageUploadInfo> list = new ArrayList<>();
    public gridadaptor(Context c,ArrayList judul,ArrayList post,ArrayList gambar, List list ) {
        mContext = c;
        this.gambar = gambar;
        this.judul = judul;
        this.post=post;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        //return web.length;
       return judul.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text_judul);
            TextView textView_post =(TextView)grid.findViewById(R.id.grid_text_post);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(judul.get(position));
            textView_post.setText(post.get(position));
            //ImageUploadInfo UploadInfo = list.get(position);
            //Glide.with(mContext).load(UploadInfo.getImageURL()).into(imageView);
            //textView.setText(web[position]);
       //     imageView.setImageURI(gambar.get(position));
            //imageView.setImageResource(Imageid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
