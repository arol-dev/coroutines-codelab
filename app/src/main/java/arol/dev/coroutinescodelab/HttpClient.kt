package arol.dev.coroutinescodelab

import java.net.HttpURLConnection
import java.net.URL

interface HttpClient {
    fun openConnection(url: String): HttpURLConnection
}

class HttClientImpl : HttpClient {
    override fun openConnection(url: String): HttpURLConnection =
        URL(url).openConnection() as HttpURLConnection
}