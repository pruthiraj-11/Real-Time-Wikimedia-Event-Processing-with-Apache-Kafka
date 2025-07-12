# 🚀 Apache Kafka Wikimedia Queueing

## 📚 Overview

This repository contains the implementation of a data processing system for Wikimedia projects using Apache Kafka. The project focuses on capturing Wikimedia data as a stream through a Kafka Producer, storing it in Apache Kafka topics, and subsequently processing and forwarding it to OpenSearch through a Kafka Consumer.

## 🚀 Project Description

The primary goal of this project is to establish a seamless flow of data from Wikimedia projects to OpenSearch, leveraging the capabilities of Apache Kafka for efficient and scalable data processing. The project comprises three main components:

1. **Kafka Producer for Wikimedia:**
   - Ingests data from Wikimedia projects as a continuous stream.
   - Publishes the data to specific Apache Kafka topics for further processing.

2. **Kafka Consumer with OpenSearch Integration:**
   - Subscribes to the Kafka topics containing Wikimedia data.
   - Processes the data and sends it to OpenSearch for indexing and search capabilities.

3. **📂 Repository Structure:**
   - **[kafka-basics](./kafka-basics):** Kafka basics module with Opensearch consumer added.
   - **[kafka-consumer-opensearch](./kafka-consumer-opensearch):** Kafka consumer with Opensearch integration.
   - **[kafka-producer-wikimedia](./kafka-producer-wikimedia):** Kafka producer for Wikimedia data sources.

## 🚀 Getting Started

To begin working with this project, follow these steps:

1. **🔍 Clone the repository:**
   ```bash
   git clone https://github.com/Prathmesh311/Apache-Kafka-Wikimedia-queueing.git
   cd Apache-Kafka-Wikimedia-queueing
   ```

2. **🚀 Explore the modules:**
   - Review the Kafka basics, Opensearch consumer, and Wikimedia producer modules for specific functionalities.

3. **🏃 Run the Kafka Producer:**
   - Follow instructions in the `kafka-producer-wikimedia` module to start ingesting data from Wikimedia into Kafka.

4. **🏃 Run the Kafka Consumer:**
   - Refer to the `kafka-consumer-opensearch` module for setting up the Kafka Consumer with OpenSearch integration.



This project showcases the ability to design and implement scalable and efficient data processing systems using Apache Kafka. The integration with OpenSearch enhances the overall functionality, enabling the extraction of valuable insights from Wikimedia data.

## 🤝 Contributions

Contributions to the project are encouraged. If you encounter issues, have suggestions for improvements, or would like to contribute new features, please open an issue or submit a pull request.

