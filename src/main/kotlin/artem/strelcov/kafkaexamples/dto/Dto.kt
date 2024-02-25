package artem.strelcov.kafkaexamples.dto

class TransferObject(
    val message: String? = null,
    val version: Int? = null
)

class TransferObjectAnother(
    val quality: Int? = null,
    val isEssential: Boolean? = null
)

