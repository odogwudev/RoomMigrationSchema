package com.odogwudev.roommigrationschema

import android.app.Application
import com.odogwudev.roommigrationschema.database.UserDatabase
import com.odogwudev.roommigrationschema.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MainApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { UserDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { UserRepository(database.userDao()) }
}