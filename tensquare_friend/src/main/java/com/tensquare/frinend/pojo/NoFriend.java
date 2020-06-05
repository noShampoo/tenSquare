package com.tensquare.frinend.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)
@Data
@Getter
@Setter
public class NoFriend implements Serializable {

    @Id
    private String userid;

    @Id
    private String friendid;

}
