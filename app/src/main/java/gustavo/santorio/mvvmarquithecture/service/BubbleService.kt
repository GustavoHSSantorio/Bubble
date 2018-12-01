package gustavo.santorio.mvvmarquithecture.service

import android.content.Intent
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import gustavo.santorio.mvvmarquithecture.R
import gustavo.santorio.mvvmarquithecture.repository.BaseRepository
import android.graphics.PixelFormat
import android.view.WindowManager
import android.R.attr.y
import android.R.attr.x
import android.view.Gravity
import android.R.attr.gravity
import android.content.Context
import android.os.Binder
import android.R.attr.y
import android.R.attr.x
import android.arch.lifecycle.MutableLiveData
import android.net.Uri
import android.os.Handler
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import gustavo.santorio.mvvmarquithecture.model.ChatVO
import gustavo.santorio.mvvmarquithecture.repository.BubbleRepository
import gustavo.santorio.mvvmarquithecture.ui.adapter.MessageAdapter
import gustavo.santorio.mvvmarquithecture.ui.adapter.ServiceAdapter


class BubbleService : BaseService(BubbleRepository::class) {

    inner class BubbleServiceBinder(val service: BubbleService) : Binder()

    override fun onBind(intent: Intent?): IBinder {
        return _binder!!
    }

    var floatingView : View? = null
    var rv_message : RecyclerView? = null
    var windowManager: WindowManager? = null
    var _binder: BubbleServiceBinder? = null
    var adapter : MessageAdapter? = null

    val questions = arrayListOf("Primeiro, qual o seu nome?","Qual meio de transporte você mais utiliza?\n"+ "1.Bicicletas/Patinetes\n" +"2.Carro Próprio/Privado\n" + "3.Transporte Coletivo)", "Qual seu foco ao se locomover?\n"+"1.Se exercitar/melhorar a saúde\n"+ "2.Ter conforto e velocidade\n" + "3.Economizar dinheiro")
    var questionsCount = 0

    var sustainability = 0
    var personal = 0
    var collective = 0

    var name : String? = null

    override fun onCreate() {
        super.onCreate()
        _binder = BubbleServiceBinder(this)

        preferenceHelper.putFlowInSingUp(true)
        preferenceHelper.putProfileType(0)

        floatingView = LayoutInflater.from(this).inflate(R.layout.view_bubble, null);
        val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                0,
                PixelFormat.TRANSLUCENT)

        params.gravity = Gravity.TOP or Gravity.LEFT
        params.x = 0
        params.y = 100

        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager?.addView(floatingView, params)

        adapter = MessageAdapter(this)

        rv_message = floatingView?.findViewById<RecyclerView>(R.id.rv_message)

