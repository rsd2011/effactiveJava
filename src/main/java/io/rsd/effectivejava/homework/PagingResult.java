package main.java.io.rsd.effectivejava.homework;

public class PagingResult {
    private final Paging paging;
    private final int totalPageCount;
    private final int startPage;
    private final int endPage;
    private final boolean moveNextPage;
    private final boolean movePreviousPage;

    public PagingResult(Paging paging, int totalPageCount, int startPage, int endPage, boolean moveNextPage, boolean movePreviousPage) {
        this.paging = paging;
        this.totalPageCount = totalPageCount;
        this.startPage = startPage;
        this.endPage = endPage;
        this.moveNextPage = moveNextPage;
        this.movePreviousPage = movePreviousPage;
    }

    public void print() {
        System.out.println("⟶ 현재 페이지 : " + paging.getCurrentPage());
        System.out.println("⟶ 총 페이지 수 : " + totalPageCount);
        System.out.println("⟶ 시작 페이지 No : " + startPage);
        System.out.println("⟶ 종료 페이지 No : " + endPage);
        System.out.println("⟶ 다음 페이지 이동가능 여부 : " + moveNextPage);
        System.out.println("⟶ 이전 페이지 이동가능 여부 : " + movePreviousPage);
    }

    public void printLine() {
        System.out.println(String.format("총 페이지 수 : %d, 현재 페이지 : %d, 시작 페이지 No : %d, 종료 페이지 : %d, 다음 페이지 이동가능 여부 : %s, 이전 페이지 이동 가능 여부 : %s",
                totalPageCount,
                paging.getCurrentPage(),
                startPage,
                endPage,
                moveNextPage,
                movePreviousPage));
    }
}
