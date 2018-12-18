export const PURCHASE_STATUS = {
    "10": { text: '已保存', index: 1 },
    "20": { text: '已提交', index: 2 },
    "30": { text: '部分确认', index: 3 },
    "40": { text: '全部确认', index: 4 },
    "50": { text: '已配送', index: 5 },
    "60": { text: '已完成', index: 6 }
};
export const PURCONFIRM_STATUS = {
    "10": { text: '待提交', index: 1 },
    "20": { text: '待配送', index: 2 },
    "30": { text: '部分配送', index: 3 },
    "40": { text: '全部配送', index: 4 }

};
export const DISTRBILL_STATUS = {
    "10": { text: '待提交', index: 1 },
    "20": { text: '已配送', index: 2 },
    "30": { text: '部分收货', index: 3 },
    "40": { text: '全部收货', index: 4 }
};
export const PURCHASE_KIND = {
    "0": { text: '手工采购', index: 1 },
    "1": { text: '请购采购', index: 2 }
};
export const INSTOCK_KIND={
    "0": { text: '手工入库', index: 1 },
    "1": { text: '采购入库', index: 2 },
    "2": { text: '请购入库', index: 3 },
    "3": { text: '调拨入库', index: 3 },
    "4": { text: '退货入库', index: 4 },
    "5": { text: '计费退货', index: 5 },
}
export const INSTOCK_STATUS={
    "0": { text: '未知状态', index: 0 },
    "00": { text: '已作废', index: 1 },
    "10": { text: '已保存', index: 1 },
    "20": { text: '已提交', index: 2 },
    "30": { text: '已审核', index: 2 },
    "40": { text: '已记账', index: 2 }
}
export const BARCODEKIND={
    CGRK:10,                    //采购入库
    QGRK:20,                    //请购入库
    DBRK:30,                    //调拨入库
    THRK:40,                    //退货入库
    SGRK:50,                    //手工入库
    JFTH:60,                    //计费退货
    BRXH:70,                    //病人消耗
    SSB:60                      //手术包打包
}
export const BARCOD_RES_TYPE={
    ZBM:10,                    //自编唯一码
    GS1:20,                     //gs1码
    DSB:30,                      //定数包码
    SSB:40,                     //手术包码
}


