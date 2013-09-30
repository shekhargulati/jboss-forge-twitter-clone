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
import com.twt.model.User;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Tweet implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column(length = 140)
   private String text;

   @Temporal(TemporalType.DATE)
   private Date createdOn;

   @ManyToOne(optional = false)
   private User postedBy;

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
         return id.equals(((Tweet) that).id);
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

   public String getText()
   {
      return this.text;
   }

   public void setText(final String text)
   {
      this.text = text;
   }

   public Date getCreatedOn()
   {
      return this.createdOn;
   }

   public void setCreatedOn(final Date createdOn)
   {
      this.createdOn = createdOn;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (text != null && !text.trim().isEmpty())
         result += "text: " + text;
      return result;
   }

   public User getPostedBy()
   {
      return this.postedBy;
   }

   public void setPostedBy(final User postedBy)
   {
      this.postedBy = postedBy;
   }
}