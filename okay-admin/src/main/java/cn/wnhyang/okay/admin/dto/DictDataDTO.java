package cn.wnhyang.okay.admin.dto;

import lombok.Data;

/**
 * @author wnhyang
 * @date 2023/9/21
 **/
@Data
public class DictDataDTO {

    private String label;

    private String value;

    private String dictType;

    private Integer status;
}