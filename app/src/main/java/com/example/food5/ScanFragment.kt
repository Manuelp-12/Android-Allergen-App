package com.example.food5

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private var lastScannedCode: String? = null


class ScanFragment : Fragment() {

    private lateinit var previewView: PreviewView
    private lateinit var barcodeText: TextView
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        previewView = view.findViewById(R.id.previewView)
        barcodeText = view.findViewById(R.id.barcodeText)

        cameraExecutor = Executors.newSingleThreadExecutor()

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 10)
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            val barcodeScanner = BarcodeScanning.getClient()

            val imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            imageAnalyzer.setAnalyzer(cameraExecutor) { imageProxy ->
                processImageProxy(imageProxy, barcodeScanner)
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    @OptIn(ExperimentalGetImage::class)
    private fun processImageProxy(imageProxy: ImageProxy, scanner: com.google.mlkit.vision.barcode.BarcodeScanner) {
        val mediaImage = imageProxy.image ?: return
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

        scanner.process(image)
            .addOnSuccessListener { barcodes ->
                if (barcodes.isNotEmpty()) {
                    val rawValue = barcodes[0].rawValue
                    if (!rawValue.isNullOrEmpty() && rawValue != lastScannedCode) {
                        lastScannedCode = rawValue
                        requireActivity().runOnUiThread {
                            //barcodeText.text = "Scanned: $rawValue"
                            barcodeText.setTextColor(Color.rgb(0,255,0))
                            barcodeText.text = "Scanned"
                        }
                        fetchProductInfo(rawValue)
                    }
                }
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
            .addOnCompleteListener {
                imageProxy.close()
            }
    }

    private fun fetchProductInfo(barcode: String) {
        RetrofitInstance.api.getProductByBarcode(barcode)
            .enqueue(object : retrofit2.Callback<OpenFoodResponse> {
                override fun onResponse(
                    call: retrofit2.Call<OpenFoodResponse>,
                    response: retrofit2.Response<OpenFoodResponse>
                ) {
                    if (response.isSuccessful) {
                        val product = response.body()?.product
                        product?.let {
                            val name = it.product_name ?: "Unknown"
                            val brand = it.brands ?: "Unknown brand"
                            val imageurl= it.image_url ?: "https://images.vexels.com/media/users/3/131734/isolated/preview/05d86a9b63d1930d6298b27081ddc345-photo-preview-frame-icon.png"
                            val allergensArray = it.allergens_tags ?: emptyList()
                            var allergens = allergensArray.joinToString(", ") { it.removePrefix("en:") }

                            if (allergensArray.isEmpty()) {
                                allergens = "none"
                            }

                            val ingredients = it.ingredients_text ?: "N/A"

                            requireActivity().runOnUiThread {
                                //barcodeText.text = "Name: $name\nBrand: $brand\nIngredients: $ingredients\nAllergens: $allergens"
                                ScannedArray.ScannedItems.add(ScannedItem(name, brand, imageurl, allergens, ingredients))
                                val intent = Intent(context, ScannedProductActivity::class.java)
                                intent.putExtra("product",name)
                                intent.putExtra("brand",brand)
                                intent.putExtra("imageurl", imageurl)
                                intent.putExtra("allergens", allergens)
                                intent.putExtra("ingredients", ingredients)
                                context?.startActivity(intent)
                            }
                        } ?: run {
                            showToast("Product not found")
                            barcodeText.setTextColor(Color.rgb(255,255,255))
                            barcodeText.text = "Scan barcode: "
                        }
                    } else {
                        showToast("Error: ${response.message()}")
                    }
                }

                override fun onFailure(call: retrofit2.Call<OpenFoodResponse>, t: Throwable) {
                    showToast("API call failed: ${t.message}")
                }
            })
    }

    private fun showToast(message: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun allPermissionsGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
    }

    override fun onResume() {
        super.onResume()
        lastScannedCode = null
        barcodeText.setTextColor(Color.rgb(255,255,255))
        barcodeText.text = "Scan barcode: "
    }
}