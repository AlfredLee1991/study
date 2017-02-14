package com.framework.common.req;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 功能描述：医嘱模板库查询请求.<br/>
 * 
 * #date： 2016年1月19日 上午9:51:53<br/>
 * #author 8104485-李旭<br/>
 * #since 1.0.0<br/>
 */
public class ReqQueryTemplate implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 4605560243336988188L;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 页大小
     */
    private int pageSize;

    /**
     * 是否查询默认的推荐栏，1是，2不是
     */
    private int queryDefault;

    /**
     * 病种cid
     */
    private String sicknessKindCid;

    /**
     * 医嘱类型，取值：1：患教，2：调查表，3：随访
     */
    private int doctorAdviceType;

    /**
     * 医嘱来源，取值，1：系统模板，2：自己创建的模板
     */
    private int adviceSource;

    /**
     * 医生cid
     */
    private String doctorCid;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getQueryDefault() {
        return queryDefault;
    }

    public void setQueryDefault(int queryDefault) {
        this.queryDefault = queryDefault;
    }

    public String getSicknessKindCid() {
        return sicknessKindCid;
    }

    public void setSicknessKindCid(String sicknessKindCid) {
        this.sicknessKindCid = sicknessKindCid;
    }

    public int getDoctorAdviceType() {
        return doctorAdviceType;
    }

    public void setDoctorAdviceType(int doctorAdviceType) {
        this.doctorAdviceType = doctorAdviceType;
    }

    public int getAdviceSource() {
        return adviceSource;
    }

    public void setAdviceSource(int adviceSource) {
        this.adviceSource = adviceSource;
    }

    public String getDoctorCid() {
        return doctorCid;
    }

    public void setDoctorCid(String doctorCid) {
        this.doctorCid = doctorCid;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
