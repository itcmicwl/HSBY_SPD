package gyqx.spdherp.applyMgr;

public class Constance {
	//主表
	public static final Integer DEPTAPPLAY_MAIN_STATUS_CANCEL   = 0;    //作废
    public static final Integer DEPTAPPLAY_MAIN_STATUS_SAVED    = 10;    //已保存(护士)
    public static final Integer DEPTAPPLAY_MAIN_STATUS_AUDIT    = 11;    //已审核(护士长)
    public static final Integer DEPTAPPLAY_MAIN_STATUS_COMMITED = 20;    //已审核(设备科)
    public static final Integer DEPTAPPLAY_MAIN_STATUS_HANDLING = 30;    //处理中
    public static final Integer DEPTAPPLAY_MAIN_STATUS_HANDLED  = 40;    //已处理
    

    //明细
    public static final Integer DEPTAPPLAY_SUB_STATUS_PENDING  =  10;       //待处理
    public static final Integer DEPTAPPLAY_SUB_STATUS_REJECTED = 20;        //已驳回
    public static final Integer DEPTAPPLAY_SUB_STATUS_NOTPURCHASE = 30;     //不采购
    public static final Integer DEPTAPPLAY_SUB_STATUS_PURCHAED = 60;        //已采购
    public static final Integer DEPTAPPLAY_SUB_STATUS_PARTDISTRED = 40;     //部分配送  
    public static final Integer DEPTAPPLAY_SUB_STATUS_ALLDISTRED = 50;      //全部配送
    
    //送货方式
    
    //资金来源
    public static final String DEPTAPPLAY_SUB_FUND_TYPE_SELF_FINANCING = "10";    //自筹经费
    public static final String DEPTAPPLAY_SUB_FUND_TYPE_FINANCIAL_ADJ  = "20";    //财政捐款
    public static final String DEPTAPPLAY_SUB_FUND_TYPE_DONATION       = "30";    //捐赠
    public static final String DEPTAPPLAY_SUB_FUND_TYPE_RESEARCH_FUND  = "40";    //已处理
    
    //审核类型
    public static final String DEPTAPPLAY_AUDIT_TYPE_DEPARTMENT_AUDIT = "10"; //科室审核
    public static final String DEPTAPPLAY_AUDIT_TYPE_PURCHASE_AUDIT = "20"; //请购审核

    //采购模式
    public static final Integer DEPTAPPLAY_MAIN_PUR_MODE_FAKE  =  10;       //实物请购
    public static final Integer DEPTAPPLAY_MAIN_PUR_MODE_INVAL  =  20;       //虚拟请购
    public static final Integer DEPTAPPLAY_MAIN_PUR_MODE_SUPPLY  =  30;       //办公用品
}
