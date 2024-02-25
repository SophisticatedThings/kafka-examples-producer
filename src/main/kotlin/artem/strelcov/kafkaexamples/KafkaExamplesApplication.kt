package artem.strelcov.kafkaexamples

import artem.strelcov.kafkaexamples.dto.TransferObject
import artem.strelcov.kafkaexamples.dto.TransferObjectAnother
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.KafkaTemplate

@SpringBootApplication
class KafkaExamplesApplication(
	val template: KafkaTemplate<String, Any>,
	val kafkaProducer: KafkaProducer<String, Any>
) {
	@Bean
	fun topic1() = NewTopic("topic1", 10, 1)
	@Bean
	fun topic2() = NewTopic("topic2", 10, 1)
	@Bean
	fun test() : Boolean {
		while (true) {
			val message = readln()
			template.send("topic1",0,"1" , TransferObject(message,1))
			template.send("topic2",0,"1" , TransferObjectAnother(1,true))
			//kafkaProducer.send(ProducerRecord("topic1","1",TransferObject(message,1)))
			//kafkaProducer.send(ProducerRecord("topic2","1",TransferObjectAnother(1,true)))
		}
		kafkaProducer.close()
		return false
	}

	companion object{
		private val LOGGER = LoggerFactory.getLogger(Companion::class.java)
	}
}
fun main(args: Array<String>) {
	runApplication<KafkaExamplesApplication>(*args)

}