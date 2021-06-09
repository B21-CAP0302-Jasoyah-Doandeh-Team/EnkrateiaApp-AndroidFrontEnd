package ml.jasoyahdoandehgroup.androidteam.enkrateiaapp.data

data class Chat (
    val messageId: String,
    val userId: User,
    val audioPath: String,
    val response: String? = null
        )