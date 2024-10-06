package com.example.botones

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var somido: View
    private lateinit var musica: View
    private lateinit var idioma: View
    private lateinit var info: View

    private var rotate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //configuracion para pasar de una ventana a otra
        val button  = findViewById<FloatingActionButton>(R.id.btn2)

        button.setOnClickListener{
            val intent = Intent(this, mapa::class.java)
            startActivity(intent)
        }

        somido = findViewById<FloatingActionButton>(R.id.somido)
        musica = findViewById<FloatingActionButton>(R.id.Musica)
        idioma = findViewById<FloatingActionButton>(R.id.idioma)
        info = findViewById<FloatingActionButton>(R.id.info)
        initShowOut(somido)
        initShowOut(musica)
        initShowOut(idioma)
        initShowOut(info)

        val somidobtn = findViewById<FloatingActionButton>(R.id.sonidobtn)
        val musicabtn = findViewById<FloatingActionButton>(R.id.sonidobtn)
        val idiomabtn = findViewById<FloatingActionButton>(R.id.sonidobtn)
        val infobtn = findViewById<FloatingActionButton>(R.id.sonidobtn)
        val btn4 = findViewById<FloatingActionButton>(R.id.btn4)

        btn4.setOnClickListener {
            toggleFabMode(it)
        }

        somido.setOnClickListener {
            Toast.makeText(this, "Sonido", Toast.LENGTH_LONG).show()
        }
        musica.setOnClickListener {
            Toast.makeText(this, "Musica", Toast.LENGTH_LONG).show()
        }
        idioma.setOnClickListener {
            Toast.makeText(this, "idioma", Toast.LENGTH_LONG).show()
        }
        info.setOnClickListener {
            Toast.makeText(this, "informacion", Toast.LENGTH_LONG).show()
        }

    }

    private fun initShowOut(v: View){
        v.apply {
            visibility = View.GONE
            translationY = height.toFloat()
            alpha=0f
        }
    }

    private fun toggleFabMode(v: View) {
        rotate = rotateFab(v, !rotate)
        if (rotate){
            ShowIn(somido)
            ShowIn(musica)
            ShowIn(idioma)
            ShowIn(info)
        }else{
            ShowOut(somido)
            ShowOut(musica)
            ShowOut(idioma)
            ShowOut(info)
        }
    }


    private fun ShowOut (view: View){
        view.apply {
            visibility = View.VISIBLE
            alpha = 1f
            translationY = 0f
            animate()
                .setDuration(200)
                .translationY(height.toFloat())
                .setListener(object : AnimatorListenerAdapter () {
                    override fun onAnimationEnd(animation: Animator) {
                        visibility = View.GONE
                        super.onAnimationEnd(animation)
                    }
                })
                .alpha(0f)
                .start()

        }
    }

    private fun ShowIn (view: View){
        view.apply {
            visibility = View.VISIBLE
            alpha = 0f
            translationY = height.toFloat()
            animate()
                .setDuration(200)
                .translationY(0f)
                .setListener(object : AnimatorListenerAdapter () {})
                .alpha(1f)
                .start()

                }
        }


    private fun rotateFab(v: View, rotate: Boolean) : Boolean {
    v.animate()
        .setDuration(200)
        .setListener(object : AnimatorListenerAdapter() {})
        .rotation(if (rotate) 180f else 0f)
        return rotate
    }
}