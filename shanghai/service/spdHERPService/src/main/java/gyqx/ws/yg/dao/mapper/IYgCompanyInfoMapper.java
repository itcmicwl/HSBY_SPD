package gyqx.ws.yg.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.ws.yg.vo.ygVo.YgCompanyInfo;

public interface IYgCompanyInfoMapper {
	/**
	 * 批量插入企业信息数据
	 * 
	 * @param cmps
	 */
	public void saveYgCompanyInfos(@Param("cmps") List<YgCompanyInfo> cmps);

	/**
	 * 根据企业编码查询对应的实体
	 * @param qybm
	 * @return
	 */
	public YgCompanyInfo getYgCompanyInfo(String qybm);
	
	List<YgCompanyInfo> list(YgCompanyInfo bean);
}
