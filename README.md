# kafka-producer-consumer

## 1. Setup kafka in your local (windows machine)
### 1.1.    Binary download from https://kafka.apache.org/downloads
```
1.  Extract at you desire path i.e. D:\kafka
2.  Edit D:\kafka\kafka_2.12-2.8.1\config\zookeeper.properties file and update attribute dataDir as:
    dataDir=d:/soft/kafka/zookeeper-data
3.  Edit D:\kafka\kafka_2.12-2.8.1\config\server.properties file and update attribute log.dirs as:
    log.dirs=d:\kafka\kafka-logs    
```
### 1.2.    Start Zookeeper
```
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```
### 1.3.    Start Kafka Server
```
.\bin\windows\kafka-server-start.bat .\config\server.properties
```
### 1.4.    Create Kafka Topic
```
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafkaTopic
```
### 1.5.    Start Producer for specific Topic
```
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic kafkaTopic
```
### 1.6.    Start Consumer for specific Topic
```
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic kafkaTopic --from-beginning
```


