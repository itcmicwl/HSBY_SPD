export const PURCHASE_STATUS = {
    "10": { text: '已保存', index: 1 },
    "20": { text: '已提交', index: 2 },
    "30": { text: '部分确认', index: 3 },
    "40": { text: '全部确认', index: 4 },
    "50": { text: '已配送', index: 5 },
    "60": { text: '已完成', index: 6 },
    "70": { text: '关闭', index: 7 }
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
export const YG_SENDBILL_DETAIL_STATUS = {  //配送单明细状态
    "00": { text: '待确认', index: 1 },
    "01": { text: '已作废', index: 2 },
    "10": { text: '未验收', index: 3 },
    "20": { text: '未验收', index: 4 },
    "25": { text: '验收中', index: 4 },
    "30": { text: '已验收', index: 4 },
};
export const YG_SENDBILLL_STATUS = {        //配送单状态
    "00": { text: '待确认', index: 1 },
    "01": { text: '已作废', index: 2 },
    "10": { text: '未验收', index: 3 },
    "20": { text: '未验收', index: 5 },   
    "25": { text: '验收中', index: 4 },
    "30": { text: '已验收', index: 6 }      
};
export const YG_INV_STATUS = {              //发票状态
    "00": { text: '待确认', index: 1 },
    "01": { text: '已作废', index: 2 },
    "10": { text: '未验收', index: 3 },
    "20": { text: '已验收', index: 4 },
    "21": { text: '已拒收', index: 5 },
    "30": { text: '已支付', index: 6 }   
};
export const YG_PUR_KIND = {              //采购类型
    "1": { text: '招标采购', index: 1 },
    "2": { text: '带量采购', index: 2 },
    "3": { text: '挂网采购', index: 3 },
    "9": { text: '其他', index: 4 }
};
