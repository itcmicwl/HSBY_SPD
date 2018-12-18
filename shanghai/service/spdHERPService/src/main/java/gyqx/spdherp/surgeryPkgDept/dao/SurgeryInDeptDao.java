package gyqx.spdherp.surgeryPkgDept.dao;

import gyqx.spdherp.po.*;
import gyqx.spdherp.surgeryPkgDept.dao.mapper.ISurgeryInDeptMapper;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurInfoVo;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurgeryBatchVo;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurgeryListVo;
import gyqx.spdherp.surgeryPkgDept.vo.InDeptSurgeryUniqueVo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class SurgeryInDeptDao {
    @Resource
    private ISurgeryInDeptMapper mapper;
    public InDeptSurInfoVo getSurgeryInfo(InDeptSurInfoVo qBean){
       return mapper.findSurgeryInfo(qBean);
    }
    public List<InDeptSurgeryListVo> findSurgeryList(InDeptSurInfoVo qBean){
        return mapper.findSurgeryList(qBean);
    }
    public List<InDeptSurgeryBatchVo> findSurgeryBatch(InDeptSurgeryListVo qBean){
        return mapper.findSurgeryBatch(qBean);
    }
    public List<InDeptSurgeryUniqueVo> findSurgeryUnique(InDeptSurgeryListVo qBean){
        return mapper.findSurgeryUnique(qBean);
    }
    public int insert(List<InStockList> inStockLists,List<InStockBatch> inStockBatches,List<InStockUniqueCode> inStockUniqueCodeList){
        int iList = 0;
        int iBatch = 0;
        int iUniue = 0;
        if(inStockLists.size()>0){
            iList = mapper.insertByList(inStockLists);
        }
        if(inStockBatches.size()>0){
            iBatch = mapper.insertByBatch(inStockBatches);
        }
        if(inStockUniqueCodeList.size()>0){
            iUniue = mapper.insertByUnique(inStockUniqueCodeList);
        }
        if(iList==inStockLists.size()&&iBatch==inStockBatches.size()&&iUniue==inStockUniqueCodeList.size()){
            return 0;
        }else {
            return -1;
        }
    }

    public List<OutStockBatch> listBatchByBillId(String billId){
        return mapper.listBatchByBillId(billId);
    }
    public List<OutStockUniqueCode> listUniByBillId(String billId){
        return mapper.listUniByBillId(billId);
    }
}
