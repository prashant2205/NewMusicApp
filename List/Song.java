package com.example.harshitkhanna.newmusicapp.List;

import com.example.harshitkhanna.newmusicapp.Database.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by harshitkhanna on 22/10/16.
 */
@Table(database = MyDatabase.class)
public class Song extends BaseModel{
    @Column
    public String name;
    @Column
    public boolean isFav;
    @PrimaryKey
    @Column
    public String path;
}
