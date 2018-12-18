package gyqx.spdhdi.orderMgr;

public class Constance {
	public static final Integer PURCHASE_KIND_HANDEL = 0;	//手工采购
	/**
	 * 请购采购
	 */
	public static final Integer PURCHASE_KIND_APPLY = 1;
	/**
	 * 自动补货采购
	 */
	public static final Integer PURCHASE_KIND_AUTO = 2;
	//-----------------采购单主表状态 start-----------------------------------------
	public static final Integer PURCHASE_STATUS_ERR = -10; 			//错误
	public static final Integer PURCHASE_STATUS_UNAUDIT = 4; 			//根据库存生成采购单提交
	public static final Integer PURCHASE_STATUS_CREATEBYSTOC = 5; 			//根据库存生成采购单提交
	public static final Integer PURCHASE_STATUS_SAVE = 10; 			//保存
	public static final Integer PURCHASE_STATUS_SUBMIT = 20; 		//提交
	public static final Integer PURCHASE_STATUS_SOMECONFIRM = 30; 	//部分确认
	public static final Integer PURCHASE_STATUS_ALLCONFIRM = 40;	//全部确认
	public static final Integer PURCHASE_STATUS_SOMEREC = 50;		//部分收货
	public static final Integer PURCHASE_STATUS_ALLREC = 60;		//全部收货
	public static final Integer PURCHASE_STATUS_CLOSE = 70;		//关闭
	//-----------------采购单主表状态  end-----------------------------------------
	//-----------------采购单明细表状态 start-----------------------------------------
	public static final Integer PURCHASE_LIST_STATUS_SAVE = 0;//未确认
	public static final Integer PURCHASE_LIST_STATUS_CONFIRM = 10;//已确认
	public static final Integer PURCHASE_LIST_STATUS_RECEIVE = 20;//已收货
	//-----------------采购确认单主表状态  end-----------------------------------------
	public static final Integer PURCONFIRM_STATUS_SAVE = 10; 
	public static final Integer PURCONFIRM_STATUS_SUBMIT = 20;
	public static final Integer PURCONFIRM_STATUS_SOMESEND = 30; 
	public static final Integer PURCONFIRM_STATUS_ALLSEND = 40; 
	
	public static final Integer PURCONFIRM_LIST_STATUS_NOSEND = 0; //未发货
	public static final Integer PURCONFIRM_LIST_STATUS_SOMESEND = 10; //部分发货
	public static final Integer PURCONFIRM_LIST_STATUS_ALLSEND = 20; //全部发货
	
	public static final Integer DISTRBILL_STATUS_SAVE = 10;			//保存
	public static final Integer DISTRBILL_STATUS_SUBMIT = 20; 		//提交(未收货)
	public static final Integer DISTRBILL_STATUS_SOMEREC = 30; 		//部分收货
	public static final Integer DISTRBILL_STATUS_ALLREC = 40; 		//全部收货
	//-------------发货单明细状态-------------------------------
	public static final Integer DISTRBILL_DETAIL_STATUS_SAVE = 0;			//保存
	public static final Integer DISTRBILL_DETAIL_STATUS_SOMEREC = 10; 		//部分收货
	public static final Integer DISTRBILL_DETAIL_STATUS_ALLREC = 20; 		//明细全部收货
	
	public static final Integer RECEIVEBILL_STATUS_SAVE = 10;
	public static final Integer RECEIVEBILL_STATUS_SUBMIT = 20; 
	
	//--------退货单主表状态
	public static final Integer RETRUNBILL_MAIN_STATUS_SAVE = 10;			//保存
	public static final Integer RETRUNBILL_MAIN_STATUS_SUBMIT = 20;			//提交
	public static final Integer RETRUNBILL_MAIN_STATUS_CONFIRM = 30;		//已全部确认	
	
	//--------退货单子表状态
	public static final Integer RETRUNBILL_SUB_STATUS_NOR = 1;			//初始 待确认
	public static final Integer RETRUNBILL_SUB_STATUS_DOWN = 0;			// 已确认
	

	public static final String SPDID="sys.name";
	
	//-------------------------采购形式---------------------------------------
	public static final String IN_STOCK_TYPE_MATERIAL_KEY="10";
	public static final String IN_STOCK_TYPE_VIRTUAL_KEY="20";
	
	public static final String IN_STOCK_TYPE_MATERIAL_NAME="实物采购";
	public static final String IN_STOCK_TYPE_VIRTUAL_NAME="虚物采购";
	//----------------------------------------------------------------
	
	//-------------------------资金来源---------------------------------------
	public static final String FUND_TYPE_DEFAULT_VALUE="10";
	//----------------------------------------------------------------
	//设备科配置
	public static final String SYS_HOS_SBK="equipDepartmentOrgId";
	public static final String SYS_AUTO_FILLER = "sys_auto_filler";
	public static final String SYS_CUR_HOS="hospital-id";
}
