package com.example.shop_app.di

import java.lang.annotation.Documented
import javax.inject.Scope

@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope


@Scope
@Documented
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope