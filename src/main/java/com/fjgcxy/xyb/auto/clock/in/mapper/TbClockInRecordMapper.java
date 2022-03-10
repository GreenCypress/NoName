package com.fjgcxy.xyb.auto.clock.in.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.fjgcxy.xyb.auto.clock.in.model.entity.TbClockInRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbClockInRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbClockInRecord record);

    int insertSelective(TbClockInRecord record);

    TbClockInRecord selectByPrimaryKey(Integer id);

    List<TbClockInRecord> selectByClockInTaskIdOrderByClockInTimeDesc(@Param("clockInTaskId")Integer clockInTaskId);



    int updateByPrimaryKeySelective(TbClockInRecord record);

    int updateByPrimaryKey(TbClockInRecord record);
}