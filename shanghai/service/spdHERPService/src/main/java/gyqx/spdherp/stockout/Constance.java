package gyqx.spdherp.stockout;

/**
 * @Auther Liangwu
 * @Date 17-9-11 下午4:51
 */
public class Constance {
    // 出库单状态
    // 保存
    public static final Integer OUTSTOCKBILL_STATUS_SAVE = 90;
    // 提交
    public static final Integer OUTSTOCKBILL_STATUS_SUBMIT = 10;
    // 审核
    public static final Integer OUTSTOCKBILL_STATUS_CHECKED = 20;
    // 记帐
    public static final Integer OUTSTOCKBILL_STATUS_ACCOUNT = 30;
    // 作废
    public static final Integer OUTSTOCKBILL_STATUS_ABOLISH = 40;
    // 驳回
    public static final Integer OUTSTOCKBILL_STATUS_REJECT = 50;
    // 入库
    public static final Integer OUTSTOCKBILL_STATUS_INSTOCK = 60;
    // 部分入库
    public static final Integer OUTSTOCKBILL_STATUS_PART_INSTOCK = 69;
    //部分消耗
    public static final Integer OUTSTOCKBILL_HALFUSE_STATUS = 61;
    //全部消耗		
    public static final Integer OUTSTOCKBILL_ALLUSE_STATUS = 62;
    // 结算
    public static final Integer OUTSTOCKBILL_STATUS_SETTLEMENT = 70;

    // 出库单Type
    // 实采
    public static final Integer OUTSTOCKBILL_TYPE_REAL = 10;
    // 虚采
    public static final Integer OUTSTOCKBILL_TYPE_FAKE = 20;


    // 出库类型
    // 请购出库
    public static final Integer OUTSTOCK_KIND_REQUEST = 40;
    // 调拨出库
    public static final Integer OUTSTOCK_KIND_TRANSFER = 10;
    // 手工出库
    public static final Integer OUTSTOCK_KIND_MANUAL = 20;
    // 科室出库消耗
    public static final Integer OUTSTOCK_KIND_CONSUME = 30;
    // 退货出库
    public static final Integer OUTSTOCK_KIND_RETURN = 50;
    // 定数包消耗出库
    public static final Integer OUTSTOCK_KIND_PCKAGE = 60;
    // 手术包领用登记
    public static final Integer OUTSTOCK_KIND_SURGERY = 70;

    //出库单明细表状态
    public static final Integer OUTSTOCK_LIST_STATUS_SVAE = 0;            //默认状态
    public static final Integer OUTSTOCK_LIST_STATUS_PART_SETTLE = 2;     //部分结算
    public static final Integer OUTSTOCK_LIST_STATUS_SETTLE = 10;         //已结算

    //消耗单状态
    public static final Integer SICKER_STATUS_SAVE = 10;                  //保存
    public static final Integer SICKER_STATUS_SUMIT = 20;                 //提交
    public static final Integer SICKER_STATUS_CHECKED = 30;               //记账

    //是否核销成功
    public static final Integer BEFORE_CANCEL = 1;     //未核销
    public static final Integer AFTER_CANCEL = 2;      //已核销成功

    public static final Integer UNIQUE_KIND_UNIQUE = 3;  // 唯一码管理

    /**
     * 1为虚库出库补实库出库
     */
    public static final int FILL_OUT_FAKE = 1;
    /**
     * -1为补计费退货冲红单据
     */
    public static final int FILL_OUT_REAL = -1;

    //唯一码管理策略
    public static final String UNIQUE_SYSTEM = "0";                   //系统分配唯一码
    public static final String UNIQUE_PORVE = "1";                    //原厂
    public static final String UNIQUE_HOS = "2";                      //医院自定义
    public static final String UNIQUE_NONE = "3";                     //非唯一码管理

    // 退货类型
    /**
     * 验收退货
     */
    public static final int RETURN_KIND_CHECK = 10;
    /**
     * 库房退货
     */
    public static final int RETURN_KIND_STOCK = 20;

    // 退货状态
    /**
     * 作废
     */
    public static final int RETURN_STATUS_INVALID = 1;
    /**
     * 初始
     */
    public static final int RETURN_STATUS_INIT = 0;
    /**
     * 已审核
     */
    public static final int RETURN_STATUS_VERIFIED = 20;
}
