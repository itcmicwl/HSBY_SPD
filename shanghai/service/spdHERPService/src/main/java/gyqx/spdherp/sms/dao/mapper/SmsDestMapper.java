package gyqx.spdherp.sms.dao.mapper;

import gyqx.spdherp.sms.vo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther Liangwu
 * @Date 18-1-25 下午12:57
 */
public interface SmsDestMapper {
    @Select("select user_id as userId, org_id as orgId, tel from sys_user_org where job = #{job} and org_id = #{orgId} and status = 1")
    List<UserInfo> findPhoneNumsQG10(@Param("job")String job, @Param("orgId") String orgId);

    @Select("SELECT suo.user_id as userId, suo.org_id as orgId, suo.tel as tel FROM apply_audit_rel aar LEFT OUTER JOIN sys_user_org suo ON aar.user_id = suo.user_id WHERE aar.pur_mode = #{purMode} and suo.org_id = #{orgId} and suo.status = 1")
    List<UserInfo> findPhoneNumsQG11(@Param("purMode") Integer purMode, @Param("orgId") String orgId);

    @Select("select user_id as userId, org_id as orgId, tel from sys_user_org where job = #{job} and org_id = #{orgId} and status = 1")
    List<UserInfo> findPhoneNumsQG20(@Param("job") String job, @Param("orgId") String orgId);

    @Select("SELECT suo.user_id as userId, suo.org_id as orgId, suo.tel FROM apply_audit_rel aar LEFT OUTER JOIN sys_user_org suo ON aar.user_id = suo.user_id WHERE aar.pur_mode = #{purMode} and suo.org_id = #{orgId} and suo.status = 1")
    List<UserInfo> findPhoneNumsCK10(@Param("purMode") Integer purMode, @Param("orgId") String orgId);

    @Select("select user_id as userId, org_id as orgId, tel from sys_user_org where user_id = #{userId} and status = 1")
    List<UserInfo> findPhoneNumsCK30Req(@Param("userId") String userId);

    @Select("select user_id as userId, org_id as orgId, tel from sys_user_org where job = #{job} and org_id = #{orgId} and status = 1")
    List<UserInfo> findPhoneNumsCK30Trans(@Param("job") String job, @Param("orgId") String orgId);
}
