package cn.wnhyang.okay.admin.mapper;


import cn.wnhyang.okay.admin.entity.RoleDO;
import cn.wnhyang.okay.admin.vo.role.RolePageReqVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息表
 *
 * @author wnhyang
 * @since 2023/05/14
 */
@Mapper
public interface RoleMapper extends BaseMapperX<RoleDO> {

    default RoleDO selectByName(String name) {
        return selectOne(RoleDO::getName, name);
    }

    default RoleDO selectByValue(String value) {
        return selectOne(RoleDO::getValue, value);
    }

    default PageResult<RoleDO> selectPage(RolePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoleDO>()
                .likeIfPresent(RoleDO::getName, reqVO.getName())
                .likeIfPresent(RoleDO::getValue, reqVO.getValue())
                .eqIfPresent(RoleDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RoleDO::getCreateTime, reqVO.getStartTime(), reqVO.getEndTime())
                .orderByDesc(RoleDO::getId));
    }
}
