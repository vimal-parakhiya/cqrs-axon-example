package com.github.vp.example.axon;

import com.github.vp.example.axon.DataSourceFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.axonframework.common.jdbc.ConnectionProvider;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jdbc.EventSchema;
import org.axonframework.eventsourcing.eventstore.jdbc.JdbcEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.SQLErrorCodesResolver;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.NoOpEventUpcaster;
import org.axonframework.spring.jdbc.SpringDataSourceConnectionProvider;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by vimalpar on 08/07/17.
 */
@Configuration
@EnableConfigurationProperties
@PropertySource(value = {"classpath:/autoconfigure.properties"})
public class DatabaseConfiguration {

    @ConfigurationProperties(prefix = "database.write")
    @Bean
    public DataSourceFactory dataSourceFactory() {
        return new DataSourceFactory();
    }

    @Bean
    public DataSource dataSource(DataSourceFactory dataSourceFactory) {
        return dataSourceFactory.dataSource();
    }

    @Bean
    public DataSourceTransactionManager eventStoreTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public EventStorageEngine jdbcEventStorageEngine(Serializer serializer,
                                                 PersistenceExceptionResolver persistenceExceptionResolver,
                                                 ConnectionProvider connectionProvider,
                                                 TransactionManager axonTransactionManager) {
        return new JdbcEventStorageEngine(
                serializer,
                NoOpEventUpcaster.INSTANCE,
                persistenceExceptionResolver,
                null,
                connectionProvider,
                axonTransactionManager,
                String.class,
                eventSchema(),
                null,
                null
        );
    }

    @Primary
    @Bean("eventBus")
    public EventStore eventStore(EventStorageEngine eventStorageEngine) {
        return new EmbeddedEventStore(eventStorageEngine);
    }

    private EventSchema eventSchema() {
        return EventSchema.builder()
                .withEventTable("domain_event_entry")
                .withGlobalIndexColumn("global_index")
                .withTypeColumn("type")
                .withAggregateIdentifierColumn("aggregate_identifier")
                .withSequenceNumberColumn("sequence_number")
                .withEventIdentifierColumn("event_identifier")
                .withPayloadColumn("payload")
                .withPayloadTypeColumn("payload_type")
                .withPayloadRevisionColumn("payload_revision")
                .withMetaDataColumn("meta_data")
                .withTimestampColumn("time_stamp")
                .build();
    }

    @Bean
    public PersistenceExceptionResolver persistenceExceptionResolver(DataSource dataSource) throws SQLException {
        return new SQLErrorCodesResolver(dataSource);
    }

    @Bean
    public ConnectionProvider springDataSourceConnectionProvider(DataSource dataSource) {
        return new SpringDataSourceConnectionProvider(dataSource);
    }

    @Bean
    public TransactionManager axonTransactionManager(PlatformTransactionManager eventStoreTransactionManager) {
        return new SpringTransactionManager(eventStoreTransactionManager);
    }


    @ConfigurationProperties(prefix = "database.write")
    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        return springLiquibase;
    }
}
