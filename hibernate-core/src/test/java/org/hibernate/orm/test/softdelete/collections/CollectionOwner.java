/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html.
 */
package org.hibernate.orm.test.softdelete.collections;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.SoftDelete;
import org.hibernate.type.NumericBooleanConverter;
import org.hibernate.type.YesNoConverter;

import jakarta.persistence.Basic;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * @author Steve Ebersole
 */
@Entity
@Table(name = "coll_owner")
public class CollectionOwner {
	@Id
	private Integer id;
	@Basic
	private String name;

	@ElementCollection
	@CollectionTable(name = "elements", joinColumns = @JoinColumn(name = "owner_fk"))
	@Column(name = "txt")
	@SoftDelete(converter = YesNoConverter.class)
	private Collection<String> elements;

	@ManyToMany
	@JoinTable(
			name = "m2m",
			joinColumns = @JoinColumn(name = "owner_fk"),
			inverseJoinColumns = @JoinColumn(name = "owned_fk")
	)
	@SoftDelete(columnName = "gone", converter = NumericBooleanConverter.class)
	private Collection<CollectionOwned> manyToMany;

	protected CollectionOwner() {
		// for Hibernate use
	}

	public CollectionOwner(Integer id, String name) {
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

	public Collection<String> getElements() {
		return elements;
	}

	public void setElements(Collection<String> elements) {
		this.elements = elements;
	}

	public void addElement(String element) {
		if ( elements == null ) {
			elements = new ArrayList<>();
		}
		elements.add( element );
	}

	public Collection<CollectionOwned> getManyToMany() {
		return manyToMany;
	}

	public void setManyToMany(Collection<CollectionOwned> manyToMany) {
		this.manyToMany = manyToMany;
	}

	public void addManyToMany(CollectionOwned element) {
		if ( manyToMany == null ) {
			manyToMany = new ArrayList<>();
		}
		manyToMany.add( element );
	}
}
