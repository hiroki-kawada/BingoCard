package com.example.bingocard

import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.button.MaterialButton

/**
 * ビンゴカード画面_ViewModel
 */
class MainViewModel : ViewModel() {

    private val firstColumnNumber = (1..15)
    private val secondColumnNumber = (16..30)
    private val thirdColumnNumber = (31..45)
    private val fourthColumnNumber = (46..60)
    private val fifthColumnNumber = (61..75)

    val firstColumnDisplayList: MutableLiveData<MutableList<Int>> by lazy {
        MutableLiveData<MutableList<Int>>()
    }

    val secondColumnDisplayList: MutableLiveData<MutableList<Int>> by lazy {
        MutableLiveData<MutableList<Int>>()
    }

    val thirdColumnDisplayList: MutableLiveData<MutableList<Int>> by lazy {
        MutableLiveData<MutableList<Int>>()
    }

    val fourthColumnDisplayList: MutableLiveData<MutableList<Int>> by lazy {
        MutableLiveData<MutableList<Int>>()
    }

    val fifthColumnDisplayList: MutableLiveData<MutableList<Int>> by lazy {
        MutableLiveData<MutableList<Int>>()
    }

    private val tapNumberList: MutableList<Int> = mutableListOf()

    /**
     * ビンゴカード作成
     */
    fun createBingoCard() {
        //1列目
        val firstList = mutableListOf<Int>()
        while (firstList.size < 5) {
            val firstColumnDisplayNumber = firstColumnNumber.random()
            if (!firstList.contains(firstColumnDisplayNumber)) {
                firstList.add(firstColumnDisplayNumber)
            }
        }
        firstColumnDisplayList.value = firstList

        //2列目
        val secondList = mutableListOf<Int>()
        while (secondList.size < 5) {
            val firstColumnDisplayNumber = secondColumnNumber.random()
            if (!secondList.contains(firstColumnDisplayNumber)) {
                secondList.add(firstColumnDisplayNumber)
            }
        }
        secondColumnDisplayList.value = secondList

        //3列目
        val thirdList = mutableListOf<Int>()
        while (thirdList.size < 4) {
            val firstColumnDisplayNumber = thirdColumnNumber.random()
            if (!thirdList.contains(firstColumnDisplayNumber)) {
                thirdList.add(firstColumnDisplayNumber)
            }

        }
        thirdColumnDisplayList.value = thirdList

        //4列目
        val fourthList = mutableListOf<Int>()
        while (fourthList.size < 5) {
            val firstColumnDisplayNumber = fourthColumnNumber.random()
            if (!fourthList.contains(firstColumnDisplayNumber)) {
                fourthList.add(firstColumnDisplayNumber)
            }
        }
        fourthColumnDisplayList.value = fourthList

        //5列目
        val fifthList = mutableListOf<Int>()
        while (fifthList.size < 5) {
            val firstColumnDisplayNumber = fifthColumnNumber.random()
            if (!fifthList.contains(firstColumnDisplayNumber)) {
                fifthList.add(firstColumnDisplayNumber)
            }
        }
        fifthColumnDisplayList.value = fifthList

    }

    /**
     * ビンゴカード押下処理_端以外
     */
    fun tapBingoNumber(
        view: View,
        tapNumber: Int,
        lineNumber: Int,
        columnNumber: Int
    ) {
        if (tapNumber in tapNumberList) {
            return
        }
        tapNumberList.add(tapNumber)
        view.setBackgroundColor(Color.GRAY)
        if (tapNumberList.size < 3) {
            return
        }
        //縦ライン確認
        columnCheck(columnNumber)
        //横ライン確認
        lineCheck(lineNumber)
    }

    fun tapEdgeBingoNumber(view: View,
        tapNumber: Int, lineNumber: Int, columnNumber: Int
    ) {
        if (tapNumber in tapNumberList) {
            return
        }
        view.setBackgroundColor(Color.GRAY)
        tapNumberList.add(tapNumber)
        if (tapNumberList.size < 3) {
            return
        }

        val slantingNumberList: MutableList<Int> = mutableListOf()
        if (lineNumber == 0 && columnNumber == 0) {
            slantingNumberList.add(firstColumnDisplayList.value?.get(0) ?: 0)
            slantingNumberList.add(secondColumnDisplayList.value?.get(1) ?: 0)
            slantingNumberList.add(fourthColumnDisplayList.value?.get(3) ?: 0)
            slantingNumberList.add(fifthColumnDisplayList.value?.get(4) ?: 0)

        } else if (lineNumber == 0 && columnNumber == 4) {
            slantingNumberList.add(firstColumnDisplayList.value?.get(4) ?: 0)
            slantingNumberList.add(secondColumnDisplayList.value?.get(3) ?: 0)
            slantingNumberList.add(fourthColumnDisplayList.value?.get(1) ?: 0)
            slantingNumberList.add(fifthColumnDisplayList.value?.get(0) ?: 0)

        } else if (lineNumber == 4 && columnNumber == 0) {
            slantingNumberList.add(firstColumnDisplayList.value?.get(4) ?: 0)
            slantingNumberList.add(secondColumnDisplayList.value?.get(3) ?: 0)
            slantingNumberList.add(fourthColumnDisplayList.value?.get(1) ?: 0)
            slantingNumberList.add(fifthColumnDisplayList.value?.get(0) ?: 0)

        } else if (lineNumber == 4 && columnNumber == 4) {
            slantingNumberList.add(firstColumnDisplayList.value?.get(0) ?: 0)
            slantingNumberList.add(secondColumnDisplayList.value?.get(1) ?: 0)
            slantingNumberList.add(fourthColumnDisplayList.value?.get(3) ?: 0)
            slantingNumberList.add(fifthColumnDisplayList.value?.get(4) ?: 0)

        }
        if (tapNumberList.containsAll(slantingNumberList)) {
            Log.d("bingo", "斜めライン揃ったよ")
        }

        //縦ライン確認
        columnCheck(columnNumber)
        //横ライン確認
        lineCheck(lineNumber)
    }

