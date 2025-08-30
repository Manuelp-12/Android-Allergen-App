package com.example.food5

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File
import java.util.Locale

class DetectFragment : Fragment() {
    private lateinit var classifier: ImageClassifierHelper
    private var photoUri: Uri? = null
    var allergensMap = AllergenData.allergensMap

    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoUri != null) {
            val inputStream = requireContext().contentResolver.openInputStream(photoUri!!)
            val bitmap = BitmapFactory.decodeStream(inputStream)

            // Run classification
            val (label, confidence) = classifier.classifyImage(bitmap)

            val mainAllergens = allergensMap[label]?.get("main")

            var mainString = ""
            if (mainAllergens == null) {
                mainString = "none"
            }
            else {
                mainString = mainAllergens.joinToString(", ")
            }

            val potentialAllergens = allergensMap[label]?.get("potential")

            var potentialString = ""
            if (potentialAllergens == null) {
                potentialString = "none"
            }
            else {
                potentialString = potentialAllergens.joinToString(", ")
            }

            val scannedProduct = DetectedItem(formatProduct(label), confidence,
                photoUri!!, mainString, potentialString)
            if (!ScannedArray.ScannedItems.contains(scannedProduct)) {
                ScannedArray.ScannedItems.add(scannedProduct)
            }

            val intent = Intent(context, DetectedProductActivity::class.java)
            intent.putExtra("product", label)
            intent.putExtra("confidence", confidence)
            intent.putExtra("mainAllergens", mainString)
            intent.putExtra("potentialAllergens", potentialString)
            intent.putExtra("image", photoUri)

            context?.startActivity(intent)
        } else {
            Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detect, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        classifier = ImageClassifierHelper(requireContext())

        //button to trigger camera
        val captureButton: View = view.findViewById(R.id.captureButton)
        captureButton.setOnClickListener {
            launchCamera()
        }
    }

    private fun launchCamera() {
        val photoFile = File.createTempFile("photo_", ".jpg", requireContext().cacheDir)
        photoUri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.provider",
            photoFile
        )
        takePictureLauncher.launch(photoUri)
    }

    fun formatProduct(str: String?): String {
        if (str == null) {
            return ""
        }
        val sb = StringBuilder(
            str.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
            }
        )

        var i = 0
        while (i < sb.length) {
            if (sb[i] == '_') {
                sb.setCharAt(i, ' ') // replace _ with space
                if (i + 1 < sb.length) {
                    sb.setCharAt(i + 1, sb[i + 1].uppercaseChar()) // capitalize next char
                }
            }
            i++
        }
        return sb.toString()
    }
}