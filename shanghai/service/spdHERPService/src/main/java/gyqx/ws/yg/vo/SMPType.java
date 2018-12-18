package gyqx.ws.yg.vo;

/**
 * Created by moonb on 2017/11/21.
 */
public enum SMPType {
    //信息上报类交易
    YY101("YY101"),//配送点传报
    //UPLOAD_STOCK("YY102"),//库存传报
    YY131("YY131"),//配送验收确认
    YY132("YY132"),//发票验收确认
    YY133("YY133"),//发票支付确认
    //两次握手类交易
    YY111("YY111"),//采购单填报
    YY112("YY112"),//采购单确认
    YY113("YY113"),//退货单填报
    YY114("YY114"),//退货单确认
    //业务查询类交易
    YY151("YY151"),//采购明细信息获取
    YY152("YY152"),//退货明细获取
    YY153("YY153"),//按配送明细获取
    YY154("YY154"),//配送单获取
    YY155("YY155"),//配送明细获取
    YY156("YY156"),//按发票明细获取
    YY157("YY157"),//发票信息获取
    YY158("YY158"),//发票明细获取
    YY159("YY159"),//采购明细状态
    YY160("YY160"),//发票状态获取
    YY161("YY161"),//配送单状态获取
    YY162("YY162"),//退货明细状态
    YY163("YY163"),//耗材单品UDI信息获取
    YY164("YY164"),//企业信息获取
    YY165("YY165"),//企业资证信息获取
    YY166("YY166");//企业授权信息获取

    private final String value;
    public String getValue() {
        return value;
    }
    SMPType(String value){
        this.value = value;
    }
    
//    { value: 'YY111', label: '采购单填报' },
//    { value: 'YY112', label: '采购单确认' },
//    { value: 'YY113', label: '退货单填报' },
//    { value: 'YY114', label: '退货单确认' },
//    { value: 'YY131', label: '配送验收确认' },
//    { value: 'YY132', label: '发票验收确认' },
//    { value: 'YY133', label: '发票支付确认' },
//    { value: 'YY151', label: '采购明细信息获取' },
//    { value: 'YY152', label: '退货明细获取' },
//    { value: 'YY153', label: '按配送明细获取' },
//    { value: 'YY154', label: '配送单获取' },
//    { value: 'YY155', label: '配送明细获取' },
//    { value: 'YY156', label: '按发票明细获取' },
//    { value: 'YY157', label: '发票信息获取' },
//    { value: 'YY158', label: '发票明细获取' },
//    { value: 'YY159', label: '采购明细状态' },
//    { value: 'YY160', label: '发票状态获取' },
//    { value: 'YY161', label: '配送单状态获取' },
//    { value: 'YY162', label: '退货明细状态' },
//    { value: 'YY163', label: '耗材单品UDI信息获取' },
//    { value: 'YY164', label: '企业信息获取' },
//    { value: 'YY165', label: '企业资证信息获取' },
//    { value: 'YY166'  label: '企业授权信息获取' },
}
