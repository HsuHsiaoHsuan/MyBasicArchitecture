package idv.hsu.taipeizoo.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import idv.hsu.taipeizoo.data.source.TaipeiZooRemoteDataSource
import idv.hsu.taipeizoo.data.source.remote.TaipeiZooRemoteDataSourceImpl
import idv.hsu.taipeizoo.data.source.remote.TaipeiZooRemoteRepositoryImpl
import idv.hsu.taipeizoo.domain.repository.TaipeiZooRemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindTaipeiZooRemoteRepository(
        remoteRepository: TaipeiZooRemoteRepositoryImpl
    ): TaipeiZooRemoteRepository

    @Singleton
    @Binds
    abstract fun bindTaipeiZooRemoteDataSource(
        remoteDataSource: TaipeiZooRemoteDataSourceImpl
    ): TaipeiZooRemoteDataSource
}