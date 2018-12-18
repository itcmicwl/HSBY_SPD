package ${groupPackageName}.dao.mapper;

<#list imports as item>
import ${item.packageName}.${item.className};
</#list>

import ${groupPackageName}.vo.${classNameQ};
import ${groupPackageName}.vo.${classNameQR};
 
public interface I${classNameQR}Mapper {

	List<${classNameQR}> list(${classNameQ} queryInfo) ;
}

