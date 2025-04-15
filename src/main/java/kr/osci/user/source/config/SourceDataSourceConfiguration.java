package kr.osci.user.source.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import com.zaxxer.hikari.HikariDataSource;
import kr.osci.user.batch.entity.BatchUserInfoEntity;
import kr.osci.user.config.AtomikosDataSourceProperties;
import kr.osci.user.config.BaseDataSourceConfiguration;
import kr.osci.user.source.entity.SourceUserInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableJpaRepositories(basePackages = "kr.osci.user.source.*", entityManagerFactoryRef = "sourceEntityManagerFactory", transactionManagerRef = "jtaTransactionManager")
public class SourceDataSourceConfiguration extends BaseDataSourceConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Qualifier("source")
    @Bean
    public DataSource sourceDataSource(@Qualifier("source") AtomikosDataSourceProperties.AtomikosDataSourceProperty atomikosDataSourceProperty) {
        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
        dataSource.setUniqueResourceName(atomikosDataSourceProperty.getUniqueResourceName());
        dataSource.setXaDataSourceClassName(atomikosDataSourceProperty.getXaDataSourceClassName());
        dataSource.setXaDataSource(getXaDataSource(atomikosDataSourceProperty.getXaDataSourceClassName(), atomikosDataSourceProperty.getXaProperties()));
        return new LazyConnectionDataSourceProxy(dataSource);
    }

    @Qualifier("source")
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean sourceEntityManagerFactory(@Qualifier("source") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(SourceUserInfoEntity.class)
                .persistenceUnit("source")
                .build();
    }

}
