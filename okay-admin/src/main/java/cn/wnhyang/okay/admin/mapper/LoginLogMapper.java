package cn.wnhyang.okay.admin.mapper;

import cn.wnhyang.okay.admin.entity.LoginLogPO;
import cn.wnhyang.okay.admin.enums.login.LoginResultEnum;
import cn.wnhyang.okay.admin.vo.loginlog.LoginLogPageVO;
import cn.wnhyang.okay.framework.common.pojo.PageResult;
import cn.wnhyang.okay.framework.mybatis.core.mapper.BaseMapperX;
import cn.wnhyang.okay.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统访问记录
 *
 * @author wnhyang
 * @since 2023/07/25
 */
@Mapper
public interface LoginLogMapper extends BaseMapperX<LoginLogPO> {

    default PageResult<LoginLogPO> selectPage(LoginLogPageVO reqVO) {
        LambdaQueryWrapperX<LoginLogPO> query = new LambdaQueryWrapperX<LoginLogPO>()
                .likeIfPresent(LoginLogPO::getUserIp, reqVO.getUserIp())
                .likeIfPresent(LoginLogPO::getAccount, reqVO.getAccount())
                .betweenIfPresent(LoginLogPO::getCreateTime, reqVO.getStartTime(), reqVO.getEndTime());
        if (Boolean.TRUE.equals(reqVO.getResult())) {
            query.eq(LoginLogPO::getResult, LoginResultEnum.SUCCESS.getResult());
        } else if (Boolean.FALSE.equals(reqVO.getResult())) {
            query.gt(LoginLogPO::getResult, LoginResultEnum.SUCCESS.getResult());
        }
        // 降序
        query.orderByDesc(LoginLogPO::getId);
        return selectPage(reqVO, query);
    }
}
