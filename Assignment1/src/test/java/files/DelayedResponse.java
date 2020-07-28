package files;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DelayedResponse {
	private int page;
	@JsonProperty("per_page") private int perPage;
	private int total;
	@JsonProperty("total_pages") private int totalPages;
	private List<Data> data;
	private Ad ad;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int per_page) {
		this.perPage = per_page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int total_pages) {
		this.totalPages = total_pages;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

}
