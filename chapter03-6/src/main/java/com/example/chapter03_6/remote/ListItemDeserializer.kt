package com.example.chapter03_6.remote

import com.example.chapter03_6.model.Coupon
import com.example.chapter03_6.model.Empty
import com.example.chapter03_6.model.FullAd
import com.example.chapter03_6.model.Horizontal
import com.example.chapter03_6.model.Image
import com.example.chapter03_6.model.ListItem
import com.example.chapter03_6.model.Sale
import com.example.chapter03_6.model.SellItem
import com.example.chapter03_6.model.ViewPager
import com.example.chapter03_6.model.ViewType
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ListItemDeserializer : JsonDeserializer<ListItem> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ListItem {
        val viewTypeName = json?.asJsonObject?.getAsJsonPrimitive("viewType")?.asString
        val gson = GsonBuilder()
            .registerTypeAdapter(ListItem::class.java, ListItemDeserializer())
            .create()

        return try {
            when (viewTypeName) {
                ViewType.VIEW_PAGER.name -> gson.fromJson(json, ViewPager::class.java)
                ViewType.HORIZONTAL.name -> gson.fromJson(json, Horizontal::class.java)
                ViewType.FULL_AD.name -> gson.fromJson(json, FullAd::class.java)

                ViewType.SELL_ITEM.name -> gson.fromJson(json, SellItem::class.java)
                ViewType.IMAGE.name -> gson.fromJson(json, Image::class.java)
                ViewType.SALE.name -> gson.fromJson(json, Sale::class.java)
                ViewType.COUPON.name -> gson.fromJson(json, Coupon::class.java)

                else -> gson.fromJson(json, Empty::class.java)
            }
        } catch (e: Exception) {
            gson.fromJson(json, Empty::class.java)
        }
    }
}