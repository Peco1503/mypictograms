package com.tec.frontend.util

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
class ImageUploader{
    companion object {

        fun uploadToStorage(uri: Uri, context: Context, userFolder: String, category: String, imageTitle: String ) {
            val storage = Firebase.storage

            // Create a storage reference from our app
            var storageRef = storage.reference

            var spaceRef: StorageReference
            spaceRef = storageRef.child("$userFolder/$category/$imageTitle.jpg")


            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }

            byteArray?.let{

                var uploadTask = spaceRef.putBytes(byteArray)
                uploadTask.addOnFailureListener {
                    Toast.makeText(
                        context,
                        "upload failed",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Handle unsuccessful uploads
                }.addOnSuccessListener { taskSnapshot ->
                    // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                    // ...
                    Toast.makeText(
                        context,
                        "upload successed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}