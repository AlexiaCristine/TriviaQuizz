package com.example.triviaquizz.Adapters

import android.app.AlertDialog
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaquizz.*
import com.example.triviaquizz.Models.Question.AnswerQuestion
import kotlinx.android.synthetic.main.rv_answers.view.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AdapterQuestion(answers : List<AnswerQuestion>, view : View) :
    RecyclerView.Adapter<AdapterQuestion.QuestionHolder>() {
    val build: AlertDialog.Builder = AlertDialog.Builder(view.context)
        .setView(R.layout.activity_loading)
        .setCancelable(false)
    val load: AlertDialog = build.create()
    val questioncontroll = ControllQuestion()
    private val gameAdapter = ControllGame()
    private var listAnswers = listOf<AnswerQuestion>()

    init {
        listAnswers = answers
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.rv_answers
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        QuestionHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(viewType, parent, false)
        )

    override fun onBindViewHolder(holder: QuestionHolder, position: Int) {
        val answer = listAnswers[position]
        holder.fillView(answer)
    }

    override fun getItemCount() = listAnswers.size


    inner class QuestionHolder(item: View) : RecyclerView.ViewHolder(item) {
        fun fillView(answer: AnswerQuestion) {
            itemView.txtAnswers.text = answer.descrip
            itemView.setOnClickListener {
                questioncontroll.answer(answer.order) { answerAPI ->
                    if (answer.order == answerAPI) {
                        itemView.bkAnswer.setBackgroundColor(Color.parseColor("#008000"))
                    } else {
                        itemView.bkAnswer.setBackgroundColor(Color.parseColor("#ff0000"))
                    }
                    InitController.problem = false
                    val mAlertDialog = AlertDialog.Builder(itemView.context).apply {
                        setIcon(R.drawable.home)
                        setTitle(R.string.app_name)
                        setMessage(
                            MainApplication.getContext()?.getString(R.string.continue_question)
                        )
                    }
                    mAlertDialog.setPositiveButton("Yes") { dialog, id ->
                        gameAdapter.start { game ->
                            Log.e("game", game.toString())
                            InitController.game = game.data!!.game
                            it.findNavController().navigate(R.id.action_gameFragment_self)
                        }
                    }
                    mAlertDialog.setNegativeButton("No") { dialog, id ->
                        it.findNavController().navigate(R.id.action_game_to_resume)
                    }
                    val dialog = mAlertDialog.create()
                    dialog.show()
                }
            }

        }

    }
}
