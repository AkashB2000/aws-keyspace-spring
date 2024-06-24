package com.keyspace.connect.keyspace.config;

import com.datastax.dse.driver.api.core.auth.ProgrammaticDseGssApiAuthProvider;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.aws.mcs.auth.SigV4AuthProvider;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

@Configuration
public class CassandraConfig {
    @Bean
    public CqlSession session() throws NoSuchAlgorithmException {
        //File driverConfig = new File(System.getProperty("user.dir") + "/application.conf");

        SigV4AuthProvider provider = new SigV4AuthProvider("ap-south-1");
        DriverConfigLoader loader = DriverConfigLoader.fromClasspath("application.conf");
        return CqlSession.builder()
                .withConfigLoader(loader)
                .withKeyspace("perinataldatastore") // Replace with your keyspace name
                .build();

//        List<InetSocketAddress> contactPoints = Collections.singletonList(
//                        InetSocketAddress.createUnresolved("cassandra.us-east-2.amazonaws.com", 9142));
//
//        try (CqlSession session = CqlSession.builder()
//                .addContactPoints(contactPoints)
//                .withSslContext(SSLContext.getDefault())
//                .withLocalDatacenter("us-east-2")
//                .withAuthProvider(provider)
//                .build()) {
//            // App code here...
        }
    }
