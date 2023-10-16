package com.finder.oauth.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class AbstractPersistableCustom<PK extends Serializable> implements Persistable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	protected void setId(final Long id) {

		this.id = id;
	}

	@Override
	public boolean isNew() {

		return null == this.id;
	}

}
