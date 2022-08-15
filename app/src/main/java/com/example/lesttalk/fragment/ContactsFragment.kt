package com.example.lesttalk.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lesttalk.R
import com.example.lesttalk.adapter.LetsTalkAdapter
import com.example.lesttalk.databinding.FragmentContactsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactsFragment : Fragment() {

    private val binding by lazy { FragmentContactsBinding.inflate(layoutInflater) }
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initializeView()
        getAllContacts()
        return binding.root
    }

    private fun initializeView() {
        binding.toolbarContacts.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun getAllContacts() {
        val exibirnomes = mutableListOf<String>()
        val getids = mutableListOf<String>()
        db.collection("users")
            .addSnapshotListener { document, error ->
                if (document != null) {
                    for (documents in document) {
                        exibirnomes += listOf(documents.data["Name"].toString())
                        getids += listOf(documents.id)
                    }
                    val adapter by lazy { LetsTalkAdapter(exibirnomes) }
                    binding.recycleview.adapter = adapter
                    adapter.setOnItemClickListener(object : LetsTalkAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val bundle = Bundle()
                            bundle.putString("id", getids[position])
                            findNavController().navigate(R.id.action_contactsFragment_to_conversaFragment, bundle)
                        }

                    })
                } else {
                    Log.i("Erro contato", error?.cause.toString())
                }
            }
    }
}