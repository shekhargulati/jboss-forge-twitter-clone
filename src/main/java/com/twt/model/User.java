package com.twt.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class User implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private String email;

   @Column
   private String password;

   @Temporal(TemporalType.DATE)
   private Date registeredOn;

   @Column(length = 140)
   private String bio;

   @Column
   private String fullName;

   @Temporal(TemporalType.DATE)
   private Date dateOfBirth;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((User) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public String getEmail()
   {
      return this.email;
   }

   public void setEmail(final String email)
   {
      this.email = email;
   }

   public String getPassword()
   {
      return this.password;
   }

   public void setPassword(final String password)
   {
      this.password = password;
   }

   public Date getRegisteredOn()
   {
      return this.registeredOn;
   }

   public void setRegisteredOn(final Date registeredOn)
   {
      this.registeredOn = registeredOn;
   }

   public String getBio()
   {
      return this.bio;
   }

   public void setBio(final String bio)
   {
      this.bio = bio;
   }

   public String getFullName()
   {
      return this.fullName;
   }

   public void setFullName(final String fullName)
   {
      this.fullName = fullName;
   }

   public Date getDateOfBirth()
   {
      return this.dateOfBirth;
   }

   public void setDateOfBirth(final Date dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (email != null && !email.trim().isEmpty())
         result += "email: " + email;
      if (password != null && !password.trim().isEmpty())
         result += ", password: " + password;
      if (bio != null && !bio.trim().isEmpty())
         result += ", bio: " + bio;
      if (fullName != null && !fullName.trim().isEmpty())
         result += ", fullName: " + fullName;
      return result;
   }
}