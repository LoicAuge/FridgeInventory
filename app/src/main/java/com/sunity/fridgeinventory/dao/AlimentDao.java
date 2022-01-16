package com.sunity.fridgeinventory.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sunity.fridgeinventory.entity.Aliment;

import java.util.List;

@Dao
public interface AlimentDao {

    @Query("SELECT * FROM aliment")
    List<Aliment> getAll();

    @Query("SELECT * FROM aliment WHERE barCode = :barCode")
    Aliment findByBarCode(double barCode);

    @Insert
    void insertAll(Aliment... users);

    @Delete
    void delete(Aliment user);
}
