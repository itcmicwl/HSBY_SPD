package gyqx.ws.yg.common;

/**
 * 代码字典
 */
public class Constance {
	
	/** 操作类型 */
	public interface OP_11{
		/** 新增 */
		Integer OP_ADD = 1;
		/** 作废 */
		Integer OP_CANCEL = 3;
	}
	
	/** 采购单类型 */
	public interface PUR_BILL_12{
		/** 医院自行采购单 */
		Integer YYZXCGD = 1;
		/** 托管药库采购单 */
		Integer TGYKCGD = 2;
	}
	
	/** 采购类型 */
	public interface PUR_13{
		/** 招标采购 */
		Integer ZHBCG = 1;
		/** 带量采购 */
		Integer DLCG = 2;
		/** 挂网采购 */
		Integer GWCG = 3;
		/** 其他 */
		Integer QT = 9;
	}
    
	/** 配送要求 */
	public interface DISTR_14{
		/** 按单配送 */
		Integer ADPS = 1;
		/** 按需配送 */
		Integer AXPS = 2;
	}
	
	/** 退货类型 */
	public interface RETURN_15{
		/** 正常退货 */
		Integer ZHCHTH = 1;
		/** 补差价退货 */
		Integer BCHJTH = 2;
	}

	/** 配送验收类型 */
	public interface DISTR_16{
		/** 预验收 */
		Integer PRE = 1;
		/** 实验收 */
		Integer ACTUAL = 2;
	}
	
	/** 单据填写方 */
	public interface BILL_17{
		/** 采购方填写 */
		Integer CGF = 1;
		/** 销售方代填 */
		Integer XSHF = 2;
	}
    
	/** 配送单状态 */
	public interface DISTR_STATE_18{
		/** 待确认 */
		String DQR = "00";
		/** 作废 */
		String ZF = "01";
		/** 已确认，未验收 */
		String QRWYSH = "10";
	}
    
	/** 采购明细状态 */
	public interface PUR_MX_19{
		/** 待提交 */
		String DTJ = "00";
		/** 已作废 */
		String YZF = "01";
		/** 待审核 */
		String DSHH = "10";
		/** 审核不通过 */
		String SHHBTG = "11";
		/** 待企业接受确认 */
		String DQYQR= "20";
		/** 企业已接收确认配送 */
		String QRPS = "21";
		/** 企业已接收确认不配送 */
		String QRBPS = "22";
		/** 企业已配送完成 */
		String PSWCH = "23";
	}
    
	/** 企业库存 */
	public interface STOCK_110{
		/** 充足 */
		String CHZ = "01";
		/** 有货，限量 */
		String XL = "02";
		/** 有货，少量 */
		String SL = "03";
		/** 暂无货 */
		String ZWH = "04";
		/** 不予披露 */
		String BYPL = "05";
	}
    
	/** 发票状态 */
	public interface INVOICE_STATE_111{
		/** 待确认 */
		String DQR = "00";
		/** 已作废 */
		String YZF = "01";
		/** 未验收 */
		String WYSH = "10";
		/** 已验收 */
		String YYSH = "20";
		/** 已拒收 */
		String YJF = "21";
		/** 已支付 */
		String YZHF = "30";
	}
    
	/** 配送明细状态 */
	public interface DISTR_MX_112{
		/** 待确认 */
		String DQR = "00";
		/** 已作废 */
		String YZF = "01";
		/** 已提交未验收 */
		String YTJWYSH = "10";
		/** 验收中 */
		String YSHZH = "20";
		/** 验收入库完成 */
		String YSHRKWCH = "30";
	}
    
	/** 退货明细状态 */
	public interface RETURN_MX_113{
		/** 待确认 */
		String DQR = "00";
		/** 已作废 */
		String YZF = "01";
		/** 已确认待企业接受 */
		String QYQR = "10";
		/** 企业已确认退货处理 */
		String QYQRTH = "20";
		/** 企业已确认不予处理 */
		String QYQRBCHL = "21";
	}
   
	/** 证件类型 */
	public interface CERT_114{
		/** 组织机构代码证 */
		String ONE = "01";
		/** 营业执照 */
		String TWO = "02";
		/** 经营许可证 */
		String THREE = "03";
		/** 生产许可证 */
		String FORE = "04";
	}
	
	/** 配送明细条码类型 */
	public interface BARCODE_115{
		/** GS1条码 */
		String GS1 = "01";
		/** HIBC条码 */
		String HIBC = "02";
		/** 其他 */
		String QT = "99";
	}
	
	/** 发票验收结果 */
	public interface INVOICE_116{
		/** 验收通过 */
		String PASS = "1";
		/** 验收不通过 */
		String REJECT = "0";
	}
	
	/** 采购单状态 */
	public interface PUR_STATE_117{
		/** 待提交 */
		String DTJ = "00";
		/** 已作废 */
		String YZF = "01";
		/** 已提交 */
		String YTJ = "20";
	}
	
	/** 退货单状态 */
	public interface RETURN_STATE_118{
		/** 待确认 */
		String DQR = "00";
		/** 已作废 */
		String YZF = "01";
		/** 已确认  */
		String YQR = "10";
	}
}
