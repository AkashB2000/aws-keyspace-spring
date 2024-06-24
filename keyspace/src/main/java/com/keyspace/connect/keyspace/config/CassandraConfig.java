package com.keyspace.connect.keyspace.config;

import com.datastax.dse.driver.api.core.auth.ProgrammaticDseGssApiAuthProvider;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.aws.mcs.auth.SigV4AuthProvider;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Configuration
public class CassandraConfig {
    @Bean
    public CqlSession session() throws NoSuchAlgorithmException {
        //File driverConfig = new File(System.getProperty("user.dir") + "/application.conf");

        SigV4AuthProvider provider = new SigV4AuthProvider("us-east-1");
        DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
        CqlSession session= CqlSession.builder()
                .withConfigLoader(loader)
                .withKeyspace("perinataldatastore") // Replace with your keyspace name
                .build();

        String query = "SELECT * FROM perinataldatastore.user LIMIT 10"; // Replace with your table name and query
        ResultSet resultSet = session.execute(SimpleStatement.newInstance(query));

        // Print the results
        for (Row row : resultSet) {
            UUID id = row.getUuid("id");
            String name = row.getString("name");
            int age = row.getInt("age");
            System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);

        }

        return session;



        }
    }
