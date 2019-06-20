package com.dpt.permission.param;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 邓鹏涛
 * @date 2019/1/20 22:20
 */
@Getter
@Setter
public class TestVo {

    @NotBlank
    private String msg;
    @NotNull
    private Integer id;
    //@NotEmpty
    //private List<String> str;

}
