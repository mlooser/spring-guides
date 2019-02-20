package com.mlooser.learn.refcassandra.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
@PropertySource("classpath:cassandra.properties")
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Value("${cassandra.keyspace}")
	private String keyspace;

	@Value("${cassandra.contactpoints}")
	private String contactPoints;

	@Value("${cassandra.port}")
	private int port;

	@Value("${cassandra.keyspace.replication}")
	private int replication;

	@Override
	protected String getKeyspaceName() {
		return keyspace;
	}

	@Override
	protected String getContactPoints() {
		return contactPoints;
	}

	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}

	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

		return Collections.singletonList(CreateKeyspaceSpecification
				.createKeyspace(keyspace)
				.ifNotExists(true)
				.withSimpleReplication(replication));
	}

	@Override
	protected int getPort() {
		return port;
	}

	@Override
	public String[] getEntityBasePackages() {
		return new String[] { "com.mlooser.learn.refcassandra.model" };
	}
}
