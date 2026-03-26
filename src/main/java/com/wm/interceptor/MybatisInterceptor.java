package com.wm.interceptor;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import com.wm.entity.TenantEntity;
import com.wm.utils.ContextHolder;

@Intercepts({
    @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})

@Component
public class MybatisInterceptor implements Interceptor{
	
	@Override
    public Object intercept(Invocation invocation) throws Throwable {
		TenantEntity  tenantInfo = ContextHolder.getContextHolder("tenantInfo", TenantEntity.class);
		if(Objects.isNull(tenantInfo)) {
			// 没有 tenantInfo 信息，直接放行
            return invocation.proceed();
		}
        String schema = tenantInfo.getSchemaName();
        if(Objects.isNull(schema) || schema.isEmpty()) {
        	// 没有 schema 信息，直接放行
            return invocation.proceed();
        }

        // 获取sql文
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String originalSql = boundSql.getSql();

        // 替换占位符 #schema#
        String newSql = originalSql.replaceAll("#schema#", schema);

        // 通过反射修改 BoundSql 中的 sql 字段
        Field sqlField = BoundSql.class.getDeclaredField("sql");
        sqlField.setAccessible(true);
        sqlField.set(boundSql, newSql);

        // 继续执行原方法
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
