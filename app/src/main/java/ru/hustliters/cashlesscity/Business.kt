package ru.hustliters.cashlesscity

import com.yandex.mapkit.search.Category

data class Business(val name: String, val address: String, val category: List<Category>)