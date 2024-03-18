package cn.wnhyang.okay.admin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.wnhyang.okay.admin.convert.DictDataConvert;
import cn.wnhyang.okay.admin.entity.DictDataPO;
import cn.wnhyang.okay.admin.service.DictDataService;
import cn.wnhyang.okay.admin.vo.dictdata.*;
import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.log.core.annotation.OperateLog;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * 字典数据
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@RestController
@RequestMapping("/system/dictData")
@RequiredArgsConstructor
public class DictDataController {

    private final DictDataService dictDataService;

    /**
     * 创建字典数据
     *
     * @param reqVO 字典数据
     * @return 字典数据id
     */
    @PostMapping
    @OperateLog(module = "后台-字典", name = "创建字典数据")
    @SaCheckPermission("system:dict:create")
    public CommonResult<Long> createDictData(@Valid @RequestBody DictDataCreateVO reqVO) {
        Long dictDataId = dictDataService.createDictData(reqVO);
        return success(dictDataId);
    }

    /**
     * 更新字典数据
     *
     * @param reqVO 字典数据
     * @return 结果
     */
    @PutMapping
    @OperateLog(module = "后台-字典", name = "更新字典数据")
    @SaCheckPermission("system:dict:update")
    public CommonResult<Boolean> updateDictData(@Valid @RequestBody DictDataUpdateVO reqVO) {
        dictDataService.updateDictData(reqVO);
        return success(true);
    }

    /**
     * 删除字典数据
     *
     * @param id 字典数据id
     * @return 结果
     */
    @DeleteMapping
    @OperateLog(module = "后台-字典", name = "删除字典数据")
    @SaCheckPermission("system:dict:delete")
    public CommonResult<Boolean> deleteDictData(@RequestParam("id") Long id) {
        dictDataService.deleteDictData(id);
        return success(true);
    }

    /**
     * 查询简单菜单数据
     *
     * @return 菜单列表
     */
    @GetMapping("/simpleList")
    @OperateLog(module = "后台-字典", name = "查询简单菜单数据")
    @SaCheckLogin
    public CommonResult<List<DictDataSimpleVO>> getSimpleDictDataList() {
        List<DictDataPO> list = dictDataService.getDictDataList();
        return success(DictDataConvert.INSTANCE.convertList(list));
    }

    /**
     * 分页字典数据
     *
     * @param reqVO 分页请求
     * @return 字典数据
     */
    @GetMapping("/page")
    @OperateLog(module = "后台-字典", name = "分页字典数据")
    @SaCheckPermission("system:dict:query")
    public CommonResult<PageResult<DictDataRespVO>> getDictDataPage(@Valid DictDataPageVO reqVO) {
        return success(DictDataConvert.INSTANCE.convertPage(dictDataService.getDictDataPage(reqVO)));
    }

    /**
     * 查询详细字典数据
     *
     * @param id 字典数据id
     * @return 字典数据
     */
    @GetMapping
    @OperateLog(module = "后台-字典", name = "查询详细字典数据")
    @SaCheckPermission("system:dict:query")
    public CommonResult<DictDataRespVO> getDictData(@RequestParam("id") Long id) {
        return success(DictDataConvert.INSTANCE.convert(dictDataService.getDictData(id)));
    }

}
