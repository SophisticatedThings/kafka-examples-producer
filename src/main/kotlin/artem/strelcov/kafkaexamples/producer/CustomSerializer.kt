package artem.strelcov.kafkaexamples.producer

import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory

class CustomSerializer : Serializer<Any> {

    private val objectMapper = ObjectMapper()
    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {

    }
    override fun serialize(topic: String?, any: Any?): ByteArray? {
        try {
            if(any == null){
                LOGGER.info("The object to serialize is null")
                return null
            }
            return objectMapper.writeValueAsBytes(any)
        }
        catch (ex : Exception){
            val info = "Не удалось сериализовать объект $any"
            LOGGER.info(info)
            throw SerializationException(info)
        }

    }

    override fun close() {
    }

    companion object{
        private val LOGGER = LoggerFactory.getLogger(Companion::class.java)
    }
}