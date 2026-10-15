# Java Journey 🚀

Welcome to my **Java Journey** repository! This is an umbrella project designed to centralize and document my deep-dive studies into modern Java, high-performance computing, and enterprise architecture.

The primary goal of this repository is to achieve mastery over core JVM internals, advanced concurrency models, and production-ready patterns required by high-scale international projects.

## 🛠️ Project Structure
This project is built as a **Maven Multi-Module** architecture using **Java 21**. Each topic in the roadmap below is isolated into its own dedicated submodule, ensuring a clean, clean-code, and organized learning environment.

---

## 🗺️ The Roadmap

### 🧵 Track 1 — Concurrency Deep Dive (Core Focus)
*   `01-threads-race-conditions-and-atomicity`: Synchronized, volatile, Atomic classes, and happens-before guarantees.
*   `02-java-memory-model-under-the-hood`: Visibility, instruction reordering, safe publication, and final fields.
*   `03-locks-and-thread-coordination`: ReentrantLock, Condition, StampedLock, Semaphore, and Phasers.
*   `04-executors-and-asynchronous-programming`: Thread pools, sizing, CompletableFuture, and async pipelines.
*   `05-concurrent-collections`: High-performance concurrent data structures (ConcurrentHashMap, BlockingQueue).
*   `06-virtual-threads-project-loom`: Project Loom, carrier threads, pinning, and high-throughput models.
*   `07-structured-concurrency-and-scoped-values`: Exploring Java 21 preview features for safer multi-threaded tasks.
*   `08-concurrency-diagnostics-and-profiling`: Analyzing thread dumps, deadlocks, jcmd, and Java Flight Recorder (JFR).

### 🚀 Track 2 — Modern Java Features (17 → 21)
*   `09-records-sealed-classes-and-pattern-matching`: Data-oriented programming and advanced pattern matching.
*   `10-generics-and-type-erasure-deep-dive`: Variance, wildcards, type erasure, and runtime behavior.
*   `11-advanced-streams-and-collectors`: Stream overhead, custom collectors, and parallel stream pitfalls.
*   `12-immutability-optional-and-sequenced-collections`: Designing robust code with Java 21's new collections.
*   `13-exception-handling-and-error-design`: Best practices for functional and object-oriented error handling.

### ⚙️ Track 3 — JVM Internals & Performance
*   `14-jvm-memory-layout`: In-depth analysis of Stack, Heap, Metaspace, and memory allocation.
*   `15-garbage-collection-and-tuning`: Exploring G1GC and Generational ZGC behaviors under load.
*   `16-classloading-jit-and-jvm-warmup`: Understanding Classloaders, C1/C2 compilers, and JIT optimization.
*   `17-microbenchmarking-with-jmh`: Writing precise benchmarks using the Java Microbenchmark Harness.

### 🔌 Track 4 — I/O & Integrations
*   `18-nio2-file-channels-and-buffers`: Non-blocking I/O, file channels, and buffer management.
*   `19-http-client-sync-and-async`: Modern HTTP/2 networking using Java's built-in HttpClient.
*   `20-jdbc-transactions-and-connection-pooling`: Transaction isolation levels and HikariCP tuning.
*   `21-serialization-with-jackson`: High-performance JSON binding using records and custom serializers.

### 🛡️ Track 5 — Production Quality
*   `22-unit-testing-and-concurrency-tests`: JUnit 5, Mockito, and specialized concurrency testing utilities.
*   `23-integration-testing-with-testcontainers`: Spawning ephemeral Docker containers for real-world testing.
*   `24-advanced-maven-and-java-modules`: Multi-module setups, dependency management, and JPMS.
*   `25-observability-logging-metrics-and-tracing`: Structured logging with SLF4J, Micrometer, and OpenTelemetry.

### 🌐 Track 6 — Java Ecosystem (Enterprise-Ready)
*   `spring-boot-3-and-core-framework`: Dependency Injection, AOP, and modern framework architecture.
*   `data-persistence-with-jpa-hibernate`: N+1 problem, caching, lazy loading, and persistence states.
*   `event-driven-messaging-kafka-rabbitmq`: Building event-driven applications from a Java perspective.

---

## 💻 Tech Stack & Requirements
*   **Java Development Kit (JDK):** 21+
*   **Build Tool:** Apache Maven 3.9+
*   **Preview Features:** Enabled (`--enable-preview`) for Virtual Threads & Structured Concurrency experimentation.

## 🚀 How to Run a Specific Module
1. Clone the repository:
   ```bash
   git clone https://github.com/silverioTenor/java-journey
   ```
2. Navigate to the desired module folder and compile using Maven:
   ```bash
   mvn clean compile
   ```
