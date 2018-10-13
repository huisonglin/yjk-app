package test.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`yjk_member_info`")
public class YjkMemberInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.id
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.member_id
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private Long memberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.front_of_identity_card
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private String frontOfIdentityCard;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.back_of_identity_card
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private String backOfIdentityCard;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.business_licence
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private String businessLicence;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.companyname
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private String companyname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.legal_person_name
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private String legalPersonName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.legal_person_idcard
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private String legalPersonIdcard;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.legal_person_mobile
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private String legalPersonMobile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.age
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.address
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.create_time
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_member_info.update_time
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.id
     *
     * @return the value of yjk_member_info.id
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.id
     *
     * @param id the value for yjk_member_info.id
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.member_id
     *
     * @return the value of yjk_member_info.member_id
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.member_id
     *
     * @param memberId the value for yjk_member_info.member_id
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.front_of_identity_card
     *
     * @return the value of yjk_member_info.front_of_identity_card
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public String getFrontOfIdentityCard() {
        return frontOfIdentityCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.front_of_identity_card
     *
     * @param frontOfIdentityCard the value for yjk_member_info.front_of_identity_card
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setFrontOfIdentityCard(String frontOfIdentityCard) {
        this.frontOfIdentityCard = frontOfIdentityCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.back_of_identity_card
     *
     * @return the value of yjk_member_info.back_of_identity_card
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public String getBackOfIdentityCard() {
        return backOfIdentityCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.back_of_identity_card
     *
     * @param backOfIdentityCard the value for yjk_member_info.back_of_identity_card
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setBackOfIdentityCard(String backOfIdentityCard) {
        this.backOfIdentityCard = backOfIdentityCard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.business_licence
     *
     * @return the value of yjk_member_info.business_licence
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public String getBusinessLicence() {
        return businessLicence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.business_licence
     *
     * @param businessLicence the value for yjk_member_info.business_licence
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.companyname
     *
     * @return the value of yjk_member_info.companyname
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.companyname
     *
     * @param companyname the value for yjk_member_info.companyname
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.legal_person_name
     *
     * @return the value of yjk_member_info.legal_person_name
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public String getLegalPersonName() {
        return legalPersonName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.legal_person_name
     *
     * @param legalPersonName the value for yjk_member_info.legal_person_name
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.legal_person_idcard
     *
     * @return the value of yjk_member_info.legal_person_idcard
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public String getLegalPersonIdcard() {
        return legalPersonIdcard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.legal_person_idcard
     *
     * @param legalPersonIdcard the value for yjk_member_info.legal_person_idcard
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setLegalPersonIdcard(String legalPersonIdcard) {
        this.legalPersonIdcard = legalPersonIdcard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.legal_person_mobile
     *
     * @return the value of yjk_member_info.legal_person_mobile
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public String getLegalPersonMobile() {
        return legalPersonMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.legal_person_mobile
     *
     * @param legalPersonMobile the value for yjk_member_info.legal_person_mobile
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setLegalPersonMobile(String legalPersonMobile) {
        this.legalPersonMobile = legalPersonMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.age
     *
     * @return the value of yjk_member_info.age
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.age
     *
     * @param age the value for yjk_member_info.age
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.address
     *
     * @return the value of yjk_member_info.address
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.address
     *
     * @param address the value for yjk_member_info.address
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.create_time
     *
     * @return the value of yjk_member_info.create_time
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.create_time
     *
     * @param createTime the value for yjk_member_info.create_time
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_member_info.update_time
     *
     * @return the value of yjk_member_info.update_time
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_member_info.update_time
     *
     * @param updateTime the value for yjk_member_info.update_time
     *
     * @mbg.generated Tue Aug 28 17:13:15 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}