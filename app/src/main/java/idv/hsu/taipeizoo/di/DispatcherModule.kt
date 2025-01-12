package idv.hsu.taipeizoo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

/**
 * @Qualifier 用於依賴注入中區分同一型別但不同用途的依賴
 */
/**
 * @Retention 用來指定註解的「保留期」，即註解資訊保留到哪個階段:
 *   AnnotationRetention.SOURCE：只保留在原始碼中，編譯後會移除不保留在 .class 檔案中。
 *   AnnotationRetention.BINARY：保留到 .class 檔案中，不可透過反射（Reflection）取得。
 *   AnnotationRetention.RUNTIME：保留到執行階段，可透過反射（Reflection）取得。
 */
/**
 * annotation class 是用來宣告自訂註解的 Kotlin 語法。
 * 自訂註解可以用來標記某些類別、函數或屬性，並且結合 @Retention 和 @Qualifier 等用法來控制其行為。
 */

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher