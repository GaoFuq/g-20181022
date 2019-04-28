package com.code.clkj.menggong.response;

import com.lf.tempcore.tempResponse.TempResponse;

import java.util.List;

/**
 * Created by clkj on 2018/1/5.
 */

public class RespMyHomePage extends TempResponse{

    /**
     * result : {"member":{"museImage":"0201801091805161599.png","museId":209,"museNickName":"TheFlash⚡️"},"bean":{"source":[{"name":"2018年01月","list":[{"comments":[{"museNickNameReply":"","bycmContent":"测试","bysmType":1,"museId":209,"museNickName":"TheFlash⚡️"}],"dyContent":"还不发","dyCreateTime":"2018-01-05 17:41:42","dyId":50,"dyImage":"","likes":[{"museId":209,"museNickName":"TheFlash⚡️"}],"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]}],"page":1,"pageSize":5,"pageLength":1,"size":18}}
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
         * member : {"museImage":"0201801091805161599.png","museId":209,"museNickName":"TheFlash⚡️"}
         * bean : {"source":[{"name":"2018年01月","list":[{"comments":[{"museNickNameReply":"","bycmContent":"测试","bysmType":1,"museId":209,"museNickName":"TheFlash⚡️"}],"dyContent":"还不发","dyCreateTime":"2018-01-05 17:41:42","dyId":50,"dyImage":"","likes":[{"museId":209,"museNickName":"TheFlash⚡️"}],"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]}],"page":1,"pageSize":5,"pageLength":1,"size":18}
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
             * museImage : 0201801091805161599.png
             * museId : 209
             * museNickName : TheFlash⚡️
             */

            private String museImage;
            private String museId;
            private String museNickName;

            public String getMuseImage() {
                return museImage;
            }

            public void setMuseImage(String museImage) {
                this.museImage = museImage;
            }

            public String getMuseId() {
                return museId;
            }

            public void setMuseId(String museId) {
                this.museId = museId;
            }

            public String getMuseNickName() {
                return museNickName;
            }

            public void setMuseNickName(String museNickName) {
                this.museNickName = museNickName;
            }
        }

        public static class BeanBean {
            /**
             * source : [{"name":"2018年01月","list":[{"comments":[{"museNickNameReply":"","bycmContent":"测试","bysmType":1,"museId":209,"museNickName":"TheFlash⚡️"}],"dyContent":"还不发","dyCreateTime":"2018-01-05 17:41:42","dyId":50,"dyImage":"","likes":[{"museId":209,"museNickName":"TheFlash⚡️"}],"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]}]
             * page : 1
             * pageSize : 5
             * pageLength : 1
             * size : 18
             */

            private String page;
            private String pageSize;
            private String pageLength;
            private String size;
            private List<SourceBean> source;

            public String getPage() {
                return page;
            }

            public void setPage(String page) {
                this.page = page;
            }

            public String getPageSize() {
                return pageSize;
            }

            public void setPageSize(String pageSize) {
                this.pageSize = pageSize;
            }

            public String getPageLength() {
                return pageLength;
            }

            public void setPageLength(String pageLength) {
                this.pageLength = pageLength;
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
                 * name : 2018年01月
                 * list : [{"comments":[{"museNickNameReply":"","bycmContent":"测试","bysmType":1,"museId":209,"museNickName":"TheFlash⚡️"}],"dyContent":"还不发","dyCreateTime":"2018-01-05 17:41:42","dyId":50,"dyImage":"","likes":[{"museId":209,"museNickName":"TheFlash⚡️"}],"memberUser":{"museNickName":"TheFlash⚡️","museId":"209","museImage":"0201801091805161599.png"}}]
                 */

                private String name;
                private List<ListBean> list;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                public static class ListBean {
                    /**
                     * comments : [{"museNickNameReply":"","bycmContent":"测试","bysmType":1,"museId":209,"museNickName":"TheFlash⚡️"}]
                     * dyContent : 还不发
                     * dyCreateTime : 2018-01-05 17:41:42
                     * dyId : 50
                     * dyImage : 
                     * likes : [{"museId":209,"museNickName":"TheFlash⚡️"}]
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
                         * museNickNameReply : 
                         * bycmContent : 测试
                         * bysmType : 1
                         * museId : 209
                         * museNickName : TheFlash⚡️
                         */

                        private String museNickNameReply;
                        private String bycmContent;
                        private String bysmType;
                        private String museId;
                        private String museNickName;

                        public String getMuseNickNameReply() {
                            return museNickNameReply;
                        }

                        public void setMuseNickNameReply(String museNickNameReply) {
                            this.museNickNameReply = museNickNameReply;
                        }

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

                        public String getMuseId() {
                            return museId;
                        }

                        public void setMuseId(String museId) {
                            this.museId = museId;
                        }

                        public String getMuseNickName() {
                            return museNickName;
                        }

                        public void setMuseNickName(String museNickName) {
                            this.museNickName = museNickName;
                        }
                    }

                    public static class LikesBean {
                        /**
                         * museId : 209
                         * museNickName : TheFlash⚡️
                         */

                        private String museId;
                        private String museNickName;

                        public String getMuseId() {
                            return museId;
                        }

                        public void setMuseId(String museId) {
                            this.museId = museId;
                        }

                        public String getMuseNickName() {
                            return museNickName;
                        }

                        public void setMuseNickName(String museNickName) {
                            this.museNickName = museNickName;
                        }
                    }
                }
            }
        }
    }
}
