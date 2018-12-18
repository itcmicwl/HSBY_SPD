package gyqx.spdherp.stockout.service;

import gyqx.spdherp.stockout.vo.SickerUserVo;
import gyqx.spdherp.stockout.vo.SurgeryPkg4UseVo;

import java.util.List;

public interface SurgeryPkgOutService {

    /**
     * 获取病人消耗的（科室已出库）手术包
     * @param surgeryPkg4UseVo
     * @return
     */
    List<SurgeryPkg4UseVo> querySickerSurPkg(SurgeryPkg4UseVo surgeryPkg4UseVo);

    /**
     * 手术包消耗单提交
     * @param sickerUseVo
     * @return
     */
    int add(SickerUserVo sickerUseVo) throws Exception;
}
