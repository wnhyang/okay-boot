package cn.wnhyang.okay.web.mapper;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.wnhyang.okay.web.entity.ExampleDO;
import cn.wnhyang.okay.web.vo.example.ExamplePageReqVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
@Mapper
public interface ExampleMapper extends BaseMapperX<ExampleDO> {
    default PageResult<ExampleDO> selectPage(ExamplePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ExampleDO>()
                .likeIfPresent(ExampleDO::getField1, reqVO.getField1())
                .eqIfPresent(ExampleDO::getField2, reqVO.getField2())
                .betweenIfPresent(ExampleDO::getCreateTime, reqVO.getStartTime(), reqVO.getEndTime())
                .orderByDesc(ExampleDO::getId));
    }
}
