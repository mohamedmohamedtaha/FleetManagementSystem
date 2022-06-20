package com.example.fleetmanagement.ui.home.fragment

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.model.Language
import com.example.fleetmanagement.ui.SplashActivity
import com.example.fleetmanagement.ui.home.dialog.LanguageFragment
import com.example.fleetmanagement.utils.Constants.ARABIC
import com.example.fleetmanagement.utils.Constants.ENGLISH
import com.example.fleetmanagement.utils.Constants.LANGUAGE_ARABIC
import com.example.fleetmanagement.utils.Constants.LANGUAGE_ENGLISH
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.FragmentSettingsBinding
import com.example.fleetmanagement.utils.showSnackBar
import java.util.*

class SettingsFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        binding.textViewChangePassword.setOnClickListener(this)
        binding.textViewChangeLanguage.setOnClickListener(this)
        hideToolbar()
        return binding.root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.textViewChangePassword -> {
            }
            R.id.textViewChangeLanguage -> {
                val language = ArrayList<Language>()
                language.add(Language(1, getString(R.string.language_arabic)))
                language.add(Language(2, getString(R.string.language_english)))
                val languageFragment = LanguageFragment({
                    if (it.id == 1) {
                        with(this.dataStoreViewModel) { putLanguage(LANGUAGE_ARABIC) }
                        updateLanguage(requireContext(), ARABIC)
                        requireActivity().finish()
                        startActivity(Intent(requireActivity(), SplashActivity::class.java))
                    } else {
                        dataStoreViewModel.putLanguage(LANGUAGE_ENGLISH)
                        updateLanguage(requireContext(), ENGLISH)
                        requireActivity().finish()
                        startActivity(Intent(requireActivity(), SplashActivity::class.java))
                    }
                }, language)
                languageFragment.show(childFragmentManager, "")
            }
        }
    }

    private fun updateLanguage(context: Context, language: String): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration: Configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)
        return context.createConfigurationContext(configuration)
    }
}