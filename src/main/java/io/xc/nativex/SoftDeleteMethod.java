package io.xc.nativex;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.Collection;

/**
 *
 * @author xiaochen
 * @since 2025/8/25
 */
public class SoftDeleteMethod extends AbstractMethod {
    public SoftDeleteMethod(String methodName) {
        super(methodName);
    }

    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String var10000 = tableInfo.getTableName();
        String sql = "<script>UPDATE " + var10000 + " SET deleted = " + true + " WHERE id in <foreach collection=\"ids\" index=\"index\" item=\"item\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach></script>";
        return this.addUpdateMappedStatement(BaseMybatisRepository.class, Collection.class, this.languageDriver.createSqlSource(this.configuration, sql, Collection.class));
    }
}
