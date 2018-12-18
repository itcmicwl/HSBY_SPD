package ${groupPackageName}.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import common.db.SimpleDao;
import common.db.query.QueryInfo;

import ${groupPackageName}.dao.mapper.I${classNameQR}Mapper;
import ${groupPackageName}.vo.${classNameQ};
import ${groupPackageName}.vo.${classNameQR};

<#list imports as item>
import ${item.packageName}.${item.className};
</#list>

@Repository
public class ${classNameQR}Dao 
{	
	@Resource
	private I${classNameQR}Mapper mapper;
	
	public List<${classNameQR}> list(${classNameQ} qbean) throws Exception{		

		return mapper.list(qbean);
	}
}


