package io.xc.nativex;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author xiaochen
 * @since 2025/8/8
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class TeController {
    
    private final UserMapper userMapper;

    @GetMapping({"/","/test"})
    public List<UserDTO> test(){
        var dto = new UserDTO();
        var id = System.nanoTime() + ThreadLocalRandom.current().nextLong(0, 1000000000);
        dto.setId(id); // 仅用于测试
        dto.setName("张三");
        dto.setAge(ThreadLocalRandom.current().nextInt(18, 36));
        userMapper.insert(dto);
        var res1 = userMapper.softDelete(List.of(id));
        var res2 = userMapper.selectById(id);
        var res3 = userMapper.updateById(res2);
        var query = new LambdaQueryWrapper<UserDTO>();
        query.select(UserDTO::getId, UserDTO::getName);
        var res4 = userMapper.selectList(query);
        var res5 = userMapper.selectList(new LambdaQueryWrapper<UserDTO>().eq(UserDTO::getId, 1L));
        var res6 = userMapper.update(new LambdaUpdateWrapper<UserDTO>().set(UserDTO::getName, "李四").eq(UserDTO::getId, 1));
        var res7 = userMapper.selectPage(new Page<>(1, 10), null);
        return userMapper.selectAll();
    }

    @GetMapping("/delAll")
    public void delAll(){
        userMapper.delete(null);
    }

}
