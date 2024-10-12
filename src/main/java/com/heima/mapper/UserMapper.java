package com.heima.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.pojo.User;

/**
 * @author asura
 * @description 针对表【news_user】的数据库操作Mapper
 * @createDate 2024-10-11 15:29:37
 * @Entity com.heima.pojo.User
 */
public interface UserMapper extends BaseMapper<User> {

    int insertBatch(@Param("userCollection") Collection<User> userCollection);
}




