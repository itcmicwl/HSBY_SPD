package ${groupPackageName}.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import common.utils.PageUtils;
<#list imports as item>
import ${item.packageName}.${item.className};
</#list>

import ${groupPackageName}.dao.${classNameQR}Dao;
import ${groupPackageName}.service.I${classNameQR}Service;
import ${groupPackageName}.vo.${classNameQ};
import ${groupPackageName}.vo.${classNameQR};

@Service
public class ${classNameQR}Service implements I${classNameQR}Service {
	
	@Resource
	private ${classNameQR}Dao  dao;	
	
	public QueryResult<${classNameQR}> listByPage(QueryInfo<${classNameQ}> queryInfo) throws Exception{		
		
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

}