        rv_message?.layoutManager = object : LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false){
            override fun canScrollHorizontally(): Boolean  = false
        }
        rv_message?.adapter = adapter

        val expandedView = floatingView?.findViewById<CardView>(R.id.expanded_view)
        val close_btn = floatingView?.findViewById<ImageView>(R.id.close_btn)
        val et_message = floatingView?.findViewById<EditText>(R.id.et_message)
        val iv_send = floatingView?.findViewById<ImageView>(R.id.iv_send)


        adapter?.serviceSelectedListener = object : ServiceAdapter.ServiceSelectedListener {
            override fun onServiceSelected(service: Int) {
                when(service){
                    1 -> {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=br.com.yellow")))
                    }
                    2 -> {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.grin")))
                    }
                    3 -> {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.ubercab")))
                    }
                    4 -> {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.taxis99")))
                    }
                    5 -> {
                        startActivity(packageManager.getLaunchIntentForPackage("me.move"))
                    }
                    6 -> {}
                    7 -> {}
                    8 -> {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=br.nanoit.viewbus")))
                    }
                    9 -> {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.tranzmate")))
                    }
                    10 -> {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.scipopulis.mockupteletransporte.teletransporte")))
                    }
                }
                close_btn?.setVisibility(View.VISIBLE)
                expandedView?.setVisibility(View.GONE)
            }
        }

        iv_send?.setOnClickListener {
            if(!et_message?.text.toString().isEmpty() && !et_message?.text.toString().isBlank()) {
                sendMessage(et_message?.text.toString())
                et_message?.setText("")
            }
        }

        close_btn?.setOnClickListener {
            stopSelf()
        }

        floatingView?.findViewById<ImageView>(R.id.tv_close)?.setOnClickListener {
            close_btn?.setVisibility(View.VISIBLE)
            expandedView?.setVisibility(View.GONE)
        }

        floatingView?.findViewById<RelativeLayout>(R.id.root_container)?.setOnTouchListener(object : View.OnTouchListener {
            private var initialX: Int = 0
            private var initialY: Int = 0
            private var initialTouchX: Float = 0.toFloat()
            private var initialTouchY: Float = 0.toFloat()


            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {

                        initialX = params.x
                        initialY = params.y

                        initialTouchX = event.rawX
                        initialTouchY = event.rawY
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        val Xdiff = (event.rawX - initialTouchX).toInt()
                        val Ydiff = (event.rawY - initialTouchY).toInt()

                        if (Xdiff < 10 && Ydiff < 10) {
                            if (isViewCollapsed()) {
                                close_btn?.setVisibility(View.GONE)
                                expandedView?.setVisibility(View.VISIBLE)
                            }
                        }
                        return true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        params.x = initialX + (event.rawX - initialTouchX).toInt()
                        params.y = initialY + (event.rawY - initialTouchY).toInt()
                        windowManager?.updateViewLayout(floatingView, params)
                        return true
                    }
                }
                return false
            }
        })

        adapter?.addMessage("Olá, eu sou o Bubbl. Se você quer\n" +
                "ter uma melhor mobilidade e \n" +
                "facilidade no transito, eu te ajudo!", 2)
        nextQuestion()
    }

    private fun sendMessage(message : String){
        adapter?.addMessage(message, 0)
        if(message.contains("encerrar") ){
            adapter?.addMessage("Te vejo por ai " + name , 2)
            rv_message?.scrollToPosition(adapter?.getItemCount()!! - 1)
            Handler().postDelayed({
                preferenceHelper.putFlowInSingUp(true)
                preferenceHelper.putProfileType(0)
                stopSelf()
            }, 2000)
        }
        else if(name == null) {
            name = message
            Handler().postDelayed({
                adapter?.addMessage("Tudo em cima, "+ name +"?\n" +
                        "Bom.. preciso saber um pouco\n" +
                        "sobre o seu perfil. Vou fazer \n" +
                        "algumas perguntas, tranquilo?", 2)
                rv_message?.scrollToPosition(adapter?.getItemCount()!! - 1)
                nextQuestion()
            },2000)
        }else if(preferenceHelper.isFlowInSingUp()) {
            saveAnswer(message)
            nextQuestion()
        }else if(message.contains("trabalho") || message.contains("casa") ) {
            adapter?.addMessage("Certo, estou pesquisando a melhor opção...", 2)
            Handler().postDelayed({
                adapter?.addMessage("Pronto! Essas são minhas sugestões baseadas no seu uso.", 2)
                rv_message?.scrollToPosition(adapter?.getItemCount()!! - 1)
                Handler().postDelayed({
                    adapter?.addAll((repository as BubbleRepository).getMessageEstimate(preferenceHelper.getProfileType(), 2))
                    rv_message?.scrollToPosition(adapter?.getItemCount()!! - 1)
                }, 2000)
            }, 1000)
        }else {
            (repository as BubbleRepository).getMessage(message, liveData)
        }
        rv_message?.scrollToPosition(adapter?.getItemCount()!! - 1)
    }

    val liveData by lazy {
        val liveData = MutableLiveData<ChatVO>()
        liveData.observeForever {
            adapter?.addMessage(it?.message!!, 2)
        }
        liveData
    }

    fun saveAnswer(message : String){
        when(message.toInt()){
            1 -> {
                sustainability++
            }
            2 -> {
                personal++
            }
            3 -> {
                collective++
            }
        }
    }

    fun nextQuestion(){
            Handler().postDelayed({
                if(questionsCount < questions.size) {
                    adapter?.addMessage(questions[questionsCount], 2)
                    questionsCount++
                }else if(questionsCount == questions.size){
                    if(sustainability > personal && sustainability > collective) {
                        preferenceHelper.putProfileType(1)
                        if(personal > collective)
                            preferenceHelper.putSuggestedProfileType(2)
                        else
                            preferenceHelper.putSuggestedProfileType(3)
                        questionsCount = 0
                    }
                    else if(personal > sustainability && personal > collective) {
                        preferenceHelper.putProfileType(2)
                        if(sustainability > collective)
                            preferenceHelper.putSuggestedProfileType(1)
                        else
                            preferenceHelper.putSuggestedProfileType(3)
                    }
                    else if(collective> sustainability && collective > personal) {
                        preferenceHelper.putProfileType(3)
                        if(sustainability > personal)
                            preferenceHelper.putSuggestedProfileType(1)
                        else
                            preferenceHelper.putSuggestedProfileType(2)
                    }
                    adapter?.addMessage("Onde você deseja ir "+ name +"?", 2)
                    preferenceHelper.putFlowInSingUp(false)
                }
                rv_message?.scrollToPosition(adapter?.getItemCount()!! - 1)
            },2000)
    }


    private fun isViewCollapsed(): Boolean {
        return floatingView == null || floatingView?.findViewById<RelativeLayout>(R.id.collapse_view)?.getVisibility() === View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        if (floatingView != null) windowManager?.removeView(floatingView)
    }
}