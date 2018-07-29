package Model;

public class ApiResponses {
    int mRC;
    Paging mPaging;

    public int getRC() {
        return mRC;
    }

    public Paging getPaging() {
        return mPaging;
    }

    public static class Paging {
        int mPage;
        int mPerPage;
        int mPageStart;
        int mPageEnd;
        int mTotal;

        public int getPage() {
            return mPage;
        }

        public int getPerPage() {
            return mPerPage;
        }

        public int getPageStart() {
            return mPageStart;
        }

        public int getPageEnd() {
            return mPageEnd;
        }

        public int getTotal() {
            return mTotal;
        }
    }

    public static class PagingPost {
        int mPage;
        int mLimit;

        public void setPage(int mPage) {
            this.mPage = mPage;
        }

        public void setLimit(int mLimit) {
            this.mLimit = mLimit;
        }
    }
}
