package ru.fishteck.movies_time.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.textfield.TextInputEditText
import ru.fishteck.appComponent
import ru.fishteck.encPrefs
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.data.models.ProfileModel
import ru.fishteck.movies_time.utils.states.ProfileState
import ru.fishteck.movies_time.utils.ViewModelFactory
import ru.fishteck.movies_time.utils.showToast
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    @Inject
    lateinit var factory: ViewModelFactory
    private val profileViewModel: ProfileViewModel
            by navGraphViewModels<ProfileViewModel>(R.id.menu_profile) { factory }
    private var isAuth = false
    private lateinit var loggedSection: ConstraintLayout
    private lateinit var notLoggedSection: ConstraintLayout
    private lateinit var exitBtn: Button
    private lateinit var fieldName: TextView
    private lateinit var fieldEmail: TextView
    private lateinit var editFieldName: TextInputEditText
    private lateinit var editFieldPassword: TextInputEditText
    private lateinit var editFieldEmail: TextInputEditText
    private lateinit var editFieldPhoneNumber: TextInputEditText
    private var profileId: Int? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFields(view)

        if (isAuth) {
            loggedSection.visibility = View.VISIBLE
            notLoggedSection.visibility = View.GONE
            exitBtn.text = getString(R.string.profile_setting_exit)
            profileId?.let { profileViewModel.loadProfile(it) }
        } else {
            loggedSection.visibility = View.GONE
            notLoggedSection.visibility = View.VISIBLE
            exitBtn.text = getString(R.string.profile_setting_enter)
        }

        exitBtn.setOnClickListener {
            if (exitBtn.text.equals(getString(R.string.profile_setting_enter))) {
                findNavController().navigate(
                        R.id.action_menu_profile_to_logInFragment
                )
            } else {
                isAuth = false
                profileId?.let { it1 -> profileViewModel.deleteProfileById(it1) }
                findNavController().navigate(R.id.action_menu_profile_self)
                it.context.applicationContext.encPrefs.edit().clear().apply()
            }
        }

        initObserver()
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)

        val id = arguments?.get(PREF_KEY)

        if (id != null) {
            context.encPrefs.edit().putInt(PREF_KEY, id as Int).apply()
            isAuth = true
            profileId = id
        } else {
            if (context.encPrefs.contains(PREF_KEY)) {
                isAuth = true
                profileId = context.encPrefs.getInt(PREF_KEY, 0)
            }
        }
    }

    private fun initObserver() {
        profileViewModel.profileState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ProfileState.Success -> {
                    setInfo(state.data)
                }
                is ProfileState.Error -> {
                    showToast(state.message)
                }
            }
        })
    }

    private fun setInfo(data: ProfileModel) {
        fieldName.text = data.userName
        fieldEmail.text = data.userEmail
    }

    private fun setFields(view: View) {
        loggedSection = view.findViewById(R.id.profile_personal_data_section)
        notLoggedSection = view.findViewById(R.id.profile_logging_section)
        exitBtn = view.findViewById(R.id.profile_settings_btn_exit)
        fieldName = view.findViewById(R.id.profile_user_name)
        fieldEmail = view.findViewById(R.id.profile_user_email)
        editFieldName = view.findViewById(R.id.profile_edit_user_name)
        editFieldPassword = view.findViewById(R.id.profile_edit_user_password)
        editFieldEmail = view.findViewById(R.id.profile_edit_user_email)
        editFieldPhoneNumber = view.findViewById(R.id.profile_edit_user_phone)
    }

    companion object {
        const val PREF_KEY = "profile_id"
    }
}