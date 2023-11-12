package com.study.core.views

import androidx.fragment.app.Fragment
import com.study.core.contracts.FragmentsHolder

/**
 * Базовий клас для усіх фрагментів
 */
abstract class BaseFragment: Fragment() {

    /**
     * Обов'язкова [BaseViewModel] яка менеджить це фрагмент
     */
    abstract val viewModel: BaseViewModel

    /**
     * Цей метод викликається якщо потрібно щось перерендирити
     * наприклад [ToolBar]
     */
    fun notifyScreenUpdates() {
        (requireActivity() as FragmentsHolder).notifyScreenUpdates()
    }
}