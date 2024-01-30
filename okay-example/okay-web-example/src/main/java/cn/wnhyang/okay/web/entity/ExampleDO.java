package cn.wnhyang.okay.web.entity;

import cn.wnhyang.okay.framework.mybatis.core.base.BaseDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wnhyang
 * @date 2024/1/5
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("example")
public class ExampleDO extends BaseDO {

    private static final long serialVersionUID = 4860177073788592502L;

    @TableId
    private Long id;

    @TableField("field1")
    private String field1;

    @TableField("field2")
    private Integer field2;

    @TableField("field3")
    private String field3;

    @TableField("type")
    private Integer type;

    @TableField("status")
    private Boolean status;

    public void addField2(Integer field2) {
        this.field2 += field2;
    }
}
