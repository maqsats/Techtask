package com.intechbel.techtask.feature.users.di

import com.intechbel.techtask.feature.users.data.api.UserApiService
import com.intechbel.techtask.feature.users.data.model.UserMapper
import com.intechbel.techtask.feature.users.data.repository.UserRepository
import com.intechbel.techtask.feature.users.domain.paging.UserPagingSource
import com.intechbel.techtask.feature.users.domain.repository.UserRepositoryImpl
import com.intechbel.techtask.feature.users.domain.usecase.UserPagingUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val userModules = module {
    single<UserApiService> {
        get<Retrofit>().create(UserApiService::class.java)
    }

    singleOf(::UserRepositoryImpl) bind UserRepository::class

    singleOf(::UserPagingUseCase)
    singleOf(::UserMapper)

    factory { UserPagingSource(pagingUseCase = get()) }
}
