/*
 * Copyright © 2020 NHSX. All rights reserved.
 */

package uk.nhs.nhsx.sonar.android.app.di

import com.polidea.rxandroidble2.RxBleClient
import dagger.Component
import uk.nhs.nhsx.sonar.android.app.BootCompletedReceiver
import uk.nhs.nhsx.sonar.android.app.FlowTestStartActivity
import uk.nhs.nhsx.sonar.android.app.MainActivity
import uk.nhs.nhsx.sonar.android.app.ble.BluetoothService
import uk.nhs.nhsx.sonar.android.app.contactevents.DeleteOutdatedEventsWorker
import uk.nhs.nhsx.sonar.android.app.debug.TesterActivity
import uk.nhs.nhsx.sonar.android.app.di.module.AppModule
import uk.nhs.nhsx.sonar.android.app.di.module.BluetoothModule
import uk.nhs.nhsx.sonar.android.app.di.module.CryptoModule
import uk.nhs.nhsx.sonar.android.app.di.module.NetworkModule
import uk.nhs.nhsx.sonar.android.app.di.module.NotificationsModule
import uk.nhs.nhsx.sonar.android.app.di.module.PersistenceModule
import uk.nhs.nhsx.sonar.android.app.diagnose.DiagnoseCloseActivity
import uk.nhs.nhsx.sonar.android.app.diagnose.DiagnoseCoughActivity
import uk.nhs.nhsx.sonar.android.app.diagnose.DiagnoseSubmitActivity
import uk.nhs.nhsx.sonar.android.app.diagnose.DiagnoseTemperatureActivity
import uk.nhs.nhsx.sonar.android.app.diagnose.SubmitContactEventsWorker
import uk.nhs.nhsx.sonar.android.app.diagnose.review.DiagnoseReviewActivity
import uk.nhs.nhsx.sonar.android.app.notifications.NotificationService
import uk.nhs.nhsx.sonar.android.app.notifications.ReminderBroadcastReceiver
import uk.nhs.nhsx.sonar.android.app.onboarding.PostCodeActivity
import uk.nhs.nhsx.sonar.android.app.registration.RegistrationWorker
import uk.nhs.nhsx.sonar.android.app.status.AtRiskActivity
import uk.nhs.nhsx.sonar.android.app.status.IsolateActivity
import uk.nhs.nhsx.sonar.android.app.status.OkActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        PersistenceModule::class,
        BluetoothModule::class,
        CryptoModule::class,
        NetworkModule::class,
        NotificationsModule::class
    ]
)
interface ApplicationComponent {
    fun inject(deleteOutdatedEventsWorker: DeleteOutdatedEventsWorker)
    fun inject(bluetoothService: BluetoothService)
    fun inject(isolateActivity: IsolateActivity)
    fun inject(okActivity: OkActivity)
    fun inject(atRiskActivity: AtRiskActivity)
    fun inject(diagnoseReviewActivity: DiagnoseReviewActivity)
    fun inject(diagnoseCloseActivity: DiagnoseCloseActivity)
    fun inject(mainActivity: MainActivity)
    fun inject(notificationService: NotificationService)
    fun inject(flowTestStartActivity: FlowTestStartActivity)
    fun inject(bootCompletedReceiver: BootCompletedReceiver)
    fun inject(postCodeActivity: PostCodeActivity)
    fun inject(testerActivity: TesterActivity)
    fun inject(diagnoseSubmitActivity: DiagnoseSubmitActivity)
    fun inject(diagnoseCoughActivity: DiagnoseCoughActivity)
    fun inject(diagnoseTemperatureActivity: DiagnoseTemperatureActivity)
    fun inject(reminderBroadcastReceiver: ReminderBroadcastReceiver)
    fun inject(registrationWorker: RegistrationWorker)
    fun inject(submitContactEventsWorker: SubmitContactEventsWorker)

    fun provideRxBleClient(): RxBleClient
}
