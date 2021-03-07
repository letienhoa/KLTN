/**
 * 
 */
package carbook.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.vladmihalcea.hibernate.type.array.IntArrayType;
//import com.vladmihalcea.hibernate.type.array.StringArrayType;
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
//import com.vladmihalcea.hibernate.type.json.JsonNodeBinaryType;
//import com.vladmihalcea.hibernate.type.json.JsonNodeStringType;
//import com.vladmihalcea.hibernate.type.json.JsonStringType;

//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;

//@TypeDefs({
 //   @TypeDef(name = "string-array", typeClass = StringArrayType.class),
  //  @TypeDef(name = "int-array", typeClass = IntArrayType.class),
   // @TypeDef(name = "json", typeClass = JsonStringType.class),
   // @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class),
   // @TypeDef(name = "jsonb-node", typeClass = JsonNodeBinaryType.class),
   // @TypeDef(name = "json-node", typeClass = JsonNodeStringType.class),
//})
@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	@CreationTimestamp
	private Date createdAt;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_at")
	@UpdateTimestamp
	private Date updatedAt;
	
	public String amountToCurrencyString(float amountTotal, String currency) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(amountTotal) + currency;
	}
	
	public String amountToCurrencyString(float amountTotal) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(amountTotal) + "đ";
	}
	
	public String getDateFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}
	
	public String getDatetimeFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
	}
	
	public String getDateFormatVNEmptyIfNull(Date date) {
		if(date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}
	
	public String getDatetimeFormatVNEmptyIfNull(Date date) {
		if(date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
	}
	
	public String getMonthYearFormatVN(Date date) {
		if (date == null) {
			return "";
		} else {
			return (new SimpleDateFormat("MM/yyyy").format(date));
		}
	}
	
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getCreatedAtFormatVN() {
		return this.getDatetimeFormatVN(createdAt);
	}
	
	public String getUpdatedAtFormatVN() {
		return this.getDatetimeFormatVN(updatedAt);
	}
}
