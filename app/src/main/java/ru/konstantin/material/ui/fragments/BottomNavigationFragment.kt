package ru.konstantin.material.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import coil.api.load
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*
import kotlinx.android.synthetic.main.main_fragment.*
import ru.konstantin.material.R
import ru.konstantin.material.databinding.FragmentBottomNavigationBinding
import ru.konstantin.material.databinding.MainFragmentBinding
import ru.konstantin.material.model.ViewState
import ru.konstantin.material.ui.picture.PODServerResponseData
import ru.konstantin.material.ui.viewModel.PODListViewModel
import ru.konstantin.material.ui.viewModel.PictureOfTheDayViewModel
import java.time.LocalDate


class BottomNavigationFragment : AppCompatActivity() {
    private var _binding: FragmentBottomNavigationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PODListViewModel by lazy {
        ViewModelProviders.of(this).get(PODListViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTheme(THEME_ID)
        viewModel.getData(LocalDate.now().minusDays(3).toString(), LocalDate.now().toString())
            .observe(this, Observer { renderData(it) })

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, MarsFragment())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.bottom_view_weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, MoonFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.activity_api_bottom_container, EarthFragment())
                        .commitAllowingStateLoss()
                    true
                }
            }
        }
        bottom_navigation_view.selectedItemId = R.id.bottom_view_earth
        bottom_navigation_view.getOrCreateBadge(R.id.bottom_view_earth)
        val badge = bottom_navigation_view.getBadge(R.id.bottom_view_earth)
        badge?.maxCharacterCount = 2
        badge?.number = 999

        bottom_navigation_view.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.bottom_view_earth -> {
                    //Item tapped
                }
                R.id.bottom_view_mars -> {
                    //Item tapped
                }
                R.id.bottom_view_weather -> {
                    //Item tapped
                }
            }
        }
    }

    private fun renderData(data: ViewState) {
        when (data) {
            is ViewState.Success<*> -> {
                bottom_navigation_view.visibility = View.VISIBLE
                loadinglayout_in_bottom_navigatio_fragment.visibility = View.GONE
                if ((data.stateData as List<PODServerResponseData>).isNullOrEmpty()) {
//                    toast("Link is empty")
                } else {
//                    binding..text = "Photo date: ${(data.stateData).date}"
                    var photos = data.stateData as List<PODServerResponseData>
                    println(photos)
//                    binding.bottomSheetContainer.bottomSheetDescriptionHeader.text =
//                        (data.stateData).title
//                    binding.bottomSheetContainer.bottomSheetDescription.text =
//                        (data.stateData).explanation

//                    image_view.load((data.stateData).url) {
//                        lifecycle(this@PictureOfTheDayFragment)
//                        error(R.drawable.ic_load_error_vector)
//                        placeholder(R.drawable.ic_no_photo_vector)
//                    }
                }
            }
            is ViewState.Loading -> {
                if (loadinglayout_in_bottom_navigatio_fragment.visibility != View.VISIBLE) {
                    loadinglayout_in_bottom_navigatio_fragment.visibility = View.VISIBLE
                }
            }
            is ViewState.Error -> {
                loadinglayout_in_bottom_navigatio_fragment.visibility = View.GONE
//                Snackbar.make(
//                    binding.,
//                    resources.getString(R.string.error_text),
//                    Snackbar.LENGTH_INDEFINITE
//                )
//                    .setAction(resources.getString(R.string.reload_text)) {
//                        viewModel.getData()
//                    }.show()
//
//                toast(data.error.message)
            }
        }
    }
}