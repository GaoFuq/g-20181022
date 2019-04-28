package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by clkj on 2018/1/5.
 */

public class RespOtherHomePage extends TempResponse {

    /**
     * result : {"member":{"isFollow":0,"museNickName":"TheFlash⚡️","museId":209,"museImage":"0201801091805161599.png"},"bean":{"page":2,"pageLength":4,"pageSize":5,"size":18,"source":[{"comments":[{"bycmContent":"我愿意","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}],"dyContent":"发一个朋友群","dyCreateTime":"2017-12-27 15:37:08","dyId":37,"dyImage":"123465.jsp","likes":[{"museNickName":"TheFlash⚡️","museId":209},{"museNickName":"闪电侠不爱笑","museId":231}],"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]}}
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
         * member : {"isFollow":0,"museNickName":"TheFlash⚡️","museId":209,"museImage":"0201801091805161599.png"}
         * bean : {"page":2,"pageLength":4,"pageSize":5,"size":18,"source":[{"comments":[{"bycmContent":"我愿意","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}],"dyContent":"发一个朋友群","dyCreateTime":"2017-12-27 15:37:08","dyId":37,"dyImage":"123465.jsp","likes":[{"museNickName":"TheFlash⚡️","museId":209},{"museNickName":"闪电侠不爱笑","museId":231}],"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]}
         */

        private MemberBean member;
        private BeanBean bean;

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public BeanBean getBean() {
            return bean;
        }

        public void setBean(BeanBean bean) {
            this.bean = bean;
        }

        public static class MemberBean {
            /**
             * isFollow : 0
             * museNickName : TheFlash⚡️
             * museId : 209
             * museImage : 0201801091805161599.png
             */

            private String isFollow;
            private String museNickName;
            private String museId;
            private String museImage;

            public String getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(String isFollow) {
                this.isFollow = isFollow;
            }

            public String getMuseNickName() {
                return museNickName;
            }

            public void setMuseNickName(String museNickName) {
                this.museNickName = museNickName;
            }

            public String getMuseId() {
                return museId;
            }

            public void setMuseId(String museId) {
                this.museId = museId;
            }

            public String getMuseImage() {
                return museImage;
            }

            public void setMuseImage(String museImage) {
                this.museImage = museImage;
            }
        }

        public static class BeanBean {
            /**
             * page : 2
             * pageLength : 4
             * pageSize : 5
             * size : 18
             * source : [{"comments":[{"bycmContent":"我愿意","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}],"dyContent":"发一个朋友群","dyCreateTime":"2017-12-27 15:37:08","dyId":37,"dyImage":"123465.jsp","likes":[{"museNickName":"TheFlash⚡️","museId":209},{"museNickName":"闪电侠不爱笑","museId":231}],"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]
             */

            private String page;
            private String pageLength;
            private String pageSize;
            private String size;
            private List<SourceBean> source;

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getPageLength() {
                return pageLength;
            }

            public void setPageLength(String pageLength) {
                this.pageLength = pageLength;
            }

            public String getPageSize() {
                return pageSize;
            }

            public void setPageSize(String pageSize) {
                this.pageSize = pageSize;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public List<SourceBean> getSource() {
                return source;
            }

            public void setSource(List<SourceBean> source) {
                this.source = source;
            }

            public static class SourceBean {
                /**
                 * comments : [{"bycmContent":"我愿意","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}]
                 * dyContent : 发一个朋友群
                 * dyCreateTime : 2017-12-27 15:37:08
                 * dyId : 37
                 * dyImage : 123465.jsp
                 * likes : [{"museNickName":"TheFlash⚡️","museId":209},{"museNickName":"闪电侠不爱笑","museId":231}]
                 * memberUser : {"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}
                 */

                private String dyContent;
                private String dyCreateTime;
                private String dyId;
                private String dyImage;
                private MemberUserBean memberUser;
                private List<CommentsBean> comments;
                private List<LikesBean> likes;

                public String getDyContent() {
                    return dyContent;
                }

                public void setDyContent(String dyContent) {
                    this.dyContent = dyContent;
                }

                public String getDyCreateTime() {
                    return dyCreateTime;
                }

                public void setDyCreateTime(String dyCreateTime) {
                    this.dyCreateTime = dyCreateTime;
                }

                public String getDyId() {
                    return dyId;
                }

                public void setDyId(String dyId) {
                    this.dyId = dyId;
                }

                public String getDyImage() {
                    return dyImage;
                }

                public void setDyImage(String dyImage) {
                    this.dyImage = dyImage;
                }

                public MemberUserBean getMemberUser() {
                    return memberUser;
                }

                public void setMemberUser(MemberUserBean memberUser) {
                    this.memberUser = memberUser;
                }

                public List<CommentsBean> getComments() {
                    return comments;
                }

                public void setComments(List<CommentsBean> comments) {
                    this.comments = comments;
                }

                public List<LikesBean> getLikes() {
                    return likes;
                }

                public void setLikes(List<LikesBean> likes) {
                    this.likes = likes;
                }

                public static class MemberUserBean {
                    /**
                     * museNickName : TheFlash⚡️
                     * museId : 209
                     * museImage : 0201801091805161599.png
                     */

                    private String museNickName;
                    private String museId;
                    private String museImage;

                    public String getMuseNickName() {
                        return museNickName;
                    }

                    public void setMuseNickName(String museNickName) {
                        this.museNickName = museNickName;
                    }

                    public String getMuseId() {
                        return museId;
                    }

                    public void setMuseId(String museId) {
                        this.museId = museId;
                    }

                    public String getMuseImage() {
                        return museImage;
                    }

                    public void setMuseImage(String museImage) {
                        this.museImage = museImage;
                    }
                }

                public static class CommentsBean {
                    /**
                     * bycmContent : 我愿意
                     * bysmType : 1
                     * museNickName : TheFlash⚡️
                     * museId : 209
                     * museNickNameReply : 
                     */

                    private String bycmContent;
                    private String bysmType;
                    private String museNickName;
                    private String museId;
                    private String museNickNameReply;

                    public String getBycmContent() {
                        return bycmContent;
                    }

                    public void setBycmContent(String bycmContent) {
                        this.bycmContent = bycmContent;
                    }

                    public String getBysmType() {
                        return bysmType;
                    }

                    public void setBysmType(String bysmType) {
                        this.bysmType = bysmType;
                    }

                    public String getMuseNickName() {
                        return museNickName;
                    }

                    public void setMuseNickName(String museNickName) {
                        this.museNickName = museNickName;
                    }

                    public String getMuseId() {
                        return museId;
                    }

                    public void setMuseId(String museId) {
                        this.museId = museId;
                    }

                    public String getMuseNickNameReply() {
                        return museNickNameReply;
                    }

                    public void setMuseNickNameReply(String museNickNameReply) {
                        this.museNickNameReply = museNickNameReply;
                    }
                }

                public static class LikesBean {
                    /**
                     * museNickName : TheFlash⚡️
                     * museId : 209
                     */

                    private String museNickName;
                    private String museId;

                    public String getMuseNickName() {
                        return museNickName;
                    }

                    public void setMuseNickName(String museNickName) {
                        this.museNickName = museNickName;
                    }

                    public String getMuseId() {
                        return museId;
                    }

                    public void setMuseId(String museId) {
                        this.museId = museId;
                    }
                }
            }
        }
    }
}
