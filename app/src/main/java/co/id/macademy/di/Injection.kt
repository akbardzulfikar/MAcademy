package co.id.macademy.di

import android.content.Context
import co.id.macademy.data.AcademyRepository
import co.id.macademy.data.source.local.LocalDataSource
import co.id.macademy.data.source.local.room.AcademyDatabase
import co.id.macademy.data.source.remote.RemoteDataSource
import co.id.macademy.utils.AppExecutors
import co.id.macademy.utils.JsonHelper

object Injection {
    fun providerRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}