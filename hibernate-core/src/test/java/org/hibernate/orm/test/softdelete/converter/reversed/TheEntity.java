/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html.
 */
package org.hibernate.orm.test.softdelete.converter.reversed;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.orm.test.softdelete.ReverseYesNoConverter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;

/**
 * @author Steve Ebersole
 */
@Entity
@Table(name = "the_entity")
@SoftDelete(columnName = "active", converter = ReverseYesNoConverter.class)
public class TheEntity {
	@Id
	private Integer id;
	@Basic
	private String name;

	protected TheEntity() {
		// for Hibernate use
	}

	public TheEntity(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
