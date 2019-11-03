package com.home.kotlinmvvmdaggerdemo.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeBean(
    @SerializedName("result")
    val result: Results
) : Parcelable {
    @Parcelize
    data class Results(
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("count")
        val count: Int,
        @SerializedName("sort")
        val sort: String,
        @SerializedName("results")
        val results: List<Result>
    ) : Parcelable {
        @Parcelize
        data class Result(
            @SerializedName("公司電話")
            val workPhone: String,
            @SerializedName("_id")
            val id2: Int,
            @SerializedName("id")
            val id: String,
            @SerializedName("管制編號")
            val regulatoryNumber: String,
            @SerializedName("清除機構")
            val clearingMechanism: String
        ) : Parcelable
    }
}