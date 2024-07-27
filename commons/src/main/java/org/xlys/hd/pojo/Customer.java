package org.xlys.hd.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * TODO
 *
 * @author Administrator
 * @date 2024/7/26 9:01 PM
 */
@Entity
@Table(catalog = "HD", name = "HD_CUSTOMER")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer cid;


    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT")
    private Account account;

    @Column(name = "IS_DELETED")
    private Integer isDeleted;

    @Column(name = "CREATOR")
    private String creator;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @Column(name = "MODIFIED_TIME")
    private Timestamp modifiedTime;
}
