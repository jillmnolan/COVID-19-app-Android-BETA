package uk.nhs.nhsx.sonar.android.app.onboarding

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_edge_case.edgeCaseText
import kotlinx.android.synthetic.main.activity_edge_case.edgeCaseTitle
import kotlinx.android.synthetic.main.activity_edge_case.takeActionButton
import uk.nhs.nhsx.sonar.android.app.R
import uk.nhs.nhsx.sonar.android.app.ble.LocationProviderChangedReceiver
import uk.nhs.nhsx.sonar.android.app.util.isLocationEnabled

open class EnableLocationActivity :
    AppCompatActivity(R.layout.activity_edge_case) {
    private var locationSubscription: Disposable? = null
    private lateinit var locationProviderChangedReceiver: LocationProviderChangedReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        edgeCaseTitle.setText(R.string.enable_location_service_title)
        edgeCaseText.setText(R.string.enable_location_service_rationale)
        takeActionButton.setText(R.string.go_to_app_settings)

        takeActionButton.setOnClickListener {
            val intent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(intent)
        }

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationProviderChangedReceiver = LocationProviderChangedReceiver(locationManager)
    }

    override fun onResume() {
        super.onResume()
        if (isLocationEnabled()) {
            finish()
        }
        registerReceiver(
            locationProviderChangedReceiver,
            IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
        )
        locationProviderChangedReceiver.onCreate()
        locationSubscription =
            locationProviderChangedReceiver.getLocationStatus().subscribe { isLocationEnabled ->
                if (isLocationEnabled) {
                    finish()
                }
            }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(locationProviderChangedReceiver)
        locationSubscription?.dispose()
    }

    companion object {
        fun start(context: Context) =
            context.startActivity(getIntent(context))

        private fun getIntent(context: Context) =
            Intent(context, EnableLocationActivity::class.java)
    }
}