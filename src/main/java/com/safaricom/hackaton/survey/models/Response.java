package com.safaricom.hackaton.survey.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(readOnly = true)
    private Long id;

    private Long questionId;

    private String response;

    @ApiModelProperty(readOnly = true)
    private ZonedDateTime created_at;

    @ApiModelProperty(readOnly = true)
    private ZonedDateTime updated_at;

    @PrePersist
    protected  void onCreate() { created_at = ZonedDateTime.now();  }

    @PreUpdate
    protected void onUpdate() { updated_at = ZonedDateTime.now(); }
}
