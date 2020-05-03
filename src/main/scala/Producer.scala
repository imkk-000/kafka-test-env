import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord, RecordMetadata}
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

object Producer extends App {
  val broker = "localhost:9092"
  val topic = "change-message"
  val changeMessage = "{}"
  val config = new Properties()
  config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker)

  def produce(topic: String, data: Array[Byte]): RecordMetadata = {
    val producer = new KafkaProducer[String, Array[Byte]](
      config, new StringSerializer, new ByteArraySerializer
    )
    val record = new ProducerRecord(topic, "1234.5.678.999", data)

    producer.send(record).get
  }

  produce(topic, changeMessage.getBytes)
}
