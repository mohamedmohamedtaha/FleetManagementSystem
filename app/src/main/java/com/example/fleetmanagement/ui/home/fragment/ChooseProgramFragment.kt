package com.example.fleetmanagement.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fleetmanagement.base.BaseFragment
import com.example.fleetmanagement.base.OnRecycleItemClick
import com.example.fleetmanagement.data.api.NetworkResult
import com.example.fleetmanagement.data.model.Program
import com.example.fleetmanagement.data.model.ProgramDuration
import com.example.fleetmanagement.ui.home.adapter.PricingAdapter
import com.example.fleetmanagement.ui.home.adapter.TrainingTypeAdapter
import com.example.fleetmanagement.ui.home.viewmodel.LoginViewModel
import com.example.fleetmanagement.R
import com.example.fleetmanagement.databinding.FragmentChooseProgramBinding
import com.example.fleetmanagement.utils.showSnackBar

class ChooseProgramFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentChooseProgramBinding
    private lateinit var trainingTypeAdapter: TrainingTypeAdapter
    private lateinit var programTypeAdapter: TrainingTypeAdapter
    private var trainingTypeIds = arrayListOf<String>()
    private var programTypeIds = arrayListOf<String>()
    private var programDuration = ""
    private var language = ""
    private val activityVieWModel: LoginViewModel by activityViewModels()
    private lateinit var adapterPricingAdapter: PricingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        language = dataStoreViewModel.getLanguage().toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseProgramBinding.inflate(layoutInflater)
        listenerButtonGroup()
        recycleViewFill()
        binding.buttonNext.setOnClickListener(this)
        hideToolbar()
        binding.activityViewModel = activityVieWModel
        return binding.root
    }

    private fun listenerButtonGroup() {
//        binding.radioGroupProgramDuration.setOnCheckedChangeListener { radioGroup, _ ->
//            when (radioGroup.checkedRadioButtonId) {
//                R.id.radioButtonThreeMonth -> {
//
//                }
//                R.id.radioButtonSixMonth -> {
//
//                }
//                R.id.radioButtonNineMonth -> {
//
//                }
//                R.id.radioButtonOneYear -> {
//
//                }
//            }
//        }
    }

    private fun recycleViewFill() {
        val trainingTypeList = ArrayList<Program>()
        trainingTypeList.add(Program("1", getString(R.string.bulk), false))
        trainingTypeList.add(Program("2", getString(R.string.gain_weight), false))
        trainingTypeList.add(Program("3", getString(R.string.lose_weight), false))
        trainingTypeList.add(Program("4", getString(R.string.cutting), false))
        val btnAdd = ""
        trainingTypeAdapter =
            TrainingTypeAdapter(trainingTypeList, object : OnRecycleItemClick<Program> {
                override fun onClick(t: Program, view: View) {
                    //trainingTypeIds = arrayListOf()
                    for (i in 0 until trainingTypeAdapter.getSelected().size) {
                        for (j in trainingTypeList.indices) {
                            if (trainingTypeList[j].name == trainingTypeAdapter.getSelected()[i] && trainingTypeList[j].isCheck) {
                                if (trainingTypeIds.contains("1") || trainingTypeIds.contains("2")) {
                                    Log.d("LoginFragment", "if ${t.id} ")
                                    if (t.id == "3" || t.id == "4") {
                                        t.isCheck = false
                                        binding.root.showSnackBar("You can't add That item")
                                        return
                                    } else {
                                        trainingTypeIds.add(trainingTypeList[j].id)
                                        Log.d("LoginFragment", "else one ${t.id}")
                                    }
                                } else
                                    if (trainingTypeIds.contains("3") || trainingTypeIds.contains("4")) {
                                        Log.d("LoginFragment", "else if ${t.id}")
                                        if (t.id == "1" || t.id == "2") {
                                            t.isCheck = false
                                            binding.root.showSnackBar("You can't add That item")
                                            return
                                        } else {
                                            trainingTypeIds.add(trainingTypeList[j].id)
                                            Log.d("LoginFragment", "else two ${t.id}")
                                        }
                                    } else {
                                        trainingTypeIds.add(trainingTypeList[j].id)
                                        Log.d("LoginFragment", "else last ${t.id}")
                                    }
                            } else {
                                if (trainingTypeIds.contains(trainingTypeList[j].id))
                                    trainingTypeIds.remove(trainingTypeList[j].id)
                            }
                        }
                    }
                    Log.d("LoginFragment", "  $trainingTypeIds")
                }
            })
        binding.recyclerViewTrainingType.adapter = trainingTypeAdapter

        val programTypeList = ArrayList<Program>()
        programTypeList.add(Program("1", getString(R.string.workout), false))
        programTypeList.add(Program("2", getString(R.string.nutrition), false))
        programTypeList.add(Program("3", getString(R.string.supplement), false))
        programTypeAdapter =
            TrainingTypeAdapter(programTypeList, object : OnRecycleItemClick<Program> {
                override fun onClick(t: Program, view: View) {
                    programTypeIds = arrayListOf()
                    for (i in 0 until programTypeAdapter.getSelected().size) {
                        for (j in programTypeList.indices) {
                            if (programTypeList[j].name == programTypeAdapter.getSelected()[i]) {
                                programTypeIds.add(programTypeList[j].id)
                                Toast.makeText(
                                    requireContext(),
                                    programTypeIds.size.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }

            })
        binding.recyclerViewProgramType.adapter = programTypeAdapter
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.buttonNext -> {
                activityVieWModel.parameters.trainingTypes = trainingTypeIds
                activityVieWModel.parameters.programTypes = programTypeIds
                activityVieWModel.parameters.programDuration = programDuration
            //    findNavController().navigate(R.id.action_chooseProgramFragment_to_enterInformationFragment)
            }
        }
    }
}















