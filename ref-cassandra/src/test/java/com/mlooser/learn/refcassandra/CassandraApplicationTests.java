package com.mlooser.learn.refcassandra;

import static org.junit.Assert.assertTrue;

import org.apache.cassandra.utils.UUIDGen;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnit;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.mlooser.learn.refcassandra.model.Person;
import com.mlooser.learn.refcassandra.repositories.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@TestExecutionListeners({
	CassandraUnitDependencyInjectionTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class })
@CassandraDataSet
@EmbeddedCassandra
public class CassandraApplicationTests {

	@Autowired
	private PersonRepository personRepository;	
	
	@Test
	public void contextLoads() {
		
		Person p1 = new Person("1", "ML", 33);
		personRepository.save(p1);
		
		assertTrue(personRepository.findById(p1.getId()).isPresent());		
	}

}
