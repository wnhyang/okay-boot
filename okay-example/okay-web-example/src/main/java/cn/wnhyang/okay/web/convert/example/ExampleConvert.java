package cn.wnhyang.okay.web.convert.example;

import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.web.entity.ExampleDO;
import cn.wnhyang.okay.web.vo.example.ExampleCreateReqVO;
import cn.wnhyang.okay.web.vo.example.ExampleRespVO;
import cn.wnhyang.okay.web.vo.example.ExampleUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
@Mapper
public interface ExampleConvert {

    ExampleConvert INSTANCE = Mappers.getMapper(ExampleConvert.class);


    PageResult<ExampleRespVO> convertPage(PageResult<ExampleDO> examplePage);

    ExampleRespVO convert(ExampleDO example);

    ExampleDO convert(ExampleCreateReqVO reqVO);

    ExampleDO convert(ExampleUpdateReqVO reqVO);
}
