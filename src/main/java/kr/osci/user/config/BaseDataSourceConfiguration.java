package kr.osci.user.config;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import javax.sql.XADataSource;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseDataSourceConfiguration implements BeanClassLoaderAware {

    private ClassLoader classLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public XADataSource getXaDataSource(String className, DataSourceProperties dataSourceProperties) {
        try {
            Class<?> dataSourceClass = ClassUtils.forName(className, this.classLoader);
            Object instance = BeanUtils.instantiateClass(dataSourceClass);
            Assert.isInstanceOf(XADataSource.class, instance);
            bindXaProperties((XADataSource) instance, dataSourceProperties);
            return (XADataSource) instance;
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to create XADataSource instance from '" + className + "'");
        }
    }

    private void bindXaProperties(XADataSource target, DataSourceProperties dataSourceProperties) {
        Binder binder = new Binder(getBinderSource(dataSourceProperties));
        binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(target));
    }

    private ConfigurationPropertySource getBinderSource(DataSourceProperties dataSourceProperties) {
        Map<Object, Object> properties = new HashMap<>(dataSourceProperties.getXa().getProperties());
        properties.computeIfAbsent("user", (key) -> dataSourceProperties.getUsername());
        properties.computeIfAbsent("password", (key) -> dataSourceProperties.getPassword());
        try {
            properties.computeIfAbsent("url", (key) -> dataSourceProperties.getUrl());
        } catch (Exception ex) {
            // Continue as not all XA DataSource's require a URL
        }
        MapConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();
        aliases.addAliases("user", "username");
        return source.withAliases(aliases);
    }

}
