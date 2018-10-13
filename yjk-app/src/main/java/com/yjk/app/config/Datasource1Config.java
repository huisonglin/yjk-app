package com.yjk.app.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import tk.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author wuwei
 * @date 2018/5/3 15:00
 */
@Primary
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.yjk.app.dao"}, sqlSessionTemplateRef = "sqlSessionTemplate1")
public class Datasource1Config {

    @Value("${mybatis.configuration.mapUnderscoreToCamelCase}")
    private boolean mapUnderscoreToCamelCase;

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;

    /**
     * datasource配置
     *
     * @return
     */
    @Bean(name = "datasource1")
    @ConfigurationProperties(prefix = "spring.datasource.ds1")
    public DataSource setDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * SqlSessionFactory配置
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory1")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("datasource1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //设置数据源
        bean.setDataSource(dataSource);
        //设置驼峰转换
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(mapUnderscoreToCamelCase);
        bean.setConfiguration(configuration);
        //设置mapper文件位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        //给包中的类注册别名，注册后可以直接使用类名，而不用使用全限定的类名
        bean.setTypeAliasesPackage(typeAliasesPackage);
        return bean.getObject();
    }

    /**
     * SqlSessionTemplate配置
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "sqlSessionTemplate1")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置事物管理器
     *
     * @return
     */
    @Bean(name = "transactionManager1")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("datasource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
