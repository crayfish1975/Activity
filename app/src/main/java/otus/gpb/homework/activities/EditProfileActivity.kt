package otus.gpb.homework.activities

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class EditProfileActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        imageView = findViewById(R.id.imageview_photo)

        imageView.setOnClickListener {
            showImageOptionsDialog()
        }

//        findViewById<Toolbar>(R.id.toolbar).apply {
//            inflateMenu(R.menu.menu)
//            setOnMenuItemClickListener {
//                when (it.itemId) {
//                    R.id.send_item -> {
//                        openSenderApp()
//                        true
//                    }
//                    else -> false
//                }
//            }
//        }
    }

    // AlertDialog с выбором действий
    private fun showImageOptionsDialog() {
        val options = arrayOf("Сделать фото", "Выбрать фото")
        AlertDialog.Builder(this)
            .setTitle("Выбор действия")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> requestCameraPermission()
                    1 -> {
                        TODO()
                    }
                }
            }
            .show()
    }

    // Запрос разрешения на камеру
    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                imageView.setImageResource(R.drawable.cat)
            }
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                showRationaleDialog()
            }
            else -> {
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    // Rationale Dialog
    private fun showRationaleDialog() {
        AlertDialog.Builder(this)
            .setTitle("Доступ к камере")
            .setMessage("Чтобы сделать фото требуется доступ к камере.")
            .setPositiveButton("Дать доступ") { dialog, _ ->
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                dialog.dismiss()
            }
            .setNegativeButton("Отмена") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    // Диалог для перехода в настройки
    private fun showSettingsDialog() {
        AlertDialog.Builder(this)
            .setTitle("Необходим доступ к камере")
            .setMessage("Разрешение камеры отключено. Пожалуйста, включите его в настройках.")
            .setPositiveButton("Открыть настройки") { dialog, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)
                }
                startActivity(intent)
                dialog.dismiss()
            }
            .show()
    }

    // Запуск разрешения через ResultApi
    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            imageView.setImageResource(R.drawable.cat)
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                showRationaleDialog()
            } else {
                showSettingsDialog()
            }
        }
    }

    /**
     * Используйте этот метод чтобы отобразить картинку полученную из медиатеки в ImageView
     */
    private fun populateImage(uri: Uri) {
        val bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri))
        imageView.setImageBitmap(bitmap)
    }

    private fun openSenderApp() {
        TODO("В качестве реализации метода отправьте неявный Intent чтобы поделиться профилем. В качестве extras передайте заполненные строки и картинку")
    }
}