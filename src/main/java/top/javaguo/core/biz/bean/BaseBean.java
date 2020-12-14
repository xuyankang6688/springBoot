package top.javaguo.core.biz.bean;

import java.io.Serializable;

/**
 * 基础类
 * 
 * @date 2018/03/08
 * @author javaGuo
 */
public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 记录条数 **/
	public Integer limit;

	/** 当前页码 **/
	public Integer offset;

	/** 查询开始时间 **/
	private String queryBeginDate;

	/** 查询结束时间 **/
	private String queryEndDate;

	/** 排序对象 **/
	private String orderBy;

	/** 排序规则 **/
	private String sortingRules;
	
	/** 查询参数 **/
	private String queryKey;

	/** 获取查询参数 **/
	public String getQueryKey() {
		return queryKey;
	}

	/** 设置查询参数 **/
	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

	/** 获取记录条数 **/
	public Integer getLimit() {
		return limit;
	}

	/** 设置记录条数 **/
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/** 获取当前页码 **/
	public Integer getOffset() {
		return offset;
	}

	/** 设置当前页码 **/
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/** 获取查询开始时间 **/
	public String getQueryBeginDate() {
		return queryBeginDate;
	}

	/** 设置查询开始时间 **/
	public void setQueryBeginDate(String queryBeginDate) {
		this.queryBeginDate = queryBeginDate;
	}

	/** 获取查询结束时间 **/
	public String getQueryEndDate() {
		return queryEndDate;
	}

	/** 设置查询结束时间 **/
	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

	/** 获取排序对象 **/
	public String getOrderBy() {
		return orderBy;
	}

	/** 设置排序对象 **/
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/** 获取排序规则 **/
	public String getSortingRules() {
		return sortingRules;
	}

	/** 设置排序规则 **/
	public void setSortingRules(String sortingRules) {
		this.sortingRules = sortingRules;
	}

    /** 获取toString参数 **/
    public String getToStringParam() {
        return ", limit=" + limit +
                ", offset=" + offset +
                ", queryBeginDate='" + queryBeginDate + '\'' +
                ", queryEndDate='" + queryEndDate + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", sortingRules='" + sortingRules + '\'' ;
    }

}
