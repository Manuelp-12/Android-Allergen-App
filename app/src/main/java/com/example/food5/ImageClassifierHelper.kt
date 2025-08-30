package com.example.food5

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.image.ops.ResizeOp.ResizeMethod
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class ImageClassifierHelper(
    context: Context,
    modelName: String = "food101_model.tflite"
) {
    private val interpreter: Interpreter
    private val labels: List<String> = context.assets.open("labels.txt").bufferedReader().readLines()
    private val imageSize = 224
    private val numClasses = labels.size

    init {
        val modelBuffer = loadModelFile(context, modelName)
        interpreter = Interpreter(modelBuffer)
    }

    private fun loadModelFile(context: Context, modelName: String): ByteBuffer {
        val fileDescriptor = context.assets.openFd(modelName)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    fun classifyImage(bitmap: Bitmap): Pair<String, Int> {
        val tensorImage = TensorImage.fromBitmap(bitmap)

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224,224, ResizeMethod.BILINEAR))
            .add(NormalizeOp(0f, 255f))
            .build()

        val processedImage = imageProcessor.process(tensorImage)

        val inputBuffer = processedImage.buffer

        val outputBuffer = Array(1) { FloatArray(numClasses) }

        interpreter.run(inputBuffer, outputBuffer)

        val scores = outputBuffer[0]
        val maxIndex = scores.indices.maxByOrNull { scores[it] } ?: -1
        val label = if (maxIndex != -1 && maxIndex < labels.size) labels[maxIndex] else "Unknown"
        val confidence = (scores[maxIndex] * 100).toInt()

        return Pair(label, confidence)
    }
}