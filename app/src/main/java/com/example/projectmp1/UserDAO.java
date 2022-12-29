package com.example.projectmp1;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
//DATA ACCESS OBJECT
public interface UserDAO {

    @Insert
    void RegisterUser(UserEntity userEntity);

    @Query("SELECT * from users where userId = (:userId) and password = (:password)")
    UserEntity login(String userId, String password);
}
