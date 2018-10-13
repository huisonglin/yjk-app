package test.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`yjk_device`")
public class YjkDevice {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.device_name
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private String deviceName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.manufacture
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Date manufacture;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.work_time
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Integer workTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.pics
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private String pics;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.member_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Long memberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.type
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.status
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.sale_status
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Integer saleStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.spec_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Long specId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.two_stage_mode_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Long twoStageModeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.mode_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Long modeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.rent_status
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Integer rentStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.remark
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.create_time
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yjk_device.update_time
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.id
     *
     * @return the value of yjk_device.id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.id
     *
     * @param id the value for yjk_device.id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.device_name
     *
     * @return the value of yjk_device.device_name
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.device_name
     *
     * @param deviceName the value for yjk_device.device_name
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.manufacture
     *
     * @return the value of yjk_device.manufacture
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Date getManufacture() {
        return manufacture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.manufacture
     *
     * @param manufacture the value for yjk_device.manufacture
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setManufacture(Date manufacture) {
        this.manufacture = manufacture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.work_time
     *
     * @return the value of yjk_device.work_time
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Integer getWorkTime() {
        return workTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.work_time
     *
     * @param workTime the value for yjk_device.work_time
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.pics
     *
     * @return the value of yjk_device.pics
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public String getPics() {
        return pics;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.pics
     *
     * @param pics the value for yjk_device.pics
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setPics(String pics) {
        this.pics = pics;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.member_id
     *
     * @return the value of yjk_device.member_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.member_id
     *
     * @param memberId the value for yjk_device.member_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.type
     *
     * @return the value of yjk_device.type
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.type
     *
     * @param type the value for yjk_device.type
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.status
     *
     * @return the value of yjk_device.status
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.status
     *
     * @param status the value for yjk_device.status
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.sale_status
     *
     * @return the value of yjk_device.sale_status
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Integer getSaleStatus() {
        return saleStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.sale_status
     *
     * @param saleStatus the value for yjk_device.sale_status
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.spec_id
     *
     * @return the value of yjk_device.spec_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Long getSpecId() {
        return specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.spec_id
     *
     * @param specId the value for yjk_device.spec_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.two_stage_mode_id
     *
     * @return the value of yjk_device.two_stage_mode_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Long getTwoStageModeId() {
        return twoStageModeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.two_stage_mode_id
     *
     * @param twoStageModeId the value for yjk_device.two_stage_mode_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setTwoStageModeId(Long twoStageModeId) {
        this.twoStageModeId = twoStageModeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.mode_id
     *
     * @return the value of yjk_device.mode_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Long getModeId() {
        return modeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.mode_id
     *
     * @param modeId the value for yjk_device.mode_id
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setModeId(Long modeId) {
        this.modeId = modeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.rent_status
     *
     * @return the value of yjk_device.rent_status
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Integer getRentStatus() {
        return rentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.rent_status
     *
     * @param rentStatus the value for yjk_device.rent_status
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setRentStatus(Integer rentStatus) {
        this.rentStatus = rentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.remark
     *
     * @return the value of yjk_device.remark
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.remark
     *
     * @param remark the value for yjk_device.remark
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.create_time
     *
     * @return the value of yjk_device.create_time
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.create_time
     *
     * @param createTime the value for yjk_device.create_time
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yjk_device.update_time
     *
     * @return the value of yjk_device.update_time
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yjk_device.update_time
     *
     * @param updateTime the value for yjk_device.update_time
     *
     * @mbg.generated Wed Aug 29 15:25:44 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}