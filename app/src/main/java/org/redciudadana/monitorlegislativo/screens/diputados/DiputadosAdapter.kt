package org.redciudadana.monitorlegislativo.screens.diputados

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_diputados_item.view.*
import org.redciudadana.monitorlegislativo.R
import org.redciudadana.monitorlegislativo.data.models.Profile

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val candidateImage: ImageView
        get() = view.diputado_image
    val candidateText: TextView
        get() = view.diputado_name
    val candidatePartido: TextView
        get() = view.diputado_partido

    var onClickListener: View.OnClickListener? = null
        set(value) {
            field = value
            view.setOnClickListener(field)
        }

}

class DiputadosAdapter(
    private val context: Context,
    private val candidateView: DiputadosContract.View,
    diputados: List<Profile>?) : RecyclerView.Adapter<ViewHolder>() {

    var diputados: List<Profile>? = diputados
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_diputados_item, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return diputados?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val candidate = diputados?.get(position)
        if (candidate != null) {
            Glide.with(context).load(candidate.fotoUrl).into(holder.candidateImage)
            holder.candidateText.text = candidate.nombre
            holder.candidatePartido.text = candidate.partidoactual
            holder.onClickListener = View.OnClickListener {
                candidateView.onCandidateSelected(candidate)
            }
        }
    }


}