package gyqx.spdhdi.myInfos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import gyqx.spdhdi.myInfos.dao.ProvRegGoodsInfoQRDao;
import gyqx.spdhdi.myInfos.service.IProvRegGoodsInfoQRService;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQ;
import gyqx.spdhdi.myInfos.vo.ProvRegGoodsInfoQR;

@Service
public class ProvRegGoodsInfoQRService implements IProvRegGoodsInfoQRService {
	
	@Resource
	private ProvRegGoodsInfoQRDao  dao;	
	
	public QueryResult<ProvRegGoodsInfoQR> listByPage(QueryInfo<ProvRegGoodsInfoQ> queryInfo) throws Exception{		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


