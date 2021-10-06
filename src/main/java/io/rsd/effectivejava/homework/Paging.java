package main.java.io.rsd.effectivejava.homework;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class Paging {
    // 세팅하는 값
    private final int VISIBLE_PAGE_COUNT = 10;
    private final int VISIBLE_FRONT_PAGE_COUNT = getVisibleFrontPageCount();

    // 받아오는 값
    private final int currentPage; // 현재 페이지
    private final int pageRowCount; // 페이지 로우 수
    private final int totalRowCount; // 총 로우 수


    /**
     * 보여지는 앞 페이지 수 산출
     * 보여지는 앞 페이지 수 = (보여지는 페이지 수 / 2) 올림 - 1
     * case
     *  1) 보여지는 페이지 수 = 10
     *  10 / 2
     *  = 5 - 1
     *  = 4
     *  2) 보여지는 페이지 수 = 5
     *  5 / 2
     *  = 2.5 올림
     *  = 3 - 1
     *  = 2
     *  3) 보여지는 페이지 수 = 3
     *  3 / 2
     *  = 1.5 올림
     *  = 2 - 1
     *  = 1
     *  4) 보여지는 페이지 수 = 2
     *  2 / 2
     *  = 1 - 1
     *  = 0
     * @return int
     */
    private int getVisibleFrontPageCount() {
        return BigDecimal.valueOf(VISIBLE_PAGE_COUNT).divide(BigDecimal.valueOf(2), RoundingMode.CEILING).intValue() - 1;
    }

    public Paging(int currentPage, int pageRowCount, int totalRowCount) {
        this.currentPage = currentPage;
        this.pageRowCount = pageRowCount;
        this.totalRowCount = totalRowCount;
    }

    /** 페이징 산출
     * 예외1) 페이지 로우 수 * 현재 페이지가 총 로우 수 + 페이지 로우 수 보다 높은 경우 Exception
     * 예외2) 현재 페이지가 총 페이지 수보다 높은 경우 Exception
     * @return PagingResult
     */
    public PagingResult paging() {
        if (pageRowCount * currentPage > totalRowCount + pageRowCount) {
            throw new IllegalArgumentException("잘못된 요청 입니다.");
        }
        int totalPageCount = totalPageCountCalculation();
        if (currentPage > totalPageCount ) {
            throw new IllegalArgumentException("잘못된 요청 입니다.");
        }
        int startPage = startPageCalculation(totalPageCount);
        int endPage = endPageCalculation(totalPageCount, startPage);
        boolean moveNextPage = currentPage != totalPageCount;
        boolean movePreviousPage = currentPage > 1;
        return new PagingResult(this, totalPageCount, startPage, endPage, moveNextPage, movePreviousPage);
    }

    /**
     * 총 페이지 수 산출
     * 조건) 총 페이지 수 = 총 로우 수 / 페이지 로우 수
     * 예외) 나눈 값의 나머지 로우가 존재하는 경우 + 1
     * case
     *  1) 총 로우 수 = 100, 페이지 로우 수 = 10
     *  100 / 10
     *  = 10
     *  2) 총 로우 수 = 101, 페이지 로우 수 = 10
     *  101 / 10
     *  = 10 나머지로 인한 ++
     *  = 11
     * @return int
     */
    private int totalPageCountCalculation() {
        int totalPageCount = totalRowCount / pageRowCount;
        if (totalRowCount % pageRowCount > 0) {
            totalPageCount++;
        }
        return totalPageCount;
    }

    /**
     * 시작 페이지 산출
     * 조건) 시작 페이지 = 현재 페이지 - 보여지는 앞 페이지 수
     * 조건) 최대 시작 페이지 = 총 페이지 수 - 보여지는 페이지 수 + 1
     * 예외1) 시작 페이지가 1보다 작은 경우 페이지 1로 return
     * 예외2) 시작 페이지가 최대 시작 페이지보다 높은 경우 최대 시작 페이지를 return
     * case
     *  1) 현재 페이지 = 5, 총 페이지 수 = 10, 보여지는 앞 페이지 수 = 4, 보여지는 페이지 수 = 10
     *  5 - 4
     *  = 1
     *  2) 현재 페이지 = 1, 총 페이지 수 = 10, 보여지는 앞 페이지 수 = 4, 보여지는 페이지 수 = 10
     *  1 - 4
     *  = -3 <= 1
     *  = 1 (예외 1)
     *  3) 현재 페이지 = 17, 총 페이지 수 = 20, 보여지는 앞 페이지 수 = 4, 보여지는 페이지 수 = 10
     *  17 - 4
     *  = 13 > 20 - 10 + 1
     *  = 11 (최대 시작 페이지)
     * @param totalPageCount 총 페이지 수
     * @return int
     */
    private int startPageCalculation(int totalPageCount) {
        int startPage = currentPage - VISIBLE_FRONT_PAGE_COUNT;
        if (startPage <= 1) {
            return 1;
        }
        int maximumStartPage = totalPageCount - VISIBLE_PAGE_COUNT + 1;
        return Math.min(maximumStartPage, startPage);
    }

    /**
     * 종료 페이지 산출
     * 조건) 종료 페이지 = 시작 페이지 + 보여지는 페이지 수 - 1
     * 예외) 종료 페이지가 총 페이지 수보다 높은 경우 총 페이지 수를 return
     * case
     *  1) 시작 페이지 = 1, 현재 페이지 = 5, 총 페이지 수 = 10, 보여지는 앞 페이지 수 = 4, 보여지는 페이지 수 = 10
     *  1 + 10 - 1
     *  = 10
     *  2) 시작 페이지 = 1, 현재 페이지 = 10, 총 페이지 수 = 10, 보여지는 앞 페이지 수 = 4, 보여지는 페이지 수 = 10
     *  1 + 10 -1 > 10
     *  = 10(총 페이지 수)
     *  3) 시작 페이지 = 11, 현재 페이지 = 17, 총 페이지 수 = 20, 보여지는 앞 페이지 수 = 4, 보여지는 페이지 수 = 10
     *  11 + 10 - 1 > 20
     *  = 20
     *  4) 시작 페이지 = 1, 현재 페이지 = 5, 총 페이지 수 = 5, 보여지는 앞 페이지 수 = 4, 보여지는 페이지 수 = 10
     *  1 + 10 - 1 > 5
     *  = 5(총 페이지 수)
     * @param totalPageCount 총 페이지 수
     * @param startPage 시작 페이지
     * @return int
     */
    private int endPageCalculation(int totalPageCount, int startPage) {
        int endPage = startPage + VISIBLE_PAGE_COUNT - 1;
        return Math.min(endPage, totalPageCount);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public static void main(String[] args) {
        final int totalRowCount = 222;
        final int pageRow = 10;
        int pageCount = totalRowCount / pageRow ;
        if (totalRowCount % pageRow > 0) pageCount ++;
        IntStream.rangeClosed(1, pageCount)
                .forEach(i -> new Paging(i, pageRow, totalRowCount).paging().printLine());
    }
}
