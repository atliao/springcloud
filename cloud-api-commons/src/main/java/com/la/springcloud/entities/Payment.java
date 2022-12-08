package com.la.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable //序列化，在不同服务器调用时，确保是同一个对象
{
    private Long id;
    private String serial;
}
