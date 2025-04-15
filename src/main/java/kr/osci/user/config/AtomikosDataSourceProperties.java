package kr.osci.user.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource")
public class AtomikosDataSourceProperties {

    private AtomikosDataSourceProperty source;
    private AtomikosDataSourceProperty batch;

    @Qualifier("source")
    @Bean
    public AtomikosDataSourceProperty getSourceAtomikosDataSourceProperty() {
        return source;
    }

    @Qualifier("batch")
    @Bean
    @Primary
    public AtomikosDataSourceProperty getBatchAtomikosDataSourceProperty() {
        return batch;
    }

    public static class AtomikosDataSourceProperty {
        private String uniqueResourceName;
        private String xaDataSourceClassName;
        private DataSourceProperties xaProperties;

        public String getUniqueResourceName() {
            return uniqueResourceName;
        }

        public String getXaDataSourceClassName() {
            return xaDataSourceClassName;
        }

        public DataSourceProperties getXaProperties() {
            return xaProperties;
        }

        public void setUniqueResourceName(String uniqueResourceName) {
            this.uniqueResourceName = uniqueResourceName;
        }

        public void setXaDataSourceClassName(String xaDataSourceClassName) {
            this.xaDataSourceClassName = xaDataSourceClassName;
        }

        public void setXaProperties(DataSourceProperties xaProperties) {
            this.xaProperties = xaProperties;
        }
    }

    public AtomikosDataSourceProperty getSource() {
        return source;
    }

    public AtomikosDataSourceProperty getBatch() {
        return batch;
    }

    public void setSource(AtomikosDataSourceProperty source) {
        this.source = source;
    }

    public void setBatch(AtomikosDataSourceProperty batch) {
        this.batch = batch;
    }

}
