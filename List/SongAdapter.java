package com.example.harshitkhanna.newmusicapp.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import com.example.harshitkhanna.newmusicapp.R;

import java.util.ArrayList;

/**
 * Created by harshitkhanna on 22/10/16.
 */

public class SongAdapter extends ArrayAdapter {

    Context mcontext;
    ArrayList<Song> msongs;

    public SongAdapter(Context context, ArrayList<Song> objects) {
        super(context, 0, objects);
        msongs=objects;
        mcontext=context;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView tv;
        ImageView isFav;

        public ViewHolder(ImageView imageView, TextView tv, ImageView isFav) {
            this.imageView = imageView;
            this.tv = tv;
            this.isFav = isFav;
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=View.inflate(mcontext, R.layout.song_list_view,null);
            ImageView iv= (ImageView) convertView.findViewById(R.id.song_list_icon);
            TextView tv= (TextView) convertView.findViewById(R.id.song_list_songName);
            ImageView fav= (ImageView) convertView.findViewById(R.id.song_list_isFav);
            ViewHolder vh=new ViewHolder(iv,tv,fav);
            convertView.setTag(vh);
        }
        ViewHolder vh= (ViewHolder) convertView.getTag();
        Song song=msongs.get(position);
        //Add song image
        vh.tv.setText(song.name);
        if(song.isFav==true){
            vh.isFav.setImageResource(android.R.drawable.star_big_on);
        }
        else{
            vh.isFav.setImageResource(android.R.drawable.star_big_off);
        }
        return convertView;
    }
}
