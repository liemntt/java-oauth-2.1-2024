package dev.thanhliem.oauth.configurations.ds;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.Objects;

@Slf4j
@Configuration
@MapperScan(basePackages = {DataSourceConfiguration.BASE_PACKAGE})
public class DataSourceConfiguration {

    public static final String MAPPER_LOCATION_PATTERN = "classpath*:/mappers/*.xml";
    public static final String BASE_PACKAGE = "dev.thanhliem.oauth.repositories.mappers";
    public static final String SESSION_FACTORY = "sqlSessionFactory";

    @Bean(name = SESSION_FACTORY)
    public SqlSessionFactory sessionFactory(HikariDataSource ds, ApplicationContext context) throws Exception {
        var factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds);
        factoryBean.setVfs(SpringBootVFS.class);
        factoryBean.setTypeAliasesPackage(BASE_PACKAGE);
        Resource[] resources = context.getResources(MAPPER_LOCATION_PATTERN);
        if (resources.length > 0) {
            factoryBean.setMapperLocations(resources);
        }

        SqlSessionFactory factory = factoryBean.getObject();
        Objects.requireNonNull(factory).getConfiguration().setMapUnderscoreToCamelCase(true);
        log.info("[DataSourceConfiguration] Initializing sessionFactory");
        return factory;
    }

    @Bean
    public SqlSessionTemplate template(SqlSessionFactory sqlSessionFactory) {
        log.info("[DataSourceConfiguration] Initializing template");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSourceTransactionManager transactionManager(HikariDataSource dataSource) {
        log.info("[DatasourceConfiguration] Initializing transactionManager");
        return new DataSourceTransactionManager(dataSource);
    }

}
