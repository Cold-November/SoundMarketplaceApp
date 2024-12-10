package com.example.soundmarketplaceapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.media.MediaPlayer
import android.net.Uri

class StorePageActivity : AppCompatActivity() {

    private lateinit var btnNews: ImageButton
    private lateinit var btnUpload: ImageButton
    private lateinit var btnProfile: ImageButton

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: ImageButton
    private lateinit var soundListView: ListView
    private lateinit var playerControls: LinearLayout
    private lateinit var playButton: Button
    private lateinit var pauseButton: Button

    private val soundList = mutableListOf<SoundData>()
    private lateinit var adapter: ArrayAdapter<String>
    private var mediaPlayer: MediaPlayer? = null
    private var currentTrackUri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_page)

        btnNews = findViewById(R.id.imgbtn_news_store)
        btnNews.setOnClickListener {
            val intent = Intent(this, NewsPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnUpload = findViewById(R.id.imgbtn_upload_store)
        btnUpload.setOnClickListener {
            val intent = Intent(this, UploadPageActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnProfile = findViewById(R.id.imgbtn_profile_store)
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        checkPermissions()
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgrey)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.darkgrey)

        // Инициализация UI
        searchEditText = findViewById(R.id.editSearch)
        searchButton = findViewById(R.id.btnSearch)
        soundListView = findViewById(R.id.soundListView)
        playerControls = findViewById(R.id.player_controls)
        playButton = findViewById(R.id.btnPlay)
        pauseButton = findViewById(R.id.btnPause)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        soundListView.adapter = adapter

        // Добавление новых треков
        val newSound = intent.getParcelableExtra<SoundData>(UploadPageActivity.SOUND_DATA_RESULT)
        if (newSound != null) {
            soundList.add(newSound)
            adapter.add("${newSound.title} - ${newSound.price}₽")
            adapter.notifyDataSetChanged()
        }

        soundListView.setOnItemClickListener { _, _, position, _ ->
            val selectedSound = soundList[position]
            currentTrackUri = selectedSound.audioUri
            startPlayer(selectedSound.audioUri)
        }

        playButton.setOnClickListener { playAudio() }
        pauseButton.setOnClickListener { pauseAudio() }

        searchButton.setOnClickListener { performSearch() }
    }

    private fun startPlayer(uri: String) {
        playerControls.visibility = LinearLayout.VISIBLE
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer.create(this, Uri.parse(uri))
        mediaPlayer?.start()
    }

    private fun playAudio() {
        mediaPlayer?.start()
    }

    private fun pauseAudio() {
        mediaPlayer?.pause()
    }

    private fun performSearch() {
        val query = searchEditText.text.toString().trim()
        adapter.clear()

        val filtered = if (query.isEmpty()) soundList else soundList.filter {
            it.title.contains(query, ignoreCase = true) || it.tags.contains(query, ignoreCase = true)
        }

        filtered.forEach {
            adapter.add("${it.title} - ${it.price}₽")
        }
        adapter.notifyDataSetChanged()
    }
    private fun checkPermissions() {
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

