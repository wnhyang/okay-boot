package cn.wnhyang.okay.web.service;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.web.entity.ExampleDO;
import cn.wnhyang.okay.web.vo.example.ExampleCreateReqVO;
import cn.wnhyang.okay.web.vo.example.ExamplePageReqVO;
import cn.wnhyang.okay.web.vo.example.ExampleUpdateReqVO;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
public interface ExampleService {

    /**
     * 获取单个example
     *
     * @param id exampleId
     * @return example
     */
    ExampleDO getExample(Long id);

    /**
     * 创建example
     *
     * @param reqVO example
     * @return exampleId
     */
    Long createExample(ExampleCreateReqVO reqVO);

    /**
     * 更新example
     *
     * @param reqVO example
     */
    void updateExample(ExampleUpdateReqVO reqVO);

    /**
     * 删除example
     *
     * @param id exampleId
     */
    void deleteExample(Long id);

    /**
     * 获取example分页
     *
     * @param reqVO example
     * @return example分页
     */
    PageResult<ExampleDO> getExamplePage(ExamplePageReqVO reqVO);
}
