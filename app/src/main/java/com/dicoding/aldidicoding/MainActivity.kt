package com.dicoding.aldidicoding

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ArticleAdapter
    private val list = ArrayList<Article>()
    private var isListView = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Halaman Utama"

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val articleList = listOf(
            Article("Taman Safari", "Overview of Taman Safari", R.drawable.tamansafari, "Bogor", "2014-10-01",
                "Taman Safari Indonesia, terletak di Bogor, adalah sebuah kebun binatang yang luas dan merupakan salah satu taman safari terbesar di Indonesia. " +
                        "Dikenal karena keindahan alamnya dan beragam spesies satwa, pengunjung dapat melihat hewan-hewan dari mobil mereka dalam suasana yang mirip dengan habitat alami. " +
                        "Taman Safari juga menawarkan berbagai atraksi, termasuk pertunjukan hewan dan wahana permainan.\n"),

            Article("Monumen Nasional", "Overview of Monumen Nasional", R.drawable.monas, "Jakarta", "1945-10-02",
                "Monumen Nasional atau Monas adalah ikon Jakarta yang dibangun untuk memperingati perjuangan kemerdekaan Indonesia. " +
                        "Terletak di pusat kota, monumen ini memiliki tinggi 132 meter dan dilapisi dengan emas. " +
                        "Pengunjung dapat naik ke puncaknya untuk menikmati pemandangan kota Jakarta. " +
                        "Monas dikelilingi oleh taman yang luas, menjadi tempat yang populer bagi warga dan wisatawan untuk beristirahat dan bersantai.\n"),

            Article("Museum Nasional", "Overview of Musem Nasional", R.drawable.munas, "Jakarta", "2024-10-03",
                "Museum Nasional Indonesia, sering disebut sebagai Museum Gajah, terletak di Jakarta. " +
                        "Museum ini menyimpan koleksi artefak sejarah dan budaya yang sangat kaya, termasuk koleksi arkeologi, etnografi, dan geografi. " +
                        "Gedungnya yang megah merupakan salah satu museum tertua di Indonesia dan memberikan wawasan mendalam tentang sejarah dan kebudayaan Indonesia dari masa pra-sejarah hingga modern."),

            Article("Dufan Park", "Overview of Dufan", R.drawable.dufan, "Jakarta", "2024-10-04",
                "Dunia Fantasi, atau Dufan, adalah taman hiburan terbesar di Indonesia, terletak di Jakarta. " +
                        "Dufan menawarkan berbagai wahana yang mendebarkan, pertunjukan, dan atraksi yang sesuai untuk semua usia. " +
                        "Dengan tema yang beragam, pengunjung dapat menikmati roller coaster, pertunjukan air, dan wahana yang terinspirasi dari cerita-cerita populer, menjadikannya tujuan yang ideal untuk keluarga dan penggemar petualangan."),

            Article("Gunung Bromo", "Overview of Gunung Bromo", R.drawable.bromo, "Probolinggo", "2024-10-05",
                "Gunung Bromo adalah salah satu gunung berapi aktif yang paling terkenal di Indonesia, terletak di Taman Nasional Bromo Tengger Semeru. " +
                        "Dikenal dengan pemandangan matahari terbitnya yang menakjubkan dan lautan pasirnya."),

            Article("Pantai Kuta", "Overview of Pantai Kuta", R.drawable.kuta, "Bali", "2024-10-06",
                "Pantai Kuta terkenal dengan pasir putihnya yang lembut dan ombak yang cocok untuk surfing. Ini adalah salah satu destinasi wisata paling populer di Bali."),

            Article("Candi Borobudur", "Overview of Candi Borobudur", R.drawable.borobudur, "Magelang", "2024-10-07",
                "Candi Borobudur adalah situs warisan dunia UNESCO dan merupakan candi Buddha terbesar di dunia. Dibangun pada abad ke-9."),

            Article("Pantai Pink", "Overview of Pantai Pink", R.drawable.pantai_pink, "Pulau Komodo", "2024-10-08",
                "Pantai Pink di Pulau Komodo dikenal karena pasirnya yang berwarna pink dan airnya yang jernih."),

            Article("Danau Toba", "Overview of Danau Toba", R.drawable.danau_toba, "Sumatera Utara", "2024-10-09",
                "Danau Toba adalah danau vulkanik terbesar di dunia dan memiliki pulau Samosir di tengahnya."),

            Article("Pulau Bali", "Overview of Pulau Bali", R.drawable.bali, "Bali", "2024-10-10",
                "Bali adalah destinasi wisata yang terkenal dengan budaya yang kaya, pemandangan yang indah, dan pantai-pantai yang menakjubkan."),

            Article("Taman Mini Indonesia Indah", "Overview of Taman Mini", R.drawable.taman_mini, "Jakarta", "2024-10-11",
                "Taman Mini Indonesia Indah adalah taman budaya yang menampilkan miniatur rumah adat dari berbagai provinsi di Indonesia."),

            Article("Pulau Komodo", "Overview of Pulau Komodo", R.drawable.komodo, "Nusa Tenggara Timur", "2024-10-12",
                "Pulau Komodo terkenal sebagai habitat asli dari komodo, kadal terbesar di dunia."),

            Article("Jam Gadang", "Overview of Jam Gadang", R.drawable.jamgadang, "Bukittinggi", "2024-10-13",
                "Jam Gadang adalah ikon kota Bukittinggi, Sumatera Barat. Menara jam ini dibangun pada tahun 1926 dan memiliki tinggi sekitar 26 meter. Jam Gadang dikelilingi oleh taman yang asri dan merupakan tempat berkumpulnya masyarakat."),

            Article("Candi Prambanan", "Overview of Candi Prambanan", R.drawable.prambanan, "Sleman", "2024-10-14",
                "Candi Prambanan adalah candi Hindu terbesar di Indonesia dan diakui sebagai warisan dunia UNESCO."),

            Article("Air Terjun Tumpak Sewa", "Overview of Air Terjun Tumpak Sewa", R.drawable.tumpuksewa, "Lumajang", "2024-10-15",
                "Air Terjun Tumpak Sewa adalah salah satu air terjun terindah di Indonesia."),

            Article("Bukit Jambul", "Overview of Bukit Jambul", R.drawable.jambul, "Karangasem", "2024-10-16",
                "Bukit Jambul adalah lokasi pemandangan yang indah di Bali."),

            Article("Pantai Sanur", "Overview of Pantai Sanur", R.drawable.sanur, "Bali", "2024-10-17",
                "Pantai Sanur terkenal dengan keindahan matahari terbit dan suasana yang tenang."),

            Article("Taman Nasional Way Kambas", "Overview of Taman Nasional Way Kambas", R.drawable.kambas, "Lampung", "2024-10-18",
                "Taman Nasional Way Kambas adalah rumah bagi badak Sumatera yang terancam punah."),

            Article("Goa Pindul", "Overview of Goa Pindul", R.drawable.pindul, "Gunungkidul", "2024-10-19",
                "Goa Pindul adalah goa yang terkenal dengan wisata cave tubing, di mana pengunjung dapat menjelajahi goa dengan perahu ban.")
        )


        adapter = ArticleAdapter(articleList) { article ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_list -> {

                showRecyclerList()
                true
            }
            R.id.action_grid -> {

                showRecyclerGrid()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showRecyclerList() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
        isListView = true
    }

    private fun showRecyclerGrid() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        adapter.notifyDataSetChanged()
        isListView = false
    }
}
