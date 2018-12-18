package gyqx.spdherp.stockout.dao;


import java.util.List;
import javax.annotation.Resource;

import gyqx.spdherp.stockout.vo.OutStockBillVo;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.stockout.vo.Sickuse4print;
import gyqx.spdherp.stockout.vo.SickuseGoods4print;

import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;

import gyqx.spdherp.stockout.dao.mapper.ISickerUseMapper;
import gyqx.spdherp.po.SickerUse;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;

@Repository
public class SickerUseDao 
{
	@Resource
	private SimpleDao dao;	
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private ISickerUseMapper mapper;

	public SickerUserVo get(String id)
	{
		return mapper.get(id);
	}
	public int update(SickerUserVo bean){
		return mapper.update(bean);
	}
	public int insert(SickerUserVo bean){
		return mapper.insert(bean);
	}

	public List<SickerUserVo> list(SickerUserVo qbean) throws Exception{
		
		return mapper.list(qbean);
	}
	public Sickuse4print getSickuser4Print(String billId) {
		// TODO Auto-generated method stub
		return mapper.getSickuser4Print(billId);
	}
	public List<SickuseGoods4print> getSickuseGoods4print(String billId) {
		// TODO Auto-generated method stub
		return mapper.getSickuseGoods4print(billId);
	}
}


