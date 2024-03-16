package cn.wnhyang.okay.admin.mapper;


import cn.wnhyang.okay.admin.entity.DictDataPO;
import cn.wnhyang.okay.admin.vo.dictdata.DictDataPageVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.Arrays;

/**
 * 字典数据表 Mapper 接口
 *
 * @author wnhyang
 * @since 2023/09/13
 */
@Mapper
public interface DictDataMapper extends BaseMapperX<DictDataPO> {

    default PageResult<DictDataPO> selectPage(DictDataPageVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DictDataPO>()
                .likeIfPresent(DictDataPO::getLabel, reqVO.getLabel())
                .eqIfPresent(DictDataPO::getDictType, reqVO.getDictType())
                .eqIfPresent(DictDataPO::getStatus, reqVO.getStatus())
                .orderByDesc(Arrays.asList(DictDataPO::getDictType, DictDataPO::getSort)));
    }

    default long selectCountByDictType(String dictType) {
        return selectCount(DictDataPO::getDictType, dictType);
    }

    default DictDataPO selectByDictTypeAndValue(String dictType, String value) {
        return selectOne(DictDataPO::getDictType, dictType, DictDataPO::getValue, value);
    }
}
