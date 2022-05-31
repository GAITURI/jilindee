package com.example.jilinde

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.common.io.Files.append
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_sec_maps.*
import java.util.*


class SecMaps : AppCompatActivity() {
    lateinit var placesClient: PlacesClient

    var placeFields = Arrays.asList(
        Place.Field.ID,
        Place.Field.NAME,
        Place.Field.ADDRESS
    )

    var placeId=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec_maps)
        val appContext = requireContext().applicationContext
setUpGetPhoto()
        initPlaces()
        setUpPlaceAutocomplete()
        setUpCurrentPlace()
        requestPermission()
        val permissionCheck: Int =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //Execute location service call if user has explicitly granted ACCESS_FINE_LOCATION..
        }

    }

    private fun setUpGetPhoto() {
        btn_get_photo.setOnClickListener{
            if(TextUtils.isEmpty(placeId))
            {
                Toast.makeText(this,"Place id must not be Empty",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            getPhotoAndDetails(placeId)
        }
    }

    private fun getPhotoAndDetails(placeId: String) {
val placeRequest = FetchPlaceRequest.builder(placeId!!,Arrays.asList(Place.Field.PHOTO_METADATAS,
   Place.Field.LAT_LNG)).build()
placesClient.fetchPlace(placeRequest)
    .addOnSuccessListener { fetchPlaceResponse ->
        val place = fetchPlaceResponse.place


       text_dtls.text = java.lang.StringBuilder(place.latLng.latitude.toString())
           .append("/")
           .append(place.latLng!!.longitude.toString())
        val photoMetadata =place.photoMetadatas!![0]
        val photoRequest = FetchPhotoRequest.builder(photoMetadata).build()
        placesClient.fetchPhoto(photoRequest).addOnSuccessListener { fetchPhotoResponse ->
            val bitmap = fetchPhotoResponse.bitmap
            image_view.setImageBitmap(bitmap)
        }
    }

    }


    private fun requestPermission() {
        Dexter.withActivity(this)
            .withPermissions(
                listOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {

                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    Toast.makeText(
                        applicationContext,
                        "You should accept Permisssions",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
    }





    private fun setUpCurrentPlace() {
        val request = FindCurrentPlaceRequest.builder(placeFields).build()

        get_current_place.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this@SecMaps,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            )
                return@setOnClickListener
            val placeResponse = placesClient.findCurrentPlace(request)
            placeResponse.addOnCompleteListener { task ->
                val response = task.result
                if (task.isSuccessful) {
                    val response = task.result

//likelihood will return a list of place likelihoods
                    response!!.placeLikelihoods.sortWith(Comparator { placeChildHood, tl ->
                        placeChildHood.likelihood.toDouble().compareTo(tl.likelihood.toDouble())
                    })
                    //after sorting, we need  reverse
                    Collections.reverse(response!!.placeLikelihoods)
                    response!!.placeLikelihoods.reverse()
                    placeId = response.placeLikelihoods[0].place.id.toString()
               val likelihoods = StringBuilder ("")
                    edt_address.setText(StringBuffer(response.placeLikelihoods[0].place.address))
                        //GET ALLA LIKELIHOODS TO EDIT LIKELIHOODS
                    for(placeLikelihood in response.placeLikelihoods) {
                    likelihoods.append(String.format("Place '' has likelihood: ",
                    placeLikelihood.place.name,
                    placeLikelihood.likelihood))
                        .append("\n")

                    }
                    edt_place_likelihoods.setText(likelihoods.toString())
                    }
                else{
                    Toast.makeText(applicationContext,"Place Not Found",Toast.LENGTH_LONG).show()
                }




                }
            }
        }






    private fun setUpPlaceAutocomplete() {
        val autoCompleteFragment=supportFragmentManager
            .findFragmentById(R.id.fragment_places) as AutocompleteSupportFragment
                autoCompleteFragment.setPlaceFields(placeFields)

       autoCompleteFragment.setOnPlaceSelectedListener(object:PlaceSelectionListener{
           override fun onPlaceSelected(p0: Place) {
               Toast.makeText(applicationContext,""+p0.address,Toast.LENGTH_LONG).show()
           }

           override fun onError(p0: Status) {
               Toast.makeText(applicationContext,""+p0.statusMessage,Toast.LENGTH_LONG).show()
           }

       })



    }

    private fun initPlaces() {
        Places.initialize(this,getString(R.String.))
        placesClient=Places.createClient(this)
    }
}


