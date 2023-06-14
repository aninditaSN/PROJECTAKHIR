import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.netblinkapp.ChatFragment
import com.example.netblinkapp.HomeFragment
import com.example.netblinkapp.R
import com.example.netblinkapp.R.id.bottom_navigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfilFragment : Fragment() {
    private lateinit var bottomNavigationView: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profil, container, false)

        bottomNavigationView = rootView.findViewById(bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Navigasi ke layout HomeFragment
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, HomeFragment()).commit()
                    true
                }
                R.id.navigation_chat -> {
                    // Navigasi ke layout ChatFragment
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, ChatFragment()).commit()
                    true
                }
                R.id.navigation_profile -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, ProfilFragment()).commit()
                    true
                }
                else -> false
            }
        }

        val button = rootView.findViewById<Button>(R.id.btn3)
        button.setOnClickListener {
            val phoneNumber = "628990415500" // nomor telepon penerima
            val message = "Halo, ini adalah pesan untuk Anda!" // pesan yang akan dikirim
            val url =
                "https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(message)}" // URL untuk membuka aplikasi WhatsApp
            try { // coba membuka aplikasi WhatsApp
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // tampilkan pesan error jika aplikasi WhatsApp tidak ditemukan
                Toast.makeText(requireContext(), "Aplikasi WhatsApp tidak ditemukan!", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }
        }

        return rootView
    }
}
