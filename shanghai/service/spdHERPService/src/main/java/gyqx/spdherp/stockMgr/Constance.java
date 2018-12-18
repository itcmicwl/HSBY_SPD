package gyqx.spdherp.stockMgr;

/**
 * Created by moonb on 2017/8/21.
 */
public class Constance {
    //入库类型
    public static final String INSTOCK_KIND_ORIGINAL = "0";         //手工入库
    public static final String INSTOCK_KIND_SEND = "1";             //采购入库
    public static final String INSTOCK_KIND_DEPTAPPLY = "2";        //请领入库
    public static final String INSTOCK_KIND_MOVE = "3";             //调拨入库
    public static final String INSTOCK_KIND_RETURNS = "4";          //退货入库
    public static final String INSTOCK_KIND_RETURNWITHMOEY ="5";    //计费退货入库

    //
    public static final int INSTOCK_TYPE_TRUE = 10;              //实物
    public static final int INSTOCK_TYPE_FAKE = 20;         //虚拟

    //入库单状态
    public static final Integer INSTOCK_STATUS_SAVE=10;         //保存
    public static final Integer INSTOCK_STATUS_SUBMIT=20;       //提交
    public static final Integer INSTOCK_STATUS_AUDIT=30;        //审核
    public static final Integer INSTOCK_STATUS_CHARGE=40;       //记账
    //出库单明细表状态
    public static final Integer IN_STOCK_LIST_STATUS_SVAE=0;            //默认状态
    public static final Integer IN_STOCK_LIST_STATUS_PART_SETTLE=2;     //部分结算
    public static final Integer IN_STOCK_LIST_STATUS_SETTLE=10;         //已结算
    //条码管理类型
    public static final Integer INSTOCKLIST_BARCODE_SYSTEM = 0;                //系统分配唯一码
    public static final Integer INSTOCKLIST_BARCODE_PORVE=1;                    //原厂
    public static final Integer INSTOCKLIST_BARCODE_HOS=2;                      //医院自定义
    public static final Integer INSTOCKLIST_BARCODE_NONE=3;                     //非唯一码管理
    //结算单状态
    public static final Integer SETTLE_MAIN_STATUS_SAVE=10; // 已保存
    public static final Integer SETTLE_MAIN_STATUS_SUBMIT=20;// 已审核
    public static final Integer SETTLE_MAIN_STATUS_UP=30; // 已上传
    public static final Integer SETTLE_MAIN_STATUS_OK=40; // 已确认
    
    public static final Integer SETTLE_TYPE_OUT = 10;       //出库结算
    public static final Integer SETTLE_TYPE_IN = 20;         //入库结算
    
}
