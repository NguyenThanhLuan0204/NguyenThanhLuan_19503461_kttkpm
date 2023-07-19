package com.SpringBoot_JWT.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deleted;

    @CreatedDate
    private Date createAt;

    @LastModifiedDate
    private Date updateAt;

    private Long createBy;

    private Long updateBy;

    public BaseEntity(Long id, String deleted, Date createAt, Date updateAt, Long createBy, Long updateBy) {
        this.id = id;
        this.deleted = deleted;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.createBy = createBy;
        this.updateBy = updateBy;
    }

    public BaseEntity(Long id, Date createAt, Date updateAt) {
        this.id = id;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity() {
    }
}
