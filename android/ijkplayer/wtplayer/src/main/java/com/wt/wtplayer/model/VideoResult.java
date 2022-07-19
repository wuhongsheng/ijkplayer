package com.wt.wtplayer.model;

/**
 * description
 *
 * @author whs
 * @date 2020/9/2
 */
public class VideoResult {

    /**
     * result : {"ErrorNum":200,"ErrotString":"success.","URL":"http://192.162.130.251:8090/live?port=1935&app=live&stream=43100111011320000002_1"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ErrorNum : 200
         * ErrotString : success.
         * URL : http://192.162.130.251:8090/live?port=1935&app=live&stream=43100111011320000002_1
         */

        private int ErrorNum;
        private String ErrotString;
        private String URL;

        public int getErrorNum() {
            return ErrorNum;
        }

        public void setErrorNum(int ErrorNum) {
            this.ErrorNum = ErrorNum;
        }

        public String getErrotString() {
            return ErrotString;
        }

        public void setErrotString(String ErrotString) {
            this.ErrotString = ErrotString;
        }

        public String getURL() {
            return URL;
        }

        public void setURL(String URL) {
            this.URL = URL;
        }
    }
}
