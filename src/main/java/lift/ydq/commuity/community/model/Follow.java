package lift.ydq.commuity.community.model;

public class Follow {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FOLLOW.ID
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FOLLOW.FOLLOWERS
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    private Long followers;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FOLLOW.REQUESTER
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    private Long requester;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FOLLOW.GMT_CREATE
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column FOLLOW.TYPE
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    private Integer type;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FOLLOW.ID
     *
     * @return the value of FOLLOW.ID
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FOLLOW.ID
     *
     * @param id the value for FOLLOW.ID
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FOLLOW.FOLLOWERS
     *
     * @return the value of FOLLOW.FOLLOWERS
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public Long getFollowers() {
        return followers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FOLLOW.FOLLOWERS
     *
     * @param followers the value for FOLLOW.FOLLOWERS
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public void setFollowers(Long followers) {
        this.followers = followers;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FOLLOW.REQUESTER
     *
     * @return the value of FOLLOW.REQUESTER
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public Long getRequester() {
        return requester;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FOLLOW.REQUESTER
     *
     * @param requester the value for FOLLOW.REQUESTER
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public void setRequester(Long requester) {
        this.requester = requester;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FOLLOW.GMT_CREATE
     *
     * @return the value of FOLLOW.GMT_CREATE
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FOLLOW.GMT_CREATE
     *
     * @param gmtCreate the value for FOLLOW.GMT_CREATE
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column FOLLOW.TYPE
     *
     * @return the value of FOLLOW.TYPE
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column FOLLOW.TYPE
     *
     * @param type the value for FOLLOW.TYPE
     *
     * @mbg.generated Wed Apr 27 16:45:39 CST 2022
     */
    public void setType(Integer type) {
        this.type = type;
    }
}