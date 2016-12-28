package com.example.harshitkhanna.newmusicapp;

import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.harshitkhanna.newmusicapp.List.Song;
import com.example.harshitkhanna.newmusicapp.List.SongAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshitkhanna on 23/10/16.
 */


public class SongFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    ArrayList<Song> songArrayList;
    SongAdapter songAdapter;
    MediaPlayer mp;
    public SongFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView lv= (ListView) rootView.findViewById(R.id.fragment_main_song_lv);
        songArrayList=new ArrayList<>();
        mp=new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //fetching songs from phone
        Bundle b=getArguments();
        int sec=b.getInt("section_number");
        if(sec==1)
            populateSongArrayList();
        else if(sec==2)
        {

        }
        else if(sec==3){
            populateFavArrayList();
        }
        songAdapter=new SongAdapter(getActivity(),songArrayList);
        lv.setAdapter(songAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PlaySong(songArrayList.get(i).path);
            }
        });
        return rootView;
    }

    private void PlaySong(String path) {
        Uri songUri=Uri.parse(path);
        if(mp.isPlaying()){
            mp.reset();
        }
        try {
            mp.setDataSource(getActivity(),songUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (IllegalStateException e){
            e.printStackTrace();
        }

        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.start();
    }

    private void populateFavArrayList() {
        songArrayList.clear();
        List<Song> songs= SQLite.select().from(Song.class).queryList();
        for(Song s:songs){
            if(s.isFav)
                songArrayList.add(s);
        }
    }

    private void populateSongArrayList() {
        songArrayList.clear();
        List<Song> songs= SQLite.select().from(Song.class).queryList();
        for(Song s:songs){
            songArrayList.add(s);
        }
    }

}
