package arol.dev.coroutinescodelab

import java.net.HttpURLConnection

interface HttpClient {
    fun openConnection(url: String): HttpURLConnection
}