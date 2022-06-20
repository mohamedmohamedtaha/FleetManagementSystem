package com.example.fleetmanagement.ui.home.fragment

import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.activityViewModels
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.model.DailyActivities
import com.example.fleetmanagement.data.model.Disease
import com.example.fleetmanagement.ui.home.dialog.DailyActivitiesFragment
import com.example.fleetmanagement.ui.home.dialog.DiseaseFragment
import com.example.fleetmanagement.ui.home.viewmodel.LoginViewModel
import com.example.fleetmanagement.utils.Constants
import com.example.fleetmanagement.utils.Formatter
import com.example.fleetmanagement.utils.Formatter.format
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.FragmentEnterInformationBinding
import com.example.fleetmanagement.utils.showSnackBar
import com.mukesh.countrypicker.Country
import com.mukesh.countrypicker.CountryPicker
import com.mukesh.countrypicker.listeners.OnCountryPickerListener
import java.util.*

class EnterInformationFragment : BaseFragment(), View.OnClickListener, OnCountryPickerListener {
    private lateinit var binding: FragmentEnterInformationBinding
    private val activityViewModel: LoginViewModel by activityViewModels()
    private var disease = ArrayList<Disease>()
    private var dailyActivity = ArrayList<DailyActivities>()
    private var language = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        language = dataStoreViewModel.getLanguage().toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEnterInformationBinding.inflate(layoutInflater)
        binding.buttonPay.setOnClickListener(this)
        binding.textInputLayoutDateOfBirth.setOnClickListener(this)
        binding.editTextNationality.setOnClickListener(this)
        binding.textViewDisease.setOnClickListener(this)
        binding.textViewDailyActivity.setOnClickListener(this)
        return binding.root
    }

    override fun onSelectCountry(country: Country?) {
        if (country?.flag != null) {
            val drawable = resources.getDrawable(country.flag)
            val bitmap = drawable.toBitmap()
            val drawabler =
                BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, 20, 20, true))
            binding.editTextNationality.setCompoundDrawablesWithIntrinsicBounds(
                drawabler,
                null,
                null,
                null
            )
        }
        binding.editTextNationality.setText(country?.name.toString())
    }

    private fun chooseYourCountry() {
        //For choose any country
        val builder: CountryPicker.Builder = CountryPicker.Builder().with(requireContext())
            .listener(this).sortBy(CountryPicker.SORT_BY_NAME)
        // .theme(CountryPicker.THEME_NEW)
        //.style(R.style.CountryPickerStyle)
        val picker = builder.build()
        picker.showBottomSheet(requireActivity() as AppCompatActivity)
    }

    private fun selectBirthOfDate() {
        val calendar = Calendar.getInstance()
        val dateClickListener =
            DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
                val myCalender = Calendar.getInstance()
                myCalender.set(Calendar.YEAR, year)
                myCalender.set(Calendar.MONTH, monthOfYear)
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                binding.textInputLayoutDateOfBirth.setText(
                    format(
                        myCalender.time.toString(),
                        Formatter.EEE_MMM_DD_HH_MM_SS_ZZZ_YYYY,
                        Formatter.DD_MM_YYYY
                    )
                )
            }
        val datePicker = DatePickerDialog(
            requireContext(), dateClickListener, calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.setTitle(getString(R.string.choose_your_birth_date))
        datePicker.show()


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonPay -> {

                activityViewModel.parameters.birthDate =
                    binding.textInputLayoutDateOfBirth.text.toString()
                activityViewModel.parameters.weight =
                    binding.textInputLayoutWeight.editText?.text.toString()
                activityViewModel.parameters.height =
                    binding.textInputLayoutHeight.editText?.text.toString()
                activityViewModel.parameters.bodyFat =
                    binding.textInputLayoutBodyFat.editText?.text.toString()
                activityViewModel.parameters.bodyMuscle =
                    binding.textInputLayoutBodyMuscle.editText?.text.toString()
                activityViewModel.parameters.bodyWater =
                    binding.textInputLayoutBodyWater.editText?.text.toString()
                activityViewModel.parameters.bmi =
                    binding.textInputLayoutBodyBMI.editText?.text.toString()
                activityViewModel.parameters.dailyActivity =
                    binding.textViewDailyActivity.text.toString()
                activityViewModel.parameters.disease = binding.textViewDailyActivity.text.toString()
                activityViewModel.parameters.medicalHistory =
                    binding.textInputLayoutMedicalHistory.editText?.text.toString()
            }
            R.id.textInputLayoutDateOfBirth -> {
                selectBirthOfDate()
            }
            R.id.editTextNationality -> {
                chooseYourCountry()
            }
            R.id.textViewDisease -> {
                val diseaseFragment = DiseaseFragment({
                    if (language == Constants.LANGUAGE_ARABIC)
                        binding.textViewDisease.text = it.name_ar
                    else
                        binding.textViewDisease.text = it.name_en
                }, disease, language)
                diseaseFragment.show(childFragmentManager, "")
            }
            R.id.textViewDailyActivity -> {
//                dailyActivity.add(Disease("1", getString(R.string.normal)))
//                dailyActivity.add(Disease("2", getString(R.string.active)))
//                dailyActivity.add(Disease("3", getString(R.string.very_active)))
                val dailyActivityFragment = DailyActivitiesFragment({
                    if (language == Constants.LANGUAGE_ARABIC)
                        binding.textViewDailyActivity.text = it.name_ar
                    else
                        binding.textViewDailyActivity.text = it.name_en

                }, dailyActivity, language)
                dailyActivityFragment.show(childFragmentManager, "")
            }
        }
    }
}