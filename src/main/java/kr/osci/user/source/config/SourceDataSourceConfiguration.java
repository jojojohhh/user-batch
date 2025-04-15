package kr.osci.user.source.config;

import com.zaxxer.hikari.HikariDataSource;
import kr.osci.user.source.entity.SourceUserInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "kr.osci.user.source", entityManagerFactoryRef = "sourceEntityManagerFactory")
public class SourceDataSourceConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Qualifier("source")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("spring.datasource.source")
    public DataSourceProperties sourceDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Qualifier("source")
    @Bean(defaultCandidate = false)
    public DataSource sourceDataSource(@Qualifier("source") DataSourceProperties sourceDataSourceProperties) {
        return sourceDataSourceProperties
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Qualifier("source")
    @Bean
    public LocalContainerEntityManagerFactoryBean sourceEntityManagerFactory(@Qualifier("source") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(SourceUserInfoEntity.class)
                .persistenceUnit("source")
                .build();
    }

}
