package com.example.areeba

import android.graphics.Matrix
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.Collections.max
import java.util.Collections.min
import kotlin.math.max
import kotlin.math.min

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [album.newInstance] factory method to
 * create an instance of this fragment.
 */


class album : Fragment() {
    private val Images = intArrayOf(R.drawable.safe, R.drawable.auth, R.drawable.rocket)
    private var index = 0
    private lateinit var imgSwitcher: ImageSwitcher

    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_album, container, false)
        imgSwitcher = view.findViewById(R.id.imgSw)
        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())

        imgSwitcher.setFactory(ViewSwitcher.ViewFactory {
            val imgView = ImageView(requireContext())
            imgView.scaleType = ImageView.ScaleType.MATRIX
            imgView.setPadding(8, 8, 8, 8)
            imgView
        })

        // Set the initial image
        imgSwitcher.setImageResource(Images[index])
        applyScaleFactor()

        val imgIn = AnimationUtils.loadAnimation(requireContext(), android.R.anim.slide_in_left)
        imgSwitcher.inAnimation = imgIn

        val imgOut = AnimationUtils.loadAnimation(requireContext(), android.R.anim.slide_out_right)
        imgSwitcher.outAnimation = imgOut

        // Previous button functionality
        val prev = view.findViewById<Button>(R.id.prev)
        prev.setOnClickListener {
            index = if (index - 1 >= 0) index - 1 else 2
            imgSwitcher.setImageResource(Images[index])
            applyScaleFactor()
        }

        // Next button functionality
        val next = view.findViewById<Button>(R.id.next)
        next.setOnClickListener {
            index = if (index + 1 < Images.size) index + 1 else 0
            imgSwitcher.setImageResource(Images[index])
            applyScaleFactor()
        }

        // Touch listener to handle pinch-zoom gesture
        imgSwitcher.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }

        return view
    }

    private fun applyScaleFactor() {
        val imgView = imgSwitcher.currentView as? ImageView
        imgView?.apply {
            val matrix = Matrix()
            matrix.postScale(scaleFactor, scaleFactor)
            imageMatrix = matrix
        }
    }

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = max(0.1f, min(scaleFactor, 10.0f))
            applyScaleFactor()
            return true
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment album.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            album().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
