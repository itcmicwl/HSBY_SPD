package gyqx.spdherp.productsInfos.dao.mapper;

import java.util.List;

import gyqx.spdhdi.po.HosKindcode;
 
public interface IHosKindcodeMapper {
	int update(HosKindcode bean);
	List<HosKindcode> list(HosKindcode queryInfo) ;
	void delete(HosKindcode bean);
}

