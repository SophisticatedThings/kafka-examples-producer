package artem.strelcov.kafkaexamples.producer

import artem.strelcov.kafkaexamples.dto.TransferObject
import artem.strelcov.kafkaexamples.dto.TransferObjectAnother
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WebSender(
    val template: KafkaTemplate<String, Any>,
    val producer: KafkaProducer<String, Any>
) {
    @GetMapping("/send")
    fun sendMessageToTopic1(@RequestBody dto : Any) {
        when(dto) {
            is TransferObject -> template.send("topic1", dto)
            is TransferObjectAnother -> template.send("topic2", dto)
            else -> {}
        }
    }
    @GetMapping("/send-default")
    fun sendMessageToTopic1Default(@RequestBody dto : Any) {
        when(dto){
            is TransferObject -> producer.send(ProducerRecord("topic1","1",dto))
            is TransferObjectAnother -> producer.send(ProducerRecord("topic2","1",dto))
            else -> {}
        }
    }
}