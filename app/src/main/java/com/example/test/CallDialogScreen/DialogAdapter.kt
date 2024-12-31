package com.example.test.CallDialogScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.Model.Dialog
import com.example.test.databinding.ItemDialogBinding

class DialogAdapter(
    val dialogList: MutableList<Dialog>,
    val onClick: (Dialog) -> Unit
) :
    RecyclerView.Adapter<DialogAdapter.DialogViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val binding = ItemDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DialogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dialogList.size
    }

    override fun onBindViewHolder(holder: DialogViewHolder, position: Int) {
        holder.onBind()
    }

    inner class DialogViewHolder(private val binding: ItemDialogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val dialog = dialogList[adapterPosition]
            binding.btnDialogNumber.apply {
                text = dialog.id
                setOnClickListener {
                    onClick(dialog)
                }
            }


        }

    }

}