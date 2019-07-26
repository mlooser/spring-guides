package com.mlooser.learn.relationaldataaccess;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void run(String... args) throws Exception {
    log.info("Creating table.");

    jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
    jdbcTemplate.execute("CREATE TABLE customers(" +
        "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

    List<Object[]> splitUpNames = Arrays.asList("AaF AaL", "BbF BbL", "AaF CcL")
        .stream()
        .map(v -> v.split(" "))
        .collect(Collectors.toList());

    log.info("Inserting customers.");

    jdbcTemplate.batchUpdate(
        "INSERT INTO customers(first_name, last_name) VALUES (?,?)",
        splitUpNames);

    log.info("Quering for customers.");

    List<Customer> customers = jdbcTemplate.query(
        "SELECT id, first_name, last_name FROM customers WHERE first_name = ? ",
        new Object[] { "AaF" },
        (rs, rowNum) -> new Customer(
            rs.getLong("id"),
            rs.getString("first_name"),
            rs.getString("last_name")));

    customers
        .stream()
        .map(c -> c.toString())
        .forEach(log::info);
  }
}
