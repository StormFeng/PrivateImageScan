package com.lida.ocr;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ResultBean {


    /**
     * errNum : 0
     * errMsg : success
     * querySign : 3845925467,2370020290
     * retData : [{"rect":{"left":"0","top":"0","width":"33","height":"31"},"word":"  8"}]
     */

    private String errNum;
    private String errMsg;
    private String querySign;
    private List<RetDataBean> retData;

    public String getErrNum() {
        return errNum;
    }

    public void setErrNum(String errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getQuerySign() {
        return querySign;
    }

    public void setQuerySign(String querySign) {
        this.querySign = querySign;
    }

    public List<RetDataBean> getRetData() {
        return retData;
    }

    public void setRetData(List<RetDataBean> retData) {
        this.retData = retData;
    }

    public static class RetDataBean {
        /**
         * rect : {"left":"0","top":"0","width":"33","height":"31"}
         * word :   8
         */

        private RectBean rect;
        private String word;

        public RectBean getRect() {
            return rect;
        }

        public void setRect(RectBean rect) {
            this.rect = rect;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public static class RectBean {
            /**
             * left : 0
             * top : 0
             * width : 33
             * height : 31
             */

            private String left;
            private String top;
            private String width;
            private String height;

            public String getLeft() {
                return left;
            }

            public void setLeft(String left) {
                this.left = left;
            }

            public String getTop() {
                return top;
            }

            public void setTop(String top) {
                this.top = top;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }
        }
    }
}
