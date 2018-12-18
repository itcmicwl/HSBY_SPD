package gyqx.ws.yg.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import gyqx.ws.yg.vo.ygVo.YgCompanyInfo;

public interface IYgCompanyInfoService {
	/**
	 * 批量插入企业信息数据
	 * @param cmps
	 */
	public void saveYgCompanyInfos(@Param("cmps")List<YgCompanyInfo> cmps);
	
	/**
	 * 根据企业编码查询对应的vo
	 * @param qybm
	 * @return
	 */
	public YgCompanyInfo getYgCompanyInfo(String qybm);
	
	/**
	 * 修改企业信息
	 * @param cmp
	 */
	public void updateYgCompanyInfo(YgCompanyInfo cmp);
	List<YgCompanyInfo> list(YgCompanyInfo bean) throws Exception;
}
