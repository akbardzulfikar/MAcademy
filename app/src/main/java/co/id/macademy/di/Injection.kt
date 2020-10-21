package co.id.macademy.di

import android.content.Context
import co.id.macademy.data.source.AcademyRepository
import co.id.macademy.data.source.remote.RemoteDataSource
import co.id.macademy.utils.JsonHelper

object Injection {
    fun providerRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}