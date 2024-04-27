import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsnews.News
import com.example.sportsnews.NewsAdapter
import com.example.sportsnews.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NewsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fabAddNews: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fabAddNews = view.findViewById(R.id.fabAddNews)
        fabAddNews.setOnClickListener {
            showAddNewsDialog()
        }

        val newsList = mutableListOf<News>(
            News("Britney Spears settles long-running legal dispute with estranged father, finally bringing ultimate end to conservatorship", "It has been our honor and privilege to represent, protect, and defend Britney Spears,” the singer’s attorney, Mathew Rosengart, said in a statement to CNN.", "https://media.cnn.com/api/v1/images/stellar/prod/gettyimages-1157263385.jpg?c=16x9&q=h_653,w_1160,c_fill/f_webp"),
            News("All the times Zendaya served looks on the ‘Challengers’ press tour", "Zendaya has always aced the red carpet. But her latest press tour is, well, a grand slam.", "https://media.cnn.com/api/v1/images/stellar/prod/gettyimages-2149435252.jpg?q=w_2000,c_fill/f_webp"),
            News("Scientists find the fingerprints of climate change on Dubai’s deadly floods", "The record-breaking rain that fell over the United Arab Emirates and Oman this month, triggering deadly floods and chaos, was driven partly by the climate crisis, according to a scientific analysis published Thursday, which pointed directly at humans burning fossil fuels.", "https://media.cnn.com/api/v1/images/stellar/prod/gettyimages-2147875487.jpg?c=16x9&q=h_653,w_1160,c_fill/f_webp")
        )

        newsAdapter = NewsAdapter(newsList)
        recyclerView.adapter = newsAdapter

        return view
    }

    private fun showAddNewsDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_news, null)

        val editTextImageUrl = dialogView.findViewById<EditText>(R.id.editTextImageUrl)
        val editTextTitle = dialogView.findViewById<EditText>(R.id.editTextTitle)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDescription)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add News")
            .setPositiveButton("Add") { dialog, _ ->
                val imageUrl = editTextImageUrl.text.toString().trim()
                val title = editTextTitle.text.toString().trim()
                val description = editTextDescription.text.toString().trim()

                if (title.isNotEmpty() && description.isNotEmpty()) {
                    val newNews = News(title, description, imageUrl)
                    addNews(newNews)
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addNews(newNews: News) {
        newsAdapter.addItem(newNews)
    }
}
