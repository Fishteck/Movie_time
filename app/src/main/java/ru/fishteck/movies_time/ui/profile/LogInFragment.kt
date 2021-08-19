package ru.fishteck.movies_time.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.textfield.TextInputEditText
import ru.fishteck.appComponent
import ru.fishteck.movies_time.R
import ru.fishteck.movies_time.data.models.ProfileModel
import ru.fishteck.movies_time.utils.ViewModelFactory
import ru.fishteck.movies_time.utils.showToast
import javax.inject.Inject
import kotlin.random.Random

class LogInFragment : Fragment(R.layout.fragment_log_in) {

    @Inject
    lateinit var factory: ViewModelFactory
    private val profileViewModel : ProfileViewModel
            by navGraphViewModels<ProfileViewModel>(R.id.logInFragment) { factory }
    private lateinit var enterBtn : Button
    private lateinit var name : TextInputEditText
    private lateinit var password : TextInputEditText
    private lateinit var email : TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterBtn = view.findViewById(R.id.log_in_settings_btn_enter)
        name = view.findViewById(R.id.log_in_edit_user_name)
        password = view.findViewById(R.id.log_in_edit_user_password)
        email = view.findViewById(R.id.log_in_edit_user_email)

        enterBtn.setOnClickListener {
            val mName = name.text.toString()
            val mPassword = password.text.toString()
            val mEmail = email.text.toString()

            if (mName.isEmpty() && mPassword.isEmpty() && mEmail.isEmpty()) {
                showToast("Пустые поля")
            } else {
                val profile = ProfileModel(
                    userName = mName,
                    userEmail = mEmail,
                    mobileNumber = getString(R.string.profile_user_phone_plug),
                    id = Random.nextInt(0, 1000),
                    interest = listOf(
                            getString(R.string.text_genre_fighters),
                            getString(R.string.text_genre_dramas),
                            getString(R.string.text_genre_comedy)
                            )
                )
                profileViewModel.addProfile(profile = profile)

                findNavController().navigate(
                    R.id.action_logInFragment_to_menu_profile,
                    bundleOf(ProfileFragment.PREF_KEY to profile.id)
                )
            }
        }
    }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }
}