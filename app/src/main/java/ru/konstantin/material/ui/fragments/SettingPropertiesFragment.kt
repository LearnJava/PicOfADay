package ru.konstantin.material.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.konstantin.material.R
import ru.konstantin.material.databinding.FragmentSettingPropertiesBinding

class SettingPropertiesFragment : Fragment() {
    private var _binding: FragmentSettingPropertiesBinding? = null
    private val binding get() = _binding!!

    companion object {

        @JvmStatic
        fun newInstance() =
            SettingPropertiesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingPropertiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        activity?.let {
//            val settingsStorage = SettingsStorage(it)
//            when (settingsStorage.theme) {
//                R.style.Theme_NearSpace -> binding.choiceThemeDefault.isChecked = true
//                R.style.Theme_NearSpace_Martian -> binding.choiceThemeMartian.isChecked = true
//            }
//            binding.choiceTheme.setOnCheckedChangeListener { group, checkedId ->
//                val themeId = if (checkedId == R.id.choice_theme_martian) {
//                    R.style.Theme_NearSpace_Martian
//                } else {
//                    R.style.Theme_NearSpace
//                }
//                if (themeId != settingsStorage.theme) {
//                    settingsStorage.theme = themeId
//                    it.setTheme(themeId)
//                    it.recreate()
//                }
//            }
//        }

        activity.let {
            val radioGroup = binding.radioGroup
            val checkedRadioButtonId = radioGroup.checkedRadioButtonId // Returns View.NO_ID if nothing is checked.
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                // Responds to child RadioButton checked/unchecked
                when {
                    binding.radioButton1.id == checkedId -> {
                        it?.setTheme(R.style.Theme_MyTheme)
                        it?.recreate()
                        println(1)
                    }
                    binding.radioButton2.id == checkedId -> {
                        it?.setTheme(R.style.Theme_MyTheme_OtherSide)
                        it?.recreate()
                        println(22)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}