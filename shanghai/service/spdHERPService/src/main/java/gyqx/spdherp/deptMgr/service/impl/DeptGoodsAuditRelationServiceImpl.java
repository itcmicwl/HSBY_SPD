package gyqx.spdherp.deptMgr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import gyqx.spdherp.deptMgr.dao.DeptGoodsAuditRelationDao;
import gyqx.spdherp.deptMgr.dao.ReceiveAddressDao;
import gyqx.spdherp.deptMgr.service.IDeptGoodsAuditRelationService;
import gyqx.spdherp.po.DeptGoodsAuditRelation;

@Service
public class DeptGoodsAuditRelationServiceImpl implements IDeptGoodsAuditRelationService{
	@Resource
	private  DeptGoodsAuditRelationDao deptGoodsAuditRelationDao;
	
	@Override
	public void insertDeptGoodsAuditRelation(List<DeptGoodsAuditRelation> deptGoodsAuditRelations) {
		// TODO Auto-generated method stub
		//通过审核人ID获取所有相关列
		List<DeptGoodsAuditRelation> list= deptGoodsAuditRelationDao.selectByAuditer(deptGoodsAuditRelations.get(0).getAuditer());
		for (DeptGoodsAuditRelation deptGoodsAuditRelation : deptGoodsAuditRelations) {
			if(!StringUtils.isNotEmpty(deptGoodsAuditRelation.getDeptId()))
			break;
			int i=0;
			for(DeptGoodsAuditRelation temp:list){
				//找到对应  修改时间和版本
				if(temp.getHosId().equals(deptGoodsAuditRelation.getHosId()) && 
				   temp.getDeptId().equals(deptGoodsAuditRelation.getDeptId())){
					//设置其版本
					deptGoodsAuditRelation.setVersion(temp.getVersion());
					//修改
					deptGoodsAuditRelationDao.updateDeptGoodsAuditRelation(deptGoodsAuditRelation);
					//移除
					list.remove(i);
					//设置为空
					deptGoodsAuditRelation=null;
					break;
				 }
				i++;
			}
			//判断是否循环到最后一个元素
			if(deptGoodsAuditRelation!=null&&(i==list.size()-1 || i==0)){
				//判断科室ID是否为空
				if(deptGoodsAuditRelation.getDeptId()!=null && !deptGoodsAuditRelation.getDeptId().trim().equals("")
				&&	deptGoodsAuditRelation.getHosId()!=null && !deptGoodsAuditRelation.getHosId().trim().equals("")){
					//插入元素
				deptGoodsAuditRelation.setId(UUID.randomUUID().toString());
				deptGoodsAuditRelationDao.insertDeptGoodsAuditRelation(deptGoodsAuditRelation);
				}
				}	
		}
		for (DeptGoodsAuditRelation temp : list) {
			//根据主键进行删除
			deptGoodsAuditRelationDao.deleteDeptGoodsAuditRelation(temp.getId());
		}
	}

	@Override
	public List<DeptGoodsAuditRelation> selectByAuditer(String id) {
		// TODO Auto-generated method stub
		return deptGoodsAuditRelationDao.selectByAuditer(id);
	}
	

}
