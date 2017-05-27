package com.mark.test.framework.core.dao;

import com.mark.test.framework.core.dto.AccountInfo;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInfoMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbggenerated
     */
    @Delete({
        "delete from account_info",
        "where SERIALNO = #{serialno,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String serialno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbggenerated
     */
    @Insert({
        "insert into account_info (SERIALNO, USERID, ",
        "ACCOUNTTYPE, ACCOUNTNO, ",
        "ACCOUNTNAME, ACCOUNTBELONG, ",
        "STATUS, LIMITAMOUNT, ",
        "INPUTTIME, UPDATETIME, ",
        "BELONGAREA, CHECKNUMBER, ",
        "ACCOUNTNO2, VERIFYTYPE, ",
        "ISRETURNCARD, SUBACCOUNTBELONG, ",
        "PROVINCE, CITY, ",
        "channelCardId, PHONENO, ",
        "LoanapplyID, BankBranch, ",
        "BankBranch_Second, source, ",
        "LoanCardFlag, NybankAcctNo)",
        "values (#{serialno,jdbcType=VARCHAR}, #{userid,jdbcType=VARCHAR}, ",
        "#{accounttype,jdbcType=VARCHAR}, #{accountno,jdbcType=VARCHAR}, ",
        "#{accountname,jdbcType=VARCHAR}, #{accountbelong,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{limitamount,jdbcType=VARCHAR}, ",
        "#{inputtime,jdbcType=VARCHAR}, #{updatetime,jdbcType=VARCHAR}, ",
        "#{belongarea,jdbcType=VARCHAR}, #{checknumber,jdbcType=VARCHAR}, ",
        "#{accountno2,jdbcType=VARCHAR}, #{verifytype,jdbcType=VARCHAR}, ",
        "#{isreturncard,jdbcType=VARCHAR}, #{subaccountbelong,jdbcType=VARCHAR}, ",
        "#{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, ",
        "#{channelcardid,jdbcType=VARCHAR}, #{phoneno,jdbcType=VARCHAR}, ",
        "#{loanapplyid,jdbcType=VARCHAR}, #{bankbranch,jdbcType=VARCHAR}, ",
        "#{bankbranchSecond,jdbcType=VARCHAR}, #{source,jdbcType=VARCHAR}, ",
        "#{loancardflag,jdbcType=VARCHAR}, #{nybankacctno,jdbcType=VARCHAR})"
    })
    int insert(AccountInfo record);



    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbggenerated
     */
    @Select({
        "select",
        "SERIALNO, USERID, ACCOUNTTYPE, ACCOUNTNO, ACCOUNTNAME, ACCOUNTBELONG, STATUS, ",
        "LIMITAMOUNT, INPUTTIME, UPDATETIME, BELONGAREA, CHECKNUMBER, ACCOUNTNO2, VERIFYTYPE, ",
        "ISRETURNCARD, SUBACCOUNTBELONG, PROVINCE, CITY, channelCardId, PHONENO, LoanapplyID, ",
        "BankBranch, BankBranch_Second, source, LoanCardFlag, NybankAcctNo",
        "from account_info",
        "where SERIALNO = #{serialno,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    AccountInfo selectByPrimaryKey(String serialno);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AccountInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbggenerated
     */
    @Update({
        "update account_info",
        "set USERID = #{userid,jdbcType=VARCHAR},",
          "ACCOUNTTYPE = #{accounttype,jdbcType=VARCHAR},",
          "ACCOUNTNO = #{accountno,jdbcType=VARCHAR},",
          "ACCOUNTNAME = #{accountname,jdbcType=VARCHAR},",
          "ACCOUNTBELONG = #{accountbelong,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=VARCHAR},",
          "LIMITAMOUNT = #{limitamount,jdbcType=VARCHAR},",
          "INPUTTIME = #{inputtime,jdbcType=VARCHAR},",
          "UPDATETIME = #{updatetime,jdbcType=VARCHAR},",
          "BELONGAREA = #{belongarea,jdbcType=VARCHAR},",
          "CHECKNUMBER = #{checknumber,jdbcType=VARCHAR},",
          "ACCOUNTNO2 = #{accountno2,jdbcType=VARCHAR},",
          "VERIFYTYPE = #{verifytype,jdbcType=VARCHAR},",
          "ISRETURNCARD = #{isreturncard,jdbcType=VARCHAR},",
          "SUBACCOUNTBELONG = #{subaccountbelong,jdbcType=VARCHAR},",
          "PROVINCE = #{province,jdbcType=VARCHAR},",
          "CITY = #{city,jdbcType=VARCHAR},",
          "channelCardId = #{channelcardid,jdbcType=VARCHAR},",
          "PHONENO = #{phoneno,jdbcType=VARCHAR},",
          "LoanapplyID = #{loanapplyid,jdbcType=VARCHAR},",
          "BankBranch = #{bankbranch,jdbcType=VARCHAR},",
          "BankBranch_Second = #{bankbranchSecond,jdbcType=VARCHAR},",
          "source = #{source,jdbcType=VARCHAR},",
          "LoanCardFlag = #{loancardflag,jdbcType=VARCHAR},",
          "NybankAcctNo = #{nybankacctno,jdbcType=VARCHAR}",
        "where SERIALNO = #{serialno,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AccountInfo record);
}