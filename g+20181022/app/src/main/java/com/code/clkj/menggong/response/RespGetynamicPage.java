package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by 92573 on 2017/12/26.
 * SourceBean 添加点赞信息
 *   private boolean isUpdateLike;//是否重新获取点赞信息
 private RespSaveDynamicLike respSaveDynamicLike;//点赞信息
 */

public class RespGetynamicPage extends TempResponse{

    /**
     * result : {"member":{"museNickName":"TheFlash⚡️","museId":209,"museImage":"0201801091805161599.png"},"bean":{"page":1,"pageLength":7,"pageSize":5,"size":35,"source":[{"comments":[{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}],"dyContent":"后天","dyCreateTime":"2018-01-12 14:18:57","dyId":55,"dyImage":"0201801121418577392.png","isLike":0,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}},{"comments":[{"bycmContent":"How are you","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"摸摸粉","bysmType":1,"museNickName":"闪电侠不爱笑","museId":231,"museNickNameReply":""}],"dyContent":"给咯哦我","dyCreateTime":"2018-01-08 18:14:01","dyId":54,"dyImage":"0201801081813598595.png","isLike":1,"memberUser":{"museNickName":"汾湖蓝u","museId":"204","museImage":"020171222092934388.jpg"}},{"comments":[],"dyContent":"测试图片存在不存在","dyCreateTime":"2018-01-05 17:51:42","dyId":53,"dyImage":"","isLike":1,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}},{"comments":[{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"很好玩","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"测试","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"测试","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"再次测试","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"火","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"有趣","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}],"dyContent":"好开心","dyCreateTime":"2018-01-05 17:47:03","dyId":52,"dyImage":"0201801051746489359.png,0201801051746545960.png,0201801051747015433.png","isLike":1,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}},{"comments":[],"dyContent":"发个图吧","dyCreateTime":"2018-01-05 17:42:20","dyId":51,"dyImage":"02018010517420488.png,0201801051742172916.png","isLike":0,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]}}
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
         * member : {"museNickName":"TheFlash⚡️","museId":209,"museImage":"0201801091805161599.png"}
         * bean : {"page":1,"pageLength":7,"pageSize":5,"size":35,"source":[{"comments":[{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}],"dyContent":"后天","dyCreateTime":"2018-01-12 14:18:57","dyId":55,"dyImage":"0201801121418577392.png","isLike":0,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}},{"comments":[{"bycmContent":"How are you","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"摸摸粉","bysmType":1,"museNickName":"闪电侠不爱笑","museId":231,"museNickNameReply":""}],"dyContent":"给咯哦我","dyCreateTime":"2018-01-08 18:14:01","dyId":54,"dyImage":"0201801081813598595.png","isLike":1,"memberUser":{"museNickName":"汾湖蓝u","museId":"204","museImage":"020171222092934388.jpg"}},{"comments":[],"dyContent":"测试图片存在不存在","dyCreateTime":"2018-01-05 17:51:42","dyId":53,"dyImage":"","isLike":1,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}},{"comments":[{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"很好玩","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"测试","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"测试","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"再次测试","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"火","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"有趣","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}],"dyContent":"好开心","dyCreateTime":"2018-01-05 17:47:03","dyId":52,"dyImage":"0201801051746489359.png,0201801051746545960.png,0201801051747015433.png","isLike":1,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}},{"comments":[],"dyContent":"发个图吧","dyCreateTime":"2018-01-05 17:42:20","dyId":51,"dyImage":"02018010517420488.png,0201801051742172916.png","isLike":0,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]}
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

        public static class BeanBean {
            /**
             * page : 1
             * pageLength : 7
             * pageSize : 5
             * size : 35
             * source : [{"comments":[{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}],"dyContent":"后天","dyCreateTime":"2018-01-12 14:18:57","dyId":55,"dyImage":"0201801121418577392.png","isLike":0,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}},{"comments":[{"bycmContent":"How are you","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"摸摸粉","bysmType":1,"museNickName":"闪电侠不爱笑","museId":231,"museNickNameReply":""}],"dyContent":"给咯哦我","dyCreateTime":"2018-01-08 18:14:01","dyId":54,"dyImage":"0201801081813598595.png","isLike":1,"memberUser":{"museNickName":"汾湖蓝u","museId":"204","museImage":"020171222092934388.jpg"}},{"comments":[],"dyContent":"测试图片存在不存在","dyCreateTime":"2018-01-05 17:51:42","dyId":53,"dyImage":"","isLike":1,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}},{"comments":[{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"很好玩","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"测试","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"测试","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"再次测试","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"火","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"有趣","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}],"dyContent":"好开心","dyCreateTime":"2018-01-05 17:47:03","dyId":52,"dyImage":"0201801051746489359.png,0201801051746545960.png,0201801051747015433.png","isLike":1,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}},{"comments":[],"dyContent":"发个图吧","dyCreateTime":"2018-01-05 17:42:20","dyId":51,"dyImage":"02018010517420488.png,0201801051742172916.png","isLike":0,"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]
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
                 * comments : [{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""},{"bycmContent":"hahah","bysmType":1,"museNickName":"TheFlash⚡️","museId":209,"museNickNameReply":""}]
                 * dyContent : 后天
                 * dyCreateTime : 2018-01-12 14:18:57
                 * dyId : 55
                 * dyImage : 0201801121418577392.png
                 * isLike : 0
                 * memberUser : {"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}
                 */

                private String dyContent;
                private String dyCreateTime;
                private String dyId;
                private String dyImage;
                private String isLike;
                private MemberUserBean memberUser;
                private List<CommentsBean> comments;
                private boolean isUpdatacomments = true;//是否重新获取评论信息
                private boolean isUpdateLike = true;//是否重新获取点赞信息
                private RespGetDynamicLikes respGetDynamicLikes;//点赞人信息

                public boolean isUpdatacomments() {
                    return isUpdatacomments;
                }

                public void setUpdatacomments(boolean updatacomments) {
                    isUpdatacomments = updatacomments;
                }

                public boolean isUpdateLike() {

                    return isUpdateLike;
                }

                public void setUpdateLike(boolean updateLike) {
                    isUpdateLike = updateLike;
                }

                public RespGetDynamicLikes getRespGetDynamicLikes() {
                    return respGetDynamicLikes;
                }

                public void setRespGetDynamicLikes(RespGetDynamicLikes respGetDynamicLikes) {
                    this.respGetDynamicLikes = respGetDynamicLikes;
                }

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

                public String getIsLike() {
                    return isLike;
                }

                public void setIsLike(String isLike) {
                    this.isLike = isLike;
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
                     * bycmContent : hahah
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
            }
        }
    }
}
