package xyz.eliothmonroy.viewmodelcourtcounter

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import xyz.eliothmonroy.viewmodelcourtcounter.ViewModel.ScoreViewModel

class MainActivity : AppCompatActivity() {

    lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreViewModel=ViewModelProvider(this).get(ScoreViewModel::class.java)

        displayForTeamA(scoreViewModel.scoreTeamA)
        displayForTeamB(scoreViewModel.scoreTeamB)
    }

    /**
     * Increase the score for Team A by 1 point.
     */
    fun addOneForTeamA(v: View?) {
        scoreViewModel.scoreTeamA += 1
        displayForTeamA(scoreViewModel.scoreTeamA)
    }

    /**
     * Increase the score for Team A by 2 points.
     */
    fun addTwoForTeamA(v: View?) {
        scoreViewModel.scoreTeamA += 2
        displayForTeamA(scoreViewModel.scoreTeamA)
    }

    /**
     * Increase the score for Team A by 3 points.
     */
    fun addThreeForTeamA(v: View?) {
        scoreViewModel.scoreTeamA += 3
        displayForTeamA(scoreViewModel.scoreTeamA)
    }

    /**
     * Increase the score for Team B by 1 point.
     */
    fun addOneForTeamB(v: View?) {
        scoreViewModel.scoreTeamB += 1
        displayForTeamB(scoreViewModel.scoreTeamB)
    }

    /**
     * Increase the score for Team B by 2 points.
     */
    fun addTwoForTeamB(v: View?) {
        scoreViewModel.scoreTeamB += 2
        displayForTeamB(scoreViewModel.scoreTeamB)
    }

    /**
     * Increase the score for Team B by 3 points.
     */
    fun addThreeForTeamB(v: View?) {
        scoreViewModel.scoreTeamB += 3
        displayForTeamB(scoreViewModel.scoreTeamB)
    }

    /**
     * Resets the score for both teams back to 0.
     */
    fun resetScore(v: View?) {
        scoreViewModel.scoreTeamA = 0
        scoreViewModel.scoreTeamB = 0
        displayForTeamA(scoreViewModel.scoreTeamA)
        displayForTeamB(scoreViewModel.scoreTeamB)
    }

    /**
     * Displays the given score for Team A.
     */
    fun displayForTeamA(score: Int) {
        val scoreView = findViewById<View>(R.id.team_a_score) as TextView
        scoreView.text = score.toString()
    }

    /**
     * Displays the given score for Team B.
     */
    fun displayForTeamB(score: Int) {
        val scoreView = findViewById<View>(R.id.team_b_score) as TextView
        scoreView.text = score.toString()
    }

}
