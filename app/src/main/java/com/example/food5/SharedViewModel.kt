package com.example.food5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class SharedViewModel : ViewModel() {
    val selectedItems = MutableLiveData<MutableSet<String>>(mutableSetOf())

    fun toggleItem(foodName: String) {
        val currentSet = selectedItems.value ?: mutableSetOf()
        if (currentSet.contains(foodName)) {
            currentSet.remove(foodName)
        } else {
            currentSet.add(foodName)
        }
        selectedItems.value = currentSet
        SharedAllergens.selectedItems = currentSet
    }


    fun isSelected(foodName: String): Boolean {
        return selectedItems.value?.contains(foodName) == true
    }
}