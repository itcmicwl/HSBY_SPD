package gyqx.spdherp.deptMgr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import common.db.SimpleDao;
import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.SysAtomUtil;
import common.utils.UserOnlineStateUtils;
import gyqx.spdherp.deptMgr.dao.ReceiveAddressDao;
import gyqx.spdherp.deptMgr.service.ReceiveAddressService;
import gyqx.spdherp.deptMgr.vo.BaseAreaVo;
import gyqx.spdherp.deptMgr.vo.ReceiveAddressVo;
import gyqx.ws.yg.common.Constance;
import gyqx.ws.yg.common.HostUtils;
import gyqx.ws.yg.common.JAXBUtil;
import gyqx.ws.yg.common.YgSendUtil;
import gyqx.ws.yg.common.YgTimeUtil;
import gyqx.ws.yg.vo.SMPType;
import gyqx.ws.yg.vo.reqVo.YY101_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY101_REQ_XML;

@Service
public class ReceiveAddressServiceImpl implements ReceiveAddressService {

	@Resource
	private ReceiveAddressDao receiveAddressDao;
	@Resource
	private UserOnlineStateUtils userOnlineStateUtils;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private SimpleDao dao;
	@Resource
	private YgSendUtil ygUtils;
	@Override
	public QueryResult<ReceiveAddressVo> getReceiveAddressList(QueryInfo<ReceiveAddressVo> queryInfo) throws Exception {
		QueryResult<ReceiveAddressVo> queryResult = new QueryResult<>();
		// 设置数据
		ReceiveAddressVo vo = queryInfo.getQueryObject();
		if (vo == null) {
			queryInfo.getQueryObject().setHosId(userOnlineStateUtils.getCurrent().getCorpId());
			queryInfo.getQueryObject().setDeptId(userOnlineStateUtils.getCurrent().getOrgId());
		}
		PageInfo<ReceiveAddressVo> pageInfo = receiveAddressDao.getReceiveAddressList(queryInfo);
		queryResult.setData(pageInfo.getList());
		queryResult.setPageNum(pageInfo.getPageNum());
		queryResult.setPageSize(pageInfo.getPageSize());
		queryResult.setTotal(pageInfo.getTotal());
		return queryResult;
	}

	@Override
	public void updateReceiveAddress(ReceiveAddressVo receiveAddressVo) throws Exception {
		receiveAddressDao.updateReceiveAddress(receiveAddressVo);
	}

	@Override
	public ReceiveAddressVo insertReceiveAddress(ReceiveAddressVo receiveAddressVo) throws Exception {
		receiveAddressVo.setId(atomUtil.newValue("receive-address-id"));
		// receiveAddressVo.setHosId(userOnlineStateUtils.getCurrent().getCorpId());
		// receiveAddressVo.setDeptId(userOnlineStateUtils.getCurrent().getOrgId());
		ReceiveAddressVo vo = receiveAddressDao.insertReceiveAddress(receiveAddressVo);
		return vo;
	}

	@Override
	public int deleteReceiveAddress(ReceiveAddressVo receiveAddressVo) throws Exception {
		return dao.executeSql("delete from receive_address where id = ?", receiveAddressVo.getId());
	}

	@Override
	public List<BaseAreaVo> queryBaseArea(BaseAreaVo baseAreaVo) {
		return receiveAddressDao.queryBaseArea(baseAreaVo.getPid());
	}

	@Override
	public void setDefaultAddr(ReceiveAddressVo receiveAddressVo) {
		receiveAddressDao.setDefaultAddr(receiveAddressVo);
	}

	@Override
	public String sendYg(ReceiveAddressVo receiveAddressVo) throws Exception {
		YY101_REQ_XML xml = new YY101_REQ_XML();
		YY101_REQ_MAIN main = new YY101_REQ_MAIN();
		main.setCzlx(Constance.OP_11.OP_ADD.toString());
		main.setPsdbm(receiveAddressVo.getId());
		main.setPsdmc(receiveAddressVo.getDeptName());
		String province = receiveAddressVo.getProvince();
		String city = receiveAddressVo.getCity();
		String area = receiveAddressVo.getArea();
		String address = receiveAddressVo.getAddress();
		String psdz = province + city + area + address;
		if("市辖区".equals(city) || "县".equals(city)){
			psdz = province + area + address;
		}
		main.setPsdz(psdz);
		main.setLxrxm(receiveAddressVo.getLxr());
		main.setLxdh(receiveAddressVo.getLxrPhone());
		main.setYzbm(receiveAddressVo.getPostCode());
		main.setBzxx(receiveAddressVo.getAreaCode());
		xml.setReqHead(HostUtils.getReqHead(YgTimeUtil.getStampA()));
		xml.setMain(main);
		String xmlData = JAXBUtil.toXML(xml, main.getClass());
		String sendRecv = ygUtils.sendRecv(SMPType.YY101, xmlData);
		return sendRecv;
	}

	@Override
	public ReceiveAddressVo getAddresByDeptId(String deptId) throws Exception {
		return receiveAddressDao.getAddresByDeptId(deptId);
	}
}
