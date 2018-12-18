package gyqx.spdherp.surgeryPkgDept.vo;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;
@Data
public class InDeptSurInfoVo {
    @Column(name="id")
    private String id;
    /**
     * 医院ID
     */
    @Column(name="hos_id")
    private String hosId ;
    /**
     * 科室ID
     */
    @Column(name="dept_id")
    private String deptId ;
    /**
     * 手术包配置表ID
     */
    @Column(name="sur_id")
    private String surId ;
    /**
     * 手术包码
     */
    @Column(name="sur_code")
    private String surCode ;
    /**
     * 手术包名
     */
    @Column(name="sur_name")
    private String surName ;
    /**
     * 患者姓名
     */
    @Column(name="sicker_name")
    private String sickerName ;
    /**
     * 入院号
     */
    @Column(name="in_hos_code")
    private String inHosCode ;
    /**
     * 手术编号
     */
    @Column(name="sur_schedule_no")
    private String surScheduleNo ;
    /**
     * 请购单号
     */
    @Column(name="apply_bill_id")
    private String applyBillId ;
    /**
     * 当前库房
     */
    @Column(name="cur_stock_id")
    private String curStockId ;
    /**
     * 状态
     * 0:打包，10：请购出库，20：请购入库，30：部分消毒，31：无需消耗，32：全部消毒40：科室出库 41：消耗，50：部分消毒，51：无需消毒，52：全部消毒，60：退库，70：完成
     */
    @Column(name="status")
    private int status ;
    /**
     * 打包人
     */
    @Column(name="packer")
    private String packer ;
    /**
     * 打包日期
     */
    @Column(name="packageDate")
    private Date packageDate ;

    private List<InDeptSurgeryListVo> surInfoList;
}