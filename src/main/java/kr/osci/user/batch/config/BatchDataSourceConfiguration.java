package kr.osci.user.batch.config;

import com.zaxxer.hikari.HikariDataSource;
import kr.osci.user.batch.entity.BatchUserInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "kr.osci.user.batch")
public class BatchDataSourceConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Qualifier("batch")
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.batch")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Qualifier("batch")
    @Bean
    @Primary
    public DataSource dataSource(@Qualifier("batch") DataSourceProperties batchDataSourceProperties) {
        return batchDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Qualifier("batch")
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("batch") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(BatchUserInfoEntity.class)
                .persistenceUnit("batch")
                .build();
    }

}

