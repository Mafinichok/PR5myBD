package com.platon.kotlinmirealabs.repository

import com.example.pr5mybd.api.RetrofitInstance
import com.example.pr5mybd.db.ArticleDatabase
import com.example.pr5mybd.models.Article

class NewsRepository(val db: ArticleDatabase) {

    suspend fun getHeadlines(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getHeadlines(countryCode, pageNumber)

    suspend fun insert(article: Article) = db.articleDao().insert(article)

    fun getFavorites() = db.articleDao().getAllArticles()

}