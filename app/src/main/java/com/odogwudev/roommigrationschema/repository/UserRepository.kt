package com.odogwudev.roommigrationschema.repository

import androidx.annotation.WorkerThread
import com.odogwudev.roommigrationschema.interfaces.UserDao
import com.odogwudev.roommigrationschema.datamodel.UserEntity
import com.odogwudev.roommigrationschema.datamodel.UserReportCardEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val userDao: UserDao
) {

    /***
     * user_table methods
     */

    val allUserList: Flow<List<UserEntity>> = userDao.getAllUserList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertUser(userEntity: UserEntity) {
        userDao.insertUser(userEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteUser(userEntity: UserEntity) {
        userDao.deleteUser(userEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateUser(userEntity: UserEntity) {
        userDao.updateUser(userEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllUserList() {
        userDao.deleteAllUserList()
    }

    /***
     * user_report_card_table methods
     */
    val allUserReportList: Flow<List<UserReportCardEntity>> = userDao.getAllUserReportList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertUserReport(userReportCardEntity: UserReportCardEntity) {
        userDao.insertUserReport(userReportCardEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteUserReport(userReportCardEntity: UserReportCardEntity) {
        userDao.deleteUserReport(userReportCardEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateUserReport(userReportCardEntity: UserReportCardEntity) {
        userDao.updateUserReport(userReportCardEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAllUserReportList() {
        userDao.deleteAllUserReportList()
    }
}