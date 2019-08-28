package com.mizhi.btbs.config;

import com.mizhi.btbs.config.database.MyBatisHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author mou
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {
        "com.mizhi.btbs.data.mapper"
})
public class WorkFlowSpringConfiguration {
    private static final String TYPE_ALIASES_PACKAGE = "com.mizhi.btbs.domain";

    @Autowired
    private DataSource dataSource;

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        return MyBatisHelper.getSqlSessionFactory(dataSource, TYPE_ALIASES_PACKAGE, "classpath*:mapper/*Mapper.xml");
    }
}
