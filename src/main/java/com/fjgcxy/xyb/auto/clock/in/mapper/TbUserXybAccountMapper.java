package com.fjgcxy.xyb.auto.clock.in.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.fjgcxy.xyb.auto.clock.in.model.entity.TbUserXybAccount;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbUserXybAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbUserXybAccount record);

    int insertSelective(TbUserXybAccount record);

    TbUserXybAccount selectByPrimaryKey(Integer id);

    List<TbUserXybAccount> selectByUserId(@Param("userId")Integer userId);


    TbUserXybAccount selectOneByUserIdAndXybUsername(@Param("userId")Integer userId,@Param("xybUsername")String xybUsername);




    int updateByPrimaryKeySelective(TbUserXybAccount record);

    int updateByPrimaryKey(TbUserXybAccount record);
}