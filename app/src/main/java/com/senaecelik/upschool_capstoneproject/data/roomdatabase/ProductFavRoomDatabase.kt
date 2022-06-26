package com.senaecelik.upschool_capstoneproject.data.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.senaecelik.upschool_capstoneproject.data.model.Product
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Product::class], version = 1)
abstract class ProductFavRoomDatabase : RoomDatabase() {

    abstract fun productBasketDAOInterface(): ProductFavDaoInterface

    companion object {
        @Volatile
        private var instance: ProductFavRoomDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun productFavRoomDatabase(context: Context): ProductFavRoomDatabase? {
            synchronized(this) {

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        ProductFavRoomDatabase::class.java,
                        "productFavDatabase.db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }
    }
}