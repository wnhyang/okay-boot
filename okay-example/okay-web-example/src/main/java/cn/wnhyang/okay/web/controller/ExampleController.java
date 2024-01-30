package cn.wnhyang.okay.web.controller;

import cn.wnhyang.okay.framework.common.pojo.CommonResult;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.web.convert.example.ExampleConvert;
import cn.wnhyang.okay.web.entity.ExampleDO;
import cn.wnhyang.okay.web.service.ExampleService;
import cn.wnhyang.okay.web.vo.example.ExampleCreateReqVO;
import cn.wnhyang.okay.web.vo.example.ExamplePageReqVO;
import cn.wnhyang.okay.web.vo.example.ExampleRespVO;
import cn.wnhyang.okay.web.vo.example.ExampleUpdateReqVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.wnhyang.okay.framework.common.pojo.CommonResult.success;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Slf4j
public class ExampleController {

    private final ExampleService exampleService;

    /**
     * 新增example
     *
     * @param reqVO example
     * @return exampleId
     */
    @PostMapping("/create")
    public CommonResult<Long> createDictType(@Valid @RequestBody ExampleCreateReqVO reqVO) {
        Long exampleId = exampleService.createExample(reqVO);
        return success(exampleId);
    }

    /**
     * 更新example
     *
     * @param reqVO example
     * @return 结果
     */
    @PutMapping("/update")
    public CommonResult<Boolean> updateExample(@Valid @RequestBody ExampleUpdateReqVO reqVO) {
        exampleService.updateExample(reqVO);
        return success(true);
    }

    /**
     * 删除example
     *
     * @param id exampleId
     * @return 结果
     */
    @DeleteMapping("/delete")
    public CommonResult<Boolean> deleteExample(@RequestParam("id") Long id) {
        exampleService.deleteExample(id);
        return success(true);
    }

    /**
     * 分页example
     *
     * @param reqVO 分页请求
     * @return 分页example
     */
    @GetMapping("/page")
    public CommonResult<PageResult<ExampleRespVO>> pageExamples(@Valid ExamplePageReqVO reqVO) {
        return success(ExampleConvert.INSTANCE.convertPage(exampleService.getExamplePage(reqVO)));
    }

    /**
     * 查询example详情
     *
     * @param id exampleId
     * @return example详情
     */
    @GetMapping(value = "/get")
    public CommonResult<ExampleRespVO> getExample(@RequestParam("id") Long id) {
        return success(ExampleConvert.INSTANCE.convert(exampleService.getExample(id)));
    }

    @GetMapping("/add")
    public CommonResult<Boolean> addExample() {
        List<ExampleDO> exampleList = exampleService.getExampleList();

        for (ExampleDO exampleDO : exampleList) {
            exampleDO.addField2(1);
        }
        exampleService.updateBatch(exampleList);
        return success(true);
    }
}
