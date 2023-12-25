package com.example.shop_app.utils.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store") //datastore instance
class PreferenceDataStore(val context: Context)

{


   companion object {
       private val USER_TOKEN = stringPreferencesKey("user_token")
   }

   suspend fun saveUserToken(accessToken:String){
       context.dataStore.edit { preferences->
           preferences[USER_TOKEN] = accessToken
       }
   }


    val accessToken: Flow<String?>
        get() = context.dataStore.data.map { preferences ->
            preferences[USER_TOKEN]
        }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}









