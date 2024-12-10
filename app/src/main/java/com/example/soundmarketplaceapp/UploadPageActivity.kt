package com.example.soundmarketplaceapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class UploadPageActivity : AppCompatActivity() {

    private lateinit var btnStore: ImageButton
    private lateinit var btnNews: ImageButton
    private lateinit var btnProfile: ImageButton

    private lateinit var btnUploadSound: Button
    private lateinit var editTitle: EditText
    private lateinit var editTags: EditText
    private lateinit var editPrice: EditText
    private lateinit var btnSubmit: Button
    private lateinit var licenseSpinner: Spinner
    private lateinit var textSystemName: TextView

    private var selectedFileUri: Uri? = null

    companion object {
        const val PICK_AUDIO_REQUEST = 100
        const val SOUND_DATA_RESULT = "SOUND_DATA_RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_page)

        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgrey)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.darkgrey)

        btnStore = findViewById(R.id.imgbtn_store_up)
        btnStore.setOnClickListener {
            val intent = Intent(this, StorePageActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnNews = findViewById(R.id.imgbtn_news_up)
        btnNews.setOnClickListener {
            val intent = Intent(this, NewsPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnProfile = findViewById(R.id.imgbtn_profile_up)
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnUploadSound = findViewById(R.id.btnUploadSound)
        editTitle = findViewById(R.id.editTitle)
        editTags = findViewById(R.id.editTags)
        editPrice = findViewById(R.id.editPrice)
        btnSubmit = findViewById(R.id.btnSubmit)
        licenseSpinner = findViewById(R.id.licenseSpinner)
        textSystemName = findViewById(R.id.textSystemName)

        setupLicenseSpinner()

        btnUploadSound.setOnClickListener { pickAudioFile() }
        btnSubmit.setOnClickListener { submitSoundData() }
    }

    private fun setupLicenseSpinner() {
        val licenses = arrayOf("Полная", "Частичная")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, licenses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        licenseSpinner.adapter = adapter
    }

    private fun pickAudioFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "audio/*"
        startActivityForResult(intent, PICK_AUDIO_REQUEST)
    }

    private fun submitSoundData() {
        if (selectedFileUri == null) {
            Toast.makeText(this, "Загрузите аудиофайл", Toast.LENGTH_SHORT).show()
            return
        }

        val title = editTitle.text.toString().trim()
        val tags = editTags.text.toString().trim()
        val price = editPrice.text.toString().trim()
        val license = licenseSpinner.selectedItem.toString()

        if (title.isEmpty() || tags.isEmpty() || price.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }

        val soundData = SoundData(
            systemName = textSystemName.text.toString(),
            title = title,
            tags = tags,
            price = price.toDouble(),
            license = license,
            audioUri = selectedFileUri.toString()
        )

        val intent = Intent(this, StorePageActivity::class.java)
        intent.putExtra(SOUND_DATA_RESULT, soundData)
        startActivity(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_AUDIO_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedFileUri = data.data
            selectedFileUri?.let {
                textSystemName.text = it.lastPathSegment
            }
        }
    }
}
