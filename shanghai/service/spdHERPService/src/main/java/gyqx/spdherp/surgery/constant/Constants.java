package gyqx.spdherp.surgery.constant;

public class Constants {
    /// 手术包定义状态
    // 启用
    public static final int SUR_DEF_FLAG_NORMAL = 1;
    // 停用
    public static final int SUR_DEF_FLAG_DEL = 0;

    // 是否需要消毒 -- 是
    public static final int SUR_NEED_STERILIZE_YES = 1;
    // 是否需要消毒 -- 否
    public static final int SUR_NEED_STERILIZE_NO = 1;

    /// 手术包信息主表状态：
    // 手术包部分消毒
    public static final int SURGERY_PKG_STATUS_STERILIZATION_FIRST_PART = 30;
    // 手术包无需消毒
    public static final int SURGERY_PKG_STATUS_STERILIZATION_FIRST_NONEED = 31;
    // 手术包全部消毒
    public static final int SURGERY_PKG_STATUS_STERILIZATION_FIRST_ALL = 32;
    // 手术包退还部分消毒
    public static final int SURGERY_PKG_STATUS_STERILIZATION_SEC_PART = 50;
    // 手术包退还无需消毒
    public static final int SURGERY_PKG_STATUS_STERILIZATION_SEC_NONEED = 51;
    // 手术包退还全部消毒
    public static final int SURGERY_PKG_STATUS_STERILIZATION_SEC_ALL = 52;


    /// 包日志类型
    // 手术包
    public static final int PKG_KIND_SURGERY = 0;
    // 定数包
    public static final int PKG_KIND_DS = 1;

    public static final int SUR_PKG_STATUS_JIANHUO = 0;            //捡货
    public static final int SUR_PKG_STATUS_DABAO = 5;            //打包
    public static final int SUR_PKG_STATUS_QGCK = 10;             //请购出库
    public static final int SUR_PKG_STATUS_QGRK = 20;             //请购入库
    public static final int SUR_PKG_STATUS_KSCK = 40;            //科室出库
    public static final int SUR_PKG_STATUS_OUTBFXD = 30;         //部分消毒
    public static final int SUR_PKG_STATUS_OUTQBXD = 32;         //全部消毒
    public static final int SUR_PKG_STATUS_BRXH = 41;            //病人消耗
    public static final int SUR_PKG_STATUS_INBFXD = 50;         //部分消毒
    public static final int SUR_PKG_STATUS_INQBXD = 52;         //全部消毒
    public static final int SUR_PKG_STATUS_THRK = 60;            //退库
    public static final int SUR_PKG_STATUS_WC = 70;               //完成

    public static final int SUR_PKG_DETAIL_STATUS_SOME = 10;            //未完成
    public static final int SUR_PKG_DETAIL_STATUS_ALL = 20;            //完成

    public static final String HOS_TAKING_KIND_SUR_PKG = "90";
}
