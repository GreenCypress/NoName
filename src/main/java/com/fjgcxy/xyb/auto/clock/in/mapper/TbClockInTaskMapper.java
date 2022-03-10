package com.fjgcxy.xyb.auto.clock.in.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.fjgcxy.xyb.auto.clock.in.model.entity.TbClockInTask;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbClockInTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbClockInTask record);

    int insertSelective(TbClockInTask record);

    TbClockInTask selectByPrimaryKey(Integer id);

    TbClockInTask selectOneByXybAccountId(@Param("xybAccountId")Integer xybAccountId);

    List<TbClockInTask> selectByState(@Param("state")Integer state);


    List<TbClockInTask> selectByUserId(@Param("userId")Integer userId);




    int updateByPrimaryKeySelective(TbClockInTask record);

    int updateByPrimaryKey(TbClockInTask record);
}