package com.dlteam2.server.Common;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import javax.persistence.metamodel.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DatabaseCleaner {
    private final EntityManager entityManager;
    private final List<String> tableNames;

    public DatabaseCleaner(EntityManager entityManager){
        this.entityManager = entityManager;
        this.tableNames = entityManager.getMetamodel()
                .getEntities()
                .stream()
                .map(Type::getJavaType)
                .map(javaType -> javaType.getAnnotation(Table.class))
                .map(Table::name)
                .collect(Collectors.toList());
    }

    @Transactional
    public void execute(){
        entityManager.flush();
        entityManager.createNativeQuery("SET foregin_key_checks = 0").executeUpdate();
        //외래키 제약조건 삭제

        for(String tableName: tableNames){
            entityManager.createNativeQuery("TRUNCATE TABLE "+tableName).executeUpdate();
        }
        entityManager.createNativeQuery("SET foregin_key_checks = 1").executeUpdate();
    }
}
