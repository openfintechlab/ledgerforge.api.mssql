# LedgerForge: A Micro-Ledger Service for Maintaining Immutable Financial Transactions
LedgerForge is a next-generation micro-ledger service designed to provide secure, efficient, and immutable financial transaction management. It serves as a dedicated lightweight yet high-performance solution for organizations and applications that require reliable financial record-keeping, real-time transaction tracking, and audit compliance.

By leveraging advanced ledger technologies, including cryptographic hashing, append-only data structures, and secure distributed storage, LedgerForge ensures that all financial transactions remain tamper-proof, transparent, and permanently recorded. This makes it an ideal choice for businesses across various industries, including fintech, banking, enterprise finance, and regulatory compliance.

# Key Features & Capabilities
1. **Immutable Transaction Records**:
Every transaction recorded in LedgerForge is permanent and tamper-proof, ensuring a reliable audit trail. Once a transaction is added to the ledger, it cannot be altered or deleted, preserving the authenticity of financial records. Ideal for regulatory compliance, fraud prevention, and financial audits.
2. **Unique Account Management**: 
Allows users to create and manage ledger accounts, each identified by a unique account number. Supports account-level transaction tracking, ensuring granular financial oversight. Provides flexible APIs for creating, retrieving, updating, and deactivating ledger accounts.
3. **Real-Time Transaction Processing**:
Supports high-throughput financial transactions with minimal latency. Ensures data consistency and synchronization across multiple systems. Enables seamless integration with payment gateways, banking systems, and enterprise financial applications.
4. **Secure and Scalable Architecture**:
Designed with cryptographic hashing and append-only storage, preventing unauthorized modifications. Can be deployed on cloud-based or on-premises infrastructure for scalable performance. Ensures high availability and fault tolerance, making it a robust solution for mission-critical financial operations.
5. **Compliance and Regulatory Support**:
Helps organizations meet compliance standards such as GAAP, IFRS, SOX, GDPR, and PCI-DSS. Generates audit-ready reports with a full transaction history and timestamped records. Offers role-based access control (RBAC) to ensure only authorized users can perform operations.

# Use Cases
**FinTech Applications:** Securely store and track payments, digital wallets, and remittances. <br>
**Banking & Financial Services:** Maintain a ledger for transactions, loan tracking, and account management.<br>
**Enterprise Accounting:** Integrate with ERP systems for financial reconciliation and compliance.<br>
**Blockchain & Smart Contracts:** Enhance decentralized applications with an immutable ledger.<br>

# Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/ledgerforge.api.mssql-0.1.0-dev01-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and method parameters for your beans (REST, CDI, Jakarta Persistence)
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
