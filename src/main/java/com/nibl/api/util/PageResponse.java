package com.nibl.api.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

/**
 * 
 * Wraps JPA response Page into our own custom response format.
 * 
 * @param <T>
 */
public class PageResponse<T> extends Response {
    private List<T> content;
    private Integer offset;
    private Integer max;
    private Long total;
    private String previous = "";
    private String current = "";
    private String next = "";

    public PageResponse() {
        super(HttpStatus.OK);
    }

    /**
     * Wraps the Page content into our own implementation.
     * 
     * The HttpServletRequest parameter is used to determine previous, current and next page based on request query string. The parameter can be null but url to
     * pages will be empty.
     * 
     * @param page
     * @param request
     */
    public PageResponse(Page<T> page, HttpServletRequest request) {
        super(HttpStatus.OK);
        String pageNumStr = "page";
        int previousPageNumber = page.getNumber() - 1;
        int currentPageNumber = page.getNumber();
        int nextPageNumber = page.getNumber() + 1;
        String pageNumberReplacement = pageNumStr + "=" + currentPageNumber;

        this.setContent(page.getContent());
        this.setOffset(page.getNumber() * page.getSize());
        this.setMax(page.getSize());
        this.setTotal(page.getTotalElements());

        // Find the proper query parameter and make sure pageNumStr exists
        String queryString = "";
        if (request != null && request.getQueryString() != null) {
            // Existing queryString, so append if pageNumStr doesn't exist
            if (request.getParameter(pageNumStr) == null) {
                queryString = request.getQueryString() + "&" + pageNumStr + "=" + currentPageNumber;
            } else {
                queryString = request.getQueryString();
            }
        } else {
            // No query string so add pageNumStr
            queryString = pageNumStr + "=" + currentPageNumber;
        }

        // Set the previous, current and next fields
        this.setCurrent(queryString);
        if (this.getOffset() > 0) {
            // Offset = 'max' * pageNum. If offset is positive, it means there is a previous
            this.setPrevious(
                    this.getCurrent().replaceAll(pageNumberReplacement, pageNumStr + "=" + previousPageNumber));
        }
        if (this.getOffset() + this.getMax() < this.getTotal()) {
            // If offset + max is less than total, it means there is a next
            this.setNext(this.getCurrent().replace(pageNumberReplacement, pageNumStr + "=" + nextPageNumber));
        }
    }

    public Pageable createPageRequest(int pageNum, int pageSize, String sort, String sortField) {
        return new PageRequest(pageNum, pageSize, new Sort(Sort.Direction.fromString(sort), sortField));
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}