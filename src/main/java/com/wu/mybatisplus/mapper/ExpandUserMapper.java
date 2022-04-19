package com.wu.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wu.mybatisplus.entity.User;

import java.util.Collection;

public interface ExpandUserMapper extends BaseMapper<User> {

    Integer insertBatchSomeColumn(Collection<User> entityList);
}
