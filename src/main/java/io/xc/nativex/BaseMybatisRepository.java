package io.xc.nativex;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author xiaochen
 * @since 2025/8/25
 */
public interface BaseMybatisRepository<T> extends BaseMapper<T> {

    int softDelete(Collection<Long> ids);

    default int softDelete(Long id) {
        return this.softDelete(List.of(id));
    }

}
