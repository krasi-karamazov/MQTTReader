package kpk.dev.model.repository

import android.util.Log
import com.squareup.moshi.Moshi
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kpk.dev.model.poko.UserAddress
import kpk.dev.model.poko.UserName
import net.eusashead.iot.mqtt.paho.PahoObservableMqttClient
import org.eclipse.paho.client.mqttv3.MqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class UserDataRepository: BaseRepository {

    private val userNameSubject = PublishSubject.create<UserName>()
    private val userAddressSubject = PublishSubject.create<UserAddress>()

    private val compositeDisposable = CompositeDisposable()

    enum class Topic(val topicURI: String){
        UserAddress("interview/user-address"),
        UserName("interview/user-name")
    }

    private val client: PahoObservableMqttClient by lazy{
        val paho = MqttAsyncClient("tcp://mqtt.flespi.io", Math.random().toString(), MemoryPersistence())
        val options = MqttConnectOptions()
        options.userName = "6M3pPBQlkJuijeNWESzGlCWLNKBNUCbOc21PuG0CfjImnl6P2vlbY6KBE9cmBb0C"
        PahoObservableMqttClient.builder(paho)
            .setConnectOptions(options)
            .build()
    }

    init {
        connect()
    }

    private fun connect() {
        compositeDisposable.add(client.connect().subscribe({
            subscribeToTopic(Topic.UserName)
            subscribeToTopic(Topic.UserAddress)
        }, { e ->
            Log.d("Error", "An error occurred")
        }))
    }

    fun cleanUp() {
        compositeDisposable.add(client.disconnect().subscribe({
            compositeDisposable.dispose()
        }, { e ->
            Log.d("Error", "An error occurred")
        }))
    }

    fun getUserName(): Observable<UserName>{
        return userNameSubject
    }

    fun getUserAddress(): Observable<UserAddress> {
        return userAddressSubject
    }

    private fun subscribeToTopic(topic: Topic) {
        val moshi = Moshi.Builder().build()
        val userAddressAdapter = moshi.adapter(UserAddress::class.java)
        val userNameAdapter = moshi.adapter(UserName::class.java)
        compositeDisposable.add(client.subscribe(topic.topicURI, 1).subscribe({ msg ->
            val jsonString = String(msg.payload)
            when(topic) {
                Topic.UserAddress ->{
                    userAddressSubject.onNext(userAddressAdapter.fromJson(jsonString)!!)
                }
                Topic.UserName -> {
                    userNameSubject.onNext(userNameAdapter.fromJson(jsonString)!!)
                }
            }
        }, { e ->
            // do something on error
        }))
    }
}