package gyqx.ws.yg.dao;


import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.ws.yg.dao.mapper.IYgRecConfirmMapper;
import gyqx.ws.yg.dao.mapper.IYgSendBillListStateMapper;
import gyqx.ws.yg.vo.repVo.YY161_REP_DETAIL;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_DETAIL;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class YgRecConfirmDao
{

	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IYgRecConfirmMapper mapper;
	public YY131_REQ_DETAIL get(String psmxbh) throws Exception{
		return mapper.get(psmxbh);
	}
	public int updateByBatch(List<YY131_REQ_DETAIL> lst) throws Exception{
		return mapper.updateByBatch(lst);
	}
	public int  insertByBatch(List<YY131_REQ_DETAIL> lst) throws Exception{
		return mapper.insertByBatch(lst);
	}
	public int  update(YY131_REQ_DETAIL bean) throws Exception{
		return mapper.update(bean);
	}
	public int  insert(YY131_REQ_DETAIL bean) throws Exception{
		return mapper.insert(bean);
	}
	public int setBillConfirm(String billId) throws Exception{
		return mapper.setBillConfirm(billId);
	}
	public List<YY131_REQ_DETAIL> list(YY131_REQ_DETAIL qbean) throws Exception{
		
		return mapper.list(qbean);
	}
}


