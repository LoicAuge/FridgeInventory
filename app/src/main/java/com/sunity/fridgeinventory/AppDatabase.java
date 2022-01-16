package com.sunity.fridgeinventory;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sunity.fridgeinventory.dao.AlimentDao;
import com.sunity.fridgeinventory.entity.Aliment;

@Database(entities = {Aliment.class}, version=1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract AlimentDao alimentDao();
}
