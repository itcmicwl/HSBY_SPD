//主表
export const DEPTBUY_MAIN_STATUS = {
    "10": { text: '已保存(护士)', index: 1 },
    "11": { text: '已审核(护士长)', index: 2 },
    "20": { text: '已审核(设备科)', index: 3 },
    "30": { text: '处理中', index: 4 },
    "40": { text: '已处理', index: 5 }
  
};
//子表
export const DEPTBUY_SUB_STATUS = {
    "10": { text: '待处理', index: 1 },
    "20": { text: '已驳回', index: 2 },
    "30": { text: '不采购', index: 3 },
    "40": { text: '部分配送', index: 4 },
    "50": { text: '全部配送', index: 5 },
    "60": { text: '已采购', index: 6 }
};
//主表 部门审核  弃用  合并到主表中
export const DEPTBUY_MAIN_DEPTAUDIT_STATUS = {
    "10": { text: '暂存', index: 1 },
    "20": { text: '已提交', index: 2 },
    "30": { text: '已审核', index: 3 }

};

