package com.codestack.deepsense.model.service.module

import com.codestack.deepsense.model.service.AccountService
import com.codestack.deepsense.model.service.MyAccountService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: MyAccountService): AccountService
}
