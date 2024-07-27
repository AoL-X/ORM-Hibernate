package org.xlys.hd.pojo;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * TODO
 *
 * @author Administrator
 * @date 2024/7/26 9:01 PM
 */
@Entity
@Table(name = "HD_ACCOUNT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;


    @Column(name = "ACCOUNT_NAME")
    private String accountName;

    /**
     * test with lazy load for byte array
     * */
    @Lob
    @Column(name = "LOGO")
    private byte[] logo;

    @Column(name = "is_deleted")
    private Integer isDeleted;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_TIME")
    private Timestamp createdTime;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "MODIFIED_TIME")
    private Timestamp modifiedTime;
}
