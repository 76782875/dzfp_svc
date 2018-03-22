package com.rjxx.taxeasy.service.jkpz;

import com.rjxx.taxeasy.dto.AdapterPost;
import com.rjxx.utils.yjapi.Result;

import java.util.Map;

/**
 * @author: zsq
 * @date: 2018/3/20 18:04
 * @describe:接口配置统一方法
 */

public interface JkpzService {

    Result jkpzInvoice(AdapterPost adapterPost);
}