    /**
     * 縦ラインチェック
     */
    private fun columnCheck(columnNumber: Int) {
        when (columnNumber) {
            0 -> {
                if (firstColumnDisplayList.value?.let { tapNumberList.containsAll(it) } == true) {
                    Log.d("bingo", "縦ライン揃ったよ")
                }
            }
            1 -> {
                if (secondColumnDisplayList.value?.let { tapNumberList.containsAll(it) } == true) {
                    Log.d("bingo", "縦ライン揃ったよ")
                }
            }
            2 -> {
                if (thirdColumnDisplayList.value?.let { tapNumberList.containsAll(it) } == true) {
                    Log.d("bingo", "縦ライン揃ったよ")
                }
            }
            3 -> {
                if (fourthColumnDisplayList.value?.let { tapNumberList.containsAll(it) } == true) {
                    Log.d("bingo", "縦ライン揃ったよ")
                }
            }
            4 -> {
                if (fifthColumnDisplayList.value?.let { tapNumberList.containsAll(it) } == true) {
                    Log.d("bingo", "縦ライン揃ったよ")
                }
            }
            else -> Log.d("bingo", "予想外")
        }
    }

    /**
     * 横ラインチェック
     */
    private fun lineCheck(lineNumber: Int) {
        when (lineNumber) {
            0 -> {
                val lineNumberList: MutableList<Int> =
                    mutableListOf(
                        firstColumnDisplayList.value?.get(lineNumber) ?: 0,
                        secondColumnDisplayList.value?.get(lineNumber) ?: 0,
                        thirdColumnDisplayList.value?.get(lineNumber) ?: 0,
                        fourthColumnDisplayList.value?.get(lineNumber) ?: 0,
                        fifthColumnDisplayList.value?.get(lineNumber) ?: 0
                    )
                if (tapNumberList.containsAll(lineNumberList)) {
                    Log.d("bingo", "横ライン揃ったよ")
                }
            }
            1 -> {
                val lineNumberList: MutableList<Int> =
                    mutableListOf(
                        firstColumnDisplayList.value?.get(lineNumber) ?: 0,
                        secondColumnDisplayList.value?.get(lineNumber) ?: 0,
                        thirdColumnDisplayList.value?.get(lineNumber) ?: 0,
                        fourthColumnDisplayList.value?.get(lineNumber) ?: 0,
                        fifthColumnDisplayList.value?.get(lineNumber) ?: 0
                    )
                if (tapNumberList.containsAll(lineNumberList)) {
                    Log.d("bingo", "横ライン揃ったよ")
                }
            }
            2 -> {
                val lineNumberList: MutableList<Int> =
                    mutableListOf(
                        firstColumnDisplayList.value?.get(lineNumber) ?: 0,
                        secondColumnDisplayList.value?.get(lineNumber) ?: 0,
                        fourthColumnDisplayList.value?.get(lineNumber) ?: 0,
                        fifthColumnDisplayList.value?.get(lineNumber) ?: 0
                    )
                if (tapNumberList.containsAll(lineNumberList)) {
                    Log.d("bingo", "横ライン揃ったよ")
                }
            }
            3 -> {
                val lineNumberList: MutableList<Int> =
                    mutableListOf(
                        firstColumnDisplayList.value?.get(lineNumber) ?: 0,
                        secondColumnDisplayList.value?.get(lineNumber) ?: 0,
                        thirdColumnDisplayList.value?.get(lineNumber - 1) ?: 0,
                        fourthColumnDisplayList.value?.get(lineNumber) ?: 0,
                        fifthColumnDisplayList.value?.get(lineNumber) ?: 0
                    )
                if (tapNumberList.containsAll(lineNumberList)) {
                    Log.d("bingo", "横ライン揃ったよ")
                }
            }
            4 -> {
                val lineNumberList: MutableList<Int> =
                    mutableListOf(
                        firstColumnDisplayList.value?.get(lineNumber) ?: 0,
                        secondColumnDisplayList.value?.get(lineNumber) ?: 0,
                        thirdColumnDisplayList.value?.get(lineNumber - 1) ?: 0,
                        fourthColumnDisplayList.value?.get(lineNumber) ?: 0,
                        fifthColumnDisplayList.value?.get(lineNumber) ?: 0
                    )
                if (tapNumberList.containsAll(lineNumberList)) {
                    Log.d("bingo", "横ライン揃ったよ")
                }
            }
            else -> Log.d("bingo", "予想外")
        }
    }

}