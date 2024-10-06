package com.example.botones

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.view.animation.LinearInterpolator
class MainActivity : AppCompatActivity() {

    private lateinit var michinauta: ImageView
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0
    private var velX: Float = 2.0f // Velocidad en el eje X
    private var velY: Float = 2.0f // Velocidad en el eje Y

    private lateinit var somido: View
    private lateinit var musica: View
    private lateinit var idioma: View
    private lateinit var info: View

    private var rotate = false

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //configuracion para pasar de una ventana a otra
        val btn2  = findViewById<FloatingActionButton>(R.id.btn2)

        btn2.setOnClickListener{
            val intent = Intent(this, MapaActivity::class.java)
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

        michinauta = findViewById(R.id.michinauta)

            michinauta.post {
                screenWidth = michinauta.rootView.width
                screenHeight = michinauta.rootView.height

                // Iniciar animación flotante
                startFloatingAnimation()
            }
    }

    private fun startFloatingAnimation() {
        // Animación en X
        val animatorX = ValueAnimator.ofFloat(0f, screenWidth.toFloat())
        animatorX.duration = 10000 // 10 segundos
        animatorX.interpolator = LinearInterpolator()
        animatorX.repeatCount = ValueAnimator.INFINITE
        animatorX.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            michinauta.translationX = value
        }
        animatorX.start()

        // Animación en Y
        val animatorY = ValueAnimator.ofFloat(0f, screenHeight.toFloat())
        animatorY.duration = 10000
        animatorY.interpolator = LinearInterpolator()
        animatorY.repeatCount = ValueAnimator.INFINITE
        animatorY.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            michinauta.translationY = value
        }
        animatorY.start()
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