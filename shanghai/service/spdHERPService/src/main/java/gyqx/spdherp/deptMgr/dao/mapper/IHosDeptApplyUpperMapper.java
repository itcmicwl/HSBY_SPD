package gyqx.spdherp.deptMgr.dao.mapper;

import java.util.List;

import gyqx.spdherp.po.HosDeptApplyUpper;
 
public interface IHosDeptApplyUpperMapper {
	int update(HosDeptApplyUpper bean);
	int insert(HosDeptApplyUpper bean);
	List<HosDeptApplyUpper> list(HosDeptApplyUpper queryInfo) ;
}

