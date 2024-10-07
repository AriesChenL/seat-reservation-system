package com.lynn.seatsystem.codegen;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午5:58
 */
public class DbGen {

    public static void main(String[] args) {
        //配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/seat_system?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建配置内容，两种风格都可以。
        GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        //GlobalConfig globalConfig = createGlobalConfigUseStyle2();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        //生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle1() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        //设置根包
        globalConfig.setBasePackage("com.lynn.seatsystem");

        globalConfig.setEntityPackage("com.lynn.seatsystem.domain.entity");

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);

        //设置项目的JDK版本，项目的JDK为14及以上时建议设置该项，小于14则可以不设置
        globalConfig.setEntityJdkVersion(21);

        //设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);

        globalConfig.setServiceGenerateEnable(true);
        globalConfig.setServiceImplGenerateEnable(true);

        globalConfig.setMapperXmlGenerateEnable(true);
        globalConfig.setMapperXmlPath("src/main/resources/mapper");

        return globalConfig;
    }
}
