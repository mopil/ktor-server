package com.example.core.util

import com.example.domain.model.ProductCategory
import com.example.domain.model.Products
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.random.Random

object DummyDataUtils {
    private val names = listOf("비트코인", "블로서리", "이더리움", "사과", "콜라", "커피", "책", "노트북", "키보드", "마우스")
    private val categories = ProductCategory.values()
    private val random = Random(System.currentTimeMillis())

    fun setDummyData() {
        setProductDummies()
    }

    private fun setProductDummies() = transaction {
        addLogger(StdOutSqlLogger)
        repeat(100) { times ->
            Products.insert {
                it[name] = "${names[random.nextInt(names.size)]} $times"
                it[category] = categories[random.nextInt(categories.size)]
                it[description] = "더미 제품 $times"
                it[price] = random.nextInt(5000, 50000)
            }
        }
    }
}
