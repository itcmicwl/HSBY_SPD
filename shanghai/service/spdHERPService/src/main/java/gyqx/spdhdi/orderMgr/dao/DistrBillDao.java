package gyqx.spdhdi.orderMgr.dao;


import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.orderMgr.dao.mapper.IDistrBillMapper;
import gyqx.spdhdi.orderMgr.vo.DistrBillVo;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

@Repository
public class DistrBillDao 
{
	@Resource
	private SysConfigUtil configUtil;	
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IDistrBillMapper mapper;
		
	public DistrBillVo getById(String id){
		return  mapper.getById(id);
	}
	public DistrBillVo getByBillId(String billId){
		return  mapper.getByBillId(billId);
	}
	public int update(DistrBillVo distrBill) throws Exception {
		return mapper.update(distrBill);
	}
	public int insert(DistrBillVo distrBill) throws Exception {
		return mapper.insert(distrBill);
	}
	public List<DistrBillVo> list(DistrBillVo qbean) throws Exception{		
		return mapper.list(qbean);
	}
	public int updateStatus(DistrBillVo qbean)throws Exception{		
		return mapper.updateStatus(qbean);
	}
}


