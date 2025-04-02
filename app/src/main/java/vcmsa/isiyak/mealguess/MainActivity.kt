package vcmsa.isiyak.mealguess

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }// Declarations
        val radioGroup: RadioGroup = findViewById(R.id.radioGroupTime)
        val btnSuggest: Button = findViewById(R.id.btnSuggest)
        val btnReset: Button = findViewById(R.id.btnReset)
        val textViewResult: TextView = findViewById(R.id.textViewResult)
        //chat gpt assisted

        // Meal suggestions based on time of day
        val mealSuggestions = mapOf(
            R.id.radioMorning to "Scrambled eggs & toast",
            R.id.radioMidMorning to "Oatmeal with berries",
            R.id.radioLunch to "Chicken salad sandwich",
            R.id.radioAfterLunch to "green tea",
            R.id.radioSupper to "Grilled salmon with vegetables",
            R.id.radioAfterSupper to "Ice cream with strawberries"
        )
        btnSuggest.setOnClickListener {
            val selectedTimeId = radioGroup.checkedRadioButtonId
            val suggestedMeal = mealSuggestions[selectedTimeId] ?: "No suggestion available"
            textViewResult.text = "Suggestion: $suggestedMeal"
        }

        btnReset.setOnClickListener {
            textViewResult.text = ""
            radioGroup.clearCheck()
            radioGroup.check(R.id.radioMorning)
            //if radioButton is not selected then show please select a time of day
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                if (checkedId == -1) {
                    textViewResult.text = "Please select a time of day"
                    btnSuggest.isEnabled = false
                } else {
                    textViewResult.text = "Enjoy your meal!"
                    btnSuggest.isEnabled = true
                    radioGroup.setOnCheckedChangeListener(null)

                }
            }
        }
    }
}
