package ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.data

data class Chat (
    val messageId: String,
    val audioPath: String? = null,
    val dateTime: String,
    val response: String? = null,
    val isResponse: Boolean = false
        )