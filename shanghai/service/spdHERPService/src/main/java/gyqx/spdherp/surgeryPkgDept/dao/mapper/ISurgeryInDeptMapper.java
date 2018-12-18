package gyqx.spdherp.surgeryPkgDept.dao.mapper;

import gyqx.spdherp.po.*;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurInfoVo;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurgeryBatchVo;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurgeryListVo;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurgeryUniqueVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISurgeryInDeptMapper {
    InDeptSurInfoVo findSurgeryInfo(InDeptSurInfoVo qBean);
    List<InDeptSurgeryListVo> findSurgeryList(InDeptSurInfoVo qBean);
    List<InDeptSurgeryBatchVo> findSurgeryBatch(InDeptSurgeryListVo qBean);
    List<InDeptSurgeryUniqueVo> findSurgeryUnique(InDeptSurgeryListVo qBean);
    int insertByList(List<InStockList> inStockLists);
    int insertByBatch(List<InStockBatch> inStockBatches);
    int insertByUnique(List<InStockUniqueCode> inStockUniqueCodeList);
    List<OutStockBatch> listBatchByBillId(@Param("billId")String billId);
    List<OutStockUniqueCode> listUniByBillId(@Param("billId")String billId);
}
