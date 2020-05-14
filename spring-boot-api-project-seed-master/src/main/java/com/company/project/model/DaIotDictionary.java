package com.company.project.model;

import javax.persistence.*;

@Table(name = "da_iot_dictionary")
public class DaIotDictionary {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 传感器类型
     */
    private Integer type;

    /**
     * 传感器名称
     */
    private String name;

    /**
     * 显示名称
     */
    @Column(name = "display_name")
    private String displayName;

    /**
     * 单位
     */
    private String unit;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取传感器类型
     *
     * @return type - 传感器类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置传感器类型
     *
     * @param type 传感器类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取传感器名称
     *
     * @return name - 传感器名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置传感器名称
     *
     * @param name 传感器名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取显示名称
     *
     * @return display_name - 显示名称
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 设置显示名称
     *
     * @param displayName 显示名称
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 获取单位
     *
     * @return unit - 单位
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 设置单位
     *
     * @param unit 单位
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
}