package io.xc.nativex;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author xiaochen
 * @since 2025/8/20
 */
@Mapper
public interface UserMapper extends BaseMybatisRepository<UserDTO> {

    List<UserDTO> selectAll();

}
