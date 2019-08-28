package com.mizhi.btbs.support;

import com.mizhi.btbs.config.database.MyBatisHelper;
import com.mizhi.btbs.config.database.MyBatisHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


@EnableAutoConfiguration
@MapperScan(basePackages = {
        "com.mizhi.btbs.data.mapper",
})
public class JdbcTestBeansConfig {

    private static final String TYPE_ALIASES_PACKAGE = "com.mizhi.btbs.domain";

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        return MyBatisHelper.getSqlSessionFactory(dataSource, TYPE_ALIASES_PACKAGE, "classpath*:mapper/*Mapper.xml");
    }
}