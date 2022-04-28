package lift.ydq.commuity.community.mapper;

import java.util.List;
import lift.ydq.commuity.community.model.Follow;
import lift.ydq.commuity.community.model.FollowExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FollowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    long countByExample(FollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    int deleteByExample(FollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    int insert(Follow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    int insertSelective(Follow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    List<Follow> selectByExampleWithRowbounds(FollowExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    List<Follow> selectByExample(FollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    Follow selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    int updateByExampleSelective(@Param("record") Follow record, @Param("example") FollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    int updateByExample(@Param("record") Follow record, @Param("example") FollowExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    int updateByPrimaryKeySelective(Follow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FOLLOW
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    int updateByPrimaryKey(Follow record);
}