package otus.gpb.homework.activities

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_c)

        val buttonOpenA: Button = findViewById(R.id.open_a)
        val buttonOpenD: Button = findViewById(R.id.open_d)
        val buttonCloseC: Button = findViewById(R.id.close_c)
        val buttonCloseStack: Button = findViewById(R.id.close_stack)

        buttonOpenA.setOnClickListener {
            // TODO
        }
        buttonOpenD.setOnClickListener {
            // TODO
        }
        buttonCloseC.setOnClickListener {
            // TODO
        }
        buttonCloseStack.setOnClickListener {
            // TODO
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}