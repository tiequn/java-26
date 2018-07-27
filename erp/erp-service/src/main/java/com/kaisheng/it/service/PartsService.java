package com.kaisheng.it.service;

import com.github.pagehelper.PageInfo;
import com.kaisheng.it.entity.Parts;
import com.kaisheng.it.entity.Type;

import java.util.List;
import java.util.Map;

/**
 * @author guojiabang
 * @date 2018/7/23 0023
 */
public interface PartsService {

    /**
     * 根据id查询对应的配件对象
     * @param id
     * @return
     */
    Parts findById(Integer id);

    /**
     * 根据页码获得对应的数据集合
     * @param pageNo 页码 1 2 3
     * @return pageInfo 对象
     */
    PageInfo<Parts> findPage(Integer pageNo);

    /**
     * 根据id删除
     * @param id
     */
    void delById(Integer id);

    /**
     * 查询所有的配件类型列表
     * @return
     */
    List<Type> findTypeList();

    /**
     * 更新
     * @param parts
     */
    void partsEdit(Parts parts);

    /**
     *配件入库
     * @param parts
     */
    void saveParts(Parts parts);

    /**
     * 根据页码和筛选条件map集合查询对应的配件列表
     * @param pageNo
     * @param queryMap
     * @return
     */
    PageInfo<Parts> findPageByPageNoAndQueryMap(Integer pageNo, Map<String, Object> queryMap);
}
