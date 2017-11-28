package com.tmall.util;

public class Page {
	int begin;
	int singleCount;
	int totalCount;
	String param;

	public Page(int beg, int singleCount) {
		this.begin = beg;
		this.singleCount = singleCount;
	}

	public int getTotalPage() {
		int total = totalCount / singleCount;
		if (totalCount % singleCount != 0) {
			total++;
		}

		return total == 0 ? 1 : total;
	}

	public int getLastBeg() {
		int lastBeg;
		if (totalCount % singleCount == 0) {
			lastBeg = totalCount - singleCount;
		} else {
			lastBeg = totalCount - totalCount % singleCount;
		}
		lastBeg = lastBeg > 0 ? lastBeg : 0;

		return lastBeg;
	}

	public boolean isHasPreviouse() {
		if (begin == 0)
			return false;
		return true;
	}

	public boolean isHasNext() {
		if (begin == getLastBeg())
			return false;
		return true;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getSingleCount() {
		return singleCount;
	}

	public void setSingleCount(int singleCount) {
		this.singleCount = singleCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
