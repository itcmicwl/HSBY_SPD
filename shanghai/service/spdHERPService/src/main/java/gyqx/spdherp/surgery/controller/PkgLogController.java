package gyqx.spdherp.surgery.controller;

import gyqx.spdherp.surgery.service.IPkgLogService;
import gyqx.spdherp.surgery.vo.PkgLogVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 包日志表(PkgLog)表控制层
 *
 * @author moonbless
 * @since 2018-09-29 14:39:05
 */
@RestController
@RequestMapping("pkgLog")
public class PkgLogController {
    /**
     * 服务对象
     */
    @Resource
    private IPkgLogService pkgLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public PkgLogVo selectOne(String id) {
        return this.pkgLogService.getById(id);
    }

}