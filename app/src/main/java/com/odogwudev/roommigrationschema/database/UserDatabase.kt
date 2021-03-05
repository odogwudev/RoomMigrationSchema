package com.odogwudev.roommigrationschema.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.odogwudev.roommigrationschema.`interface`.UserDao
import com.odogwudev.roommigrationschema.datamodel.UserEntity
import com.odogwudev.roommigrationschema.datamodel.UserReportCardEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [UserEntity::class, UserReportCardEntity::class],
    version = 3,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE 'user_table' ADD COLUMN 'mobileNUmber' TEXT NOT NULL DEFAULT ''")
            }
        }
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'user_report_card_table' ('id' INTEGER NOT NULL,'subjectName' TEXT NOT NULL,'subjectGrade'TEXT NOT NULL,'subjectMarks' REAL NOT NULL, PRIMARY KEY( 'id')) ")
            }

        }

        @Volatile
        private var INSTANCE: UserDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                    .addCallback(UserDatabaseCallback(scope, context))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class UserDatabaseCallback(
        private val scope: CoroutineScope, context: Context
    ) : RoomDatabase.Callback() {

        private val mContext = context

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val userDao = database.userDao()

                    // Delete all content here.
                    userDao.deleteAllUserList()


                    //Add Data To Room
                    userDao.insertUser(
                        UserEntity(
                            userName = "odogwuDev",
                            cityName = "Portharcourt",
                            mobileNumber = "123456"
                        )
                    )

                    userDao.insertUser(
                        UserEntity(
                            userName = "Hamza",
                            cityName = "Lahore",
                            mobileNumber = "123"
                        )
                    )
                    userDao.insertUser(
                        UserEntity(
                            userName = "Mickarlz",
                            cityName = "Abuja",
                            mobileNumber = "123"
                        )
                    )
                    //Add data to user Report
                    userDao.insertUserReport(
                        UserReportCardEntity(
                            subjectName = "Statistics",
                            subjectGrade = "A",
                            subjectMarks = 70F
                        )
                    )

                    userDao.insertUserReport(
                        UserReportCardEntity(
                            subjectName = "Dsa",
                            subjectGrade = "C",
                            subjectMarks = 50F
                        )
                    )

                }
            }
        }
    }

}