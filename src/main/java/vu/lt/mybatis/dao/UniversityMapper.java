package vu.lt.mybatis.dao;

import vu.lt.mybatis.model.University;

import java.util.List;
import org.mybatis.cdi.Mapper;

@Mapper
public interface UniversityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.UNIVERSITY
     *
     * @mbg.generated Sun Apr 28 20:52:24 EEST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.UNIVERSITY
     *
     * @mbg.generated Sun Apr 28 20:52:24 EEST 2019
     */
    int insert(University record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.UNIVERSITY
     *
     * @mbg.generated Sun Apr 28 20:52:24 EEST 2019
     */
    University selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.UNIVERSITY
     *
     * @mbg.generated Sun Apr 28 20:52:24 EEST 2019
     */
    List<University> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.UNIVERSITY
     *
     * @mbg.generated Sun Apr 28 20:52:24 EEST 2019
     */
    int updateByPrimaryKey(University record);
}