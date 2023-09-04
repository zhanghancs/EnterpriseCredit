package com.example.enterprisecredit.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhufu
 * @since 2023-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Industry implements Serializable {

    private static final long serialVersionUID = 1L;

    private String industry;


}
