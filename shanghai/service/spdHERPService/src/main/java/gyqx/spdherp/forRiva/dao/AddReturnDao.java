package gyqx.spdherp.forRiva.dao;

import gyqx.spdherp.forRiva.dao.mapper.IAddReturnMapper;
import gyqx.spdherp.forRiva.dao.mapper.ICallForRivaMapper;
import gyqx.spdherp.stockout.vo.SickerUserVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AddReturnDao
{
	@Resource
	private IAddReturnMapper iAddReturnMapper;

	public SickerUserVo getSickerUseResult(String id){

		return iAddReturnMapper.getSickerUseById(id);
	}

	public  int stockpilebatch(SickerUserVo sickerUserVo){
		return iAddReturnMapper.stockpilebatch(sickerUserVo);
	}


}


