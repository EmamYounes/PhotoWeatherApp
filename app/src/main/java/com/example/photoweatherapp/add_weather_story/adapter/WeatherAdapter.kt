package com.example.photoweatherapp.add_weather_story.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.photoweatherapp.R
import com.example.photoweatherapp.add_weather_story.data_classes.WeatherModel
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherAdapter(
    weatherModelList: MutableList<WeatherModel>,
    val context: Context
) :
    RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {
    private var myList: MutableList<WeatherModel?> = mutableListOf()
    var onCLick: OnCLick? = null

    inner class WeatherHolder(private val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        fun bind(itemModel: WeatherModel, position: Int) {
            itemView.parent_view.tag = position
            itemView.parent_view.setOnClickListener(this)

            itemView.add_place_name.text = itemModel.addPlaceName
            itemView.temperature.text = itemModel.temperature
            itemView.weather_condition.text = itemModel.weatherCondition
            setImageByteArray(view.image_iv, itemModel.imageByteArray)
        }

        override fun onClick(p0: View?) {
            val model = myList[p0!!.tag as Int]
            onCLick!!.onItemClickListener(model)
        }

        private fun setImageByteArray(imageView: ImageView, byteArray: ByteArray) {
            val decodedByte =
                BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            imageView.setImageBitmap(
                decodedByte
            )
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherAdapter.WeatherHolder {
        return WeatherHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.weather_item,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = myList.size

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherHolder, position: Int) {
        val model: WeatherModel = this.myList[position]!!
        holder.bind(model, position)
    }

    fun deleteItem(
        position: Int
    ) {
        myList.removeAt(position)
        notifyDataSetChanged()
    }

    fun addList(lst: MutableList<WeatherModel?>) {
        myList = ((lst as MutableList<WeatherModel>).toMutableList())
        notifyDataSetChanged()
    }

    fun removeAll() {
        myList.clear()
        notifyDataSetChanged()
    }

    fun getCurrentList(): MutableList<WeatherModel?> {
        if (myList.isNullOrEmpty())
            return mutableListOf()
        return myList
    }

    fun addNewItem(obj: WeatherModel?) {
        obj.let {
            it?.let { it1 -> myList.add(it1) }
        }
        notifyDataSetChanged()
    }

    init {
        myList = weatherModelList.toMutableList()
    }

    fun updateItem(pos: Int, obj: WeatherModel?) {
        obj?.let { myList.removeAt(pos) }
        obj?.let { myList.add(pos, it) }
        notifyDataSetChanged()
    }


}
