package uz.gita.newsappxml.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.noteappplaymarketyb.navigation.Handler
import uz.gita.noteappplaymarketyb.navigation.NavigationDispatcher
import uz.gita.noteappplaymarketyb.navigation.Navigator

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindsNavigator(impl: NavigationDispatcher): Navigator

    @Binds
    fun bindsHandler(impl: NavigationDispatcher): Handler
}