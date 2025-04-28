package com.example.application.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.application.R
import com.example.application.databinding.FragmentNotificationsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private lateinit var logoutBtn: Button
    private lateinit var userName: TextView
    private lateinit var userTest: TextView
    private lateinit var userAnswer: TextView
    private lateinit var userPercent: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        userName = binding.userName
        notificationsViewModel.email.observe(viewLifecycleOwner) {
            userName.text = it
        }

        userTest = binding.tests
        notificationsViewModel.tests.observe(viewLifecycleOwner) {
            userTest.text = it
        }

        userAnswer = binding.answers
        notificationsViewModel.answers.observe(viewLifecycleOwner) {
            userAnswer.text = it
        }

        userPercent = binding.percent
        notificationsViewModel.percent.observe(viewLifecycleOwner) {
            userPercent.text = it
        }

        if(FirebaseAuth.getInstance().currentUser == null) {
            findNavController().navigate(R.id.action_navigation_notifications_to_loginFragment)
        } else {
            notificationsViewModel.setUserEmail()
            notificationsViewModel.setUserData()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        logoutBtn = binding.logoutBtn
        logoutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_navigation_notifications_to_navigation_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}