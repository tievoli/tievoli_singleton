/*
 * @(#)Page.java 1.0 2016年7月7日
 *
 * Copyright (c) 2015 www.yescai.com. All Rights Reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.tievoli.model;

import java.io.Serializable;

/**
 * @author Ryan Jiang
 * 
 */
public class Page<T> implements Serializable {

	private static final Integer defaultPageIndex = 1;

	private static final Integer defaultPageSize = 20;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 285904103760765206L;

	private Integer pageIndex;
	private Integer pageSize;
	private Integer rowTotal;
	private Integer pageTotal;
	private Integer jumpNum;
	private Integer startRow;
	private String pageBar;
	private T object;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = (pageIndex == null || pageIndex.intValue() <= 0) ? defaultPageIndex : pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = (pageSize == null || pageSize.intValue() <= 0) ? defaultPageSize : pageSize;
	}

	public Integer getRowTotal() {
		return rowTotal;
	}

	public void setRowTotal(Integer rowTotal) {
		this.rowTotal = rowTotal;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public Integer getJumpNum() {
		return jumpNum;
	}

	public void setJumpNum(Integer jumpNum) {
		this.jumpNum = jumpNum;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public String getPageBar() {
		return pageBar;
	}

	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

}
