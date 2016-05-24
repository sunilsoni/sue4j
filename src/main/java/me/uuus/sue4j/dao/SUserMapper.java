package me.uuus.sue4j.dao;

import java.util.List;
import me.uuus.sue4j.pojo.SUser;
import me.uuus.sue4j.pojo.SUserExample;
import org.apache.ibatis.annotations.Param;

public interface SUserMapper {
    int countByExample(SUserExample example);

    int deleteByExample(SUserExample example);

    int deleteByPrimaryKey(Integer sId);

    int insert(SUser record);

    int insertSelective(SUser record);

    List<SUser> selectByExample(SUserExample example);

    SUser selectByPrimaryKey(Integer sId);

    int updateByExampleSelective(@Param("record") SUser record, @Param("example") SUserExample example);

    int updateByExample(@Param("record") SUser record, @Param("example") SUserExample example);

    int updateByPrimaryKeySelective(SUser record);

    int updateByPrimaryKey(SUser record);
}