//package com.example.mymarvel;
//
//
//import com.opentable.db.postgres.embedded.EmbeddedPostgres;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DBConfig {
//
//    @Bean
//    public DataSource inMemoryDS() throws Exception {
//        DataSource embeddedPostgresDS = EmbeddedPostgres.builder()
//                .setPort(5432)
//                .start().getPostgresDatabase();
//        return embeddedPostgresDS;
//    }
//}
