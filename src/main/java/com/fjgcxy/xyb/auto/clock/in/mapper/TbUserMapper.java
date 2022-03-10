package com.fjgcxy.xyb.auto.clock.in.mapper;

import com.fjgcxy.xyb.auto.clock.in.model.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;

@Mapper
public interface TbUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    TbUser selectOneByUsername(@Param("username") String username);
}