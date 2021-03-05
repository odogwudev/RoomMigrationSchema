package com.odogwudev.roommigrationschema.interfaces

import androidx.room.*
import com.odogwudev.roommigrationschema.datamodel.UserEntity
import com.odogwudev.roommigrationschema.datamodel.UserReportCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    /****
     * User Dao
     */

    @Query("SELECT * FROM user_table")
    fun getAllUserList(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(userEntity: UserEntity)

    @Delete
    suspend fun deleteUser(userEntity: UserEntity)

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUserList()


    /** School Record
     *  Database object
     */

    @Query("SELECT * FROM user_report_card_table")
    fun getAllUserReportList(): Flow<List<UserReportCardEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserReport(userReportCardEntity: UserReportCardEntity)

    @Delete
    suspend fun deleteUserReport(userReportCardEntity: UserReportCardEntity)

    @Update
    suspend fun updateUserReport(userReportCardEntity: UserReportCardEntity)

    @Query("DELETE FROM user_report_card_table")
    suspend fun deleteAllUserReportList()

}